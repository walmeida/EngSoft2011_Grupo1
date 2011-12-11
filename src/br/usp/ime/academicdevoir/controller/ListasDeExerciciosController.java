package br.usp.ime.academicdevoir.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.usp.ime.academicdevoir.dao.ListaDeExerciciosDao;
import br.usp.ime.academicdevoir.dao.ListaDeRespostasDao;
import br.usp.ime.academicdevoir.dao.ProfessorDao;
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.AutoCorrecao;
import br.usp.ime.academicdevoir.entidade.EstadoDaListaDeRespostas;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.ListaDeRespostas;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.PropriedadesDaListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.PropriedadesDaListaDeRespostas;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.usp.ime.academicdevoir.entidade.QuestaoDaLista;
import br.usp.ime.academicdevoir.entidade.Resposta;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.Constantes;
import br.usp.ime.academicdevoir.infra.Permission;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;
import br.usp.ime.academicdevoir.infra.VerificadorDePrazos;

@Resource
/**
 * Controlador de listas de exercicios.
 */
public class ListasDeExerciciosController {

	/**
	 * @uml.property name="result"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final Result result;
	/**
	 * @uml.property name="dao"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final ListaDeExerciciosDao dao;
	/**
	 * @uml.property name="listaDeRespostasDao"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final ListaDeRespostasDao listaDeRespostasDao;
	/**
	 * @uml.property name="questaoDao"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final QuestaoDao questaoDao;
	/**
	 * @uml.property name="professorDao"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final ProfessorDao professorDao;
	/**
	 * @uml.property name="turmaDao"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final TurmaDao turmaDao;
	/**
	 * @uml.property name="validator"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final Validator validator;
	/**
	 * @uml.property name="usuarioSession"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final UsuarioSession usuarioSession;

	/**
	 * @param result
	 *            para interação com o jsp da lista de exercicio.
	 * @param turmaDao
	 *            para interação com o banco de dados
	 * @param validator
	 */
	public ListasDeExerciciosController(Result result,
			ListaDeExerciciosDao dao, ListaDeRespostasDao listaDeRespostasDao,
			QuestaoDao questaoDao, ProfessorDao professorDao,
			TurmaDao turmaDao, Validator validator, UsuarioSession usuarioSession) {

		this.result = result;
		this.dao = dao;
		this.listaDeRespostasDao = listaDeRespostasDao;
		this.questaoDao = questaoDao;
		this.professorDao = professorDao;
		this.turmaDao = turmaDao;
		this.validator = validator;
		this.usuarioSession = usuarioSession;
	}

	@Post
	@Path("/listasDeExercicios")
	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
	/**
	 * Verifica se a lista de exercícios fornecida é válida e adiciona no banco de dados.
	 * 
	 * @param listaDeExercicios
	 * @param prazoDeEntrega
	 * @param idDasTurmas
	 */
	public void cadastra(PropriedadesDaListaDeExercicios propriedades,
			final List<Integer> prazoDeEntrega, Long idDaTurma) {
		
		ListaDeExercicios listaDeExercicios = new ListaDeExercicios();

		Turma turma = turmaDao.carrega(idDaTurma);

		propriedades.setPrazoDeEntrega(prazoDeEntrega);
		
		validator.validate(propriedades);
		validator.onErrorUsePageOf(this).cadastro();
		
		listaDeExercicios.setTurma(turma);
		listaDeExercicios.setPropriedades(propriedades);

		dao.salva(listaDeExercicios);
		result.redirectTo(this).verLista(listaDeExercicios.getId());
	}

	@Get
	@Path("/listasDeExercicios/{id}")
	/** 
	 * Devolve uma lista de exercícios com o id fornecido.
	 * 
	 * @param id
	 * */
	public void verLista(Long id) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
		ListaDeExercicios listaDeExercicios = dao.carrega(id);
		Professor professor = professorDao.carrega(usuarioSession.getUsuario()
				.getId());

		result.include("listaDeExercicios", listaDeExercicios);
		result.include("prazo", listaDeExercicios.getPropriedades()
				.getPrazoDeEntregaFormatado());
		result.include("turmasDoProfessor", professor.getTurmas());
	}

	@Get
	@Path("/listasDeExercicios/resolver/{id}")
	@Permission({ Privilegio.ALUNO, Privilegio.MONITOR })
	/** 
	 * Devolve uma lista de exercícios com o id fornecido.
	 * 
	 * @param id
	 * */
	public void resolverLista(Long id) {
		ListaDeExercicios listaDeExercicios = dao.carrega(id);
		
		Aluno aluno = (Aluno) usuarioSession.getUsuario();
		ListaDeRespostas listaDeRespostas = listaDeRespostasDao
				.getRespostasDoAluno(id, aluno);

		if (listaDeRespostas == null) {
			PropriedadesDaListaDeRespostas propriedades = new PropriedadesDaListaDeRespostas();
			 
			propriedades.setEstado(EstadoDaListaDeRespostas.SALVA);
			propriedades.setNumeroDeEnvios(0);

			listaDeRespostas = new ListaDeRespostas();
			listaDeRespostas.setPropriedades(propriedades);
			listaDeRespostas.setListaDeExercicios(listaDeExercicios);
			listaDeRespostas.setAluno(aluno);
			listaDeRespostas.setPropriedades(propriedades);
			if (!VerificadorDePrazos.estaNoPrazo(listaDeExercicios.
                    getPropriedades().getPrazoDeEntrega())) {
                listaDeRespostas.getPropriedades().
                setEstado(EstadoDaListaDeRespostas.FINALIZADA);
                listaDeRespostas.setRespostas(new ArrayList<Resposta>());
                listaDeRespostasDao.salva(listaDeRespostas);
                result.redirectTo(this).verCorrecao(listaDeRespostas);
                return;
            }
		}

		else if (listaDeRespostas.getRespostas() != null
				&& listaDeRespostas.getRespostas().size() > 0) {
			result.redirectTo(this).alterarRespostas(listaDeRespostas);
			return;
		}

		listaDeRespostas.setRespostas(new ArrayList<Resposta>());
        listaDeRespostasDao.salva(listaDeRespostas);
		
		result.include("prazo", listaDeExercicios.getPropriedades()
				.getPrazoDeEntregaFormatado());
		result.include("listaDeExercicios", listaDeExercicios);
		result.include("numeroDeQuestoes", listaDeExercicios.getQuestoes()
				.size());
		result.include("listaDeRespostas", listaDeRespostas);
	}

	@Get
	@Path("/respostas/alterar/{listaDeRespostas.id}")
	@Permission({ Privilegio.ALUNO, Privilegio.MONITOR })
	public void alterarRespostas(ListaDeRespostas listaDeRespostas) {
	    listaDeRespostas = listaDeRespostasDao
				.carrega(listaDeRespostas.getId());
	    
        ListaDeExercicios listaDeExercicios = listaDeRespostas
                                                   .getListaDeExercicios();
        
        if (listaDeRespostas.getPropriedades().getEstado() == 
                EstadoDaListaDeRespostas.SALVA && 
                !VerificadorDePrazos.estaNoPrazo(listaDeRespostas.
                getListaDeExercicios().getPropriedades().getPrazoDeEntrega())) {
            listaDeRespostas.getPropriedades().
                setEstado(EstadoDaListaDeRespostas.FINALIZADA);
            listaDeRespostasDao.atualiza(listaDeRespostas);
        }
            
	    if (listaDeRespostas.getPropriedades().getEstado() == 
	        EstadoDaListaDeRespostas.CORRIGIDA ||
	        listaDeRespostas.getPropriedades().getEstado() == 
	            EstadoDaListaDeRespostas.FINALIZADA)
	        result.redirectTo(ListasDeExerciciosController.class).
	               verCorrecao(listaDeRespostas);

		List<String> renders = new ArrayList<String>();
		
		List<QuestaoDaLista> questoes = listaDeExercicios.getQuestoes();
		List<Resposta> respostas = listaDeRespostas.getRespostas();
		boolean achouResposta;
		
		for (QuestaoDaLista questaoDaLista : questoes) {
			
			achouResposta = false;
			for (Resposta resposta : respostas) {
				if (resposta.getQuestao().getId() == questaoDaLista.getQuestao().getId()) {
					renders.add(resposta.getQuestao().getRenderAlteracao(resposta));
					respostas.remove(resposta);
					achouResposta = true;
					break;
				}
			}
			if (achouResposta) continue;
			renders.add(questaoDaLista.getQuestao().getRenderizacao());
		}

		result.include("renderizacao", renders);
		result.include("listaDeRespostas", listaDeRespostas);
		result.include("listaDeExercicios", listaDeExercicios);
		result.include("numeroDeQuestoes", questoes
				.size());
	    result.include("VerificadorDePrazos", VerificadorDePrazos.class);

	}

	@Get
    @Path("/respostas/verCorrecao/{listaDeRespostas.id}")
    public void verCorrecao(ListaDeRespostas listaDeRespostas) {
	    listaDeRespostas = listaDeRespostasDao
            .carrega(listaDeRespostas.getId());
	   
	    ListaDeExercicios listaDeExercicios = listaDeRespostas
            .getListaDeExercicios();
	    List<String> renders = new ArrayList<String>();
    
	    List<QuestaoDaLista> questoes = listaDeExercicios.getQuestoes();
	    List<Resposta> respostas = listaDeRespostas.getRespostas();
	    boolean achouResposta;
    
	    for (QuestaoDaLista questaoDaLista : questoes) {
        
	        achouResposta = false;
	        for (Resposta resposta : respostas) {
	            if (resposta.getQuestao().getId() == questaoDaLista.getQuestao().
	                    getId()) {
	                renders.add(resposta.getQuestao().getRenderCorrecao(resposta));
	                respostas.remove(resposta);
	                achouResposta = true;
	                break;
	            }
	        }

	        if (achouResposta) continue;
	        renders.add(questaoDaLista.getQuestao().
	                getRenderCorrecao(new Resposta()));
	    }
    
        result.include("renderizacao", renders);
        result.include("listaDeRespostas", listaDeRespostas);
        result.include("listaDeExercicios", listaDeExercicios);
        result.include("numeroDeQuestoes", questoes
                .size());
    }
	
	@Get
	@Path("/listasDeExercicios/altera/{id}")
	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
	/** 
	 * Retorna uma lista de exercícios com o id fornecido.
	 * 
	 * @param id
	 * @return ListaDeExercicios 
	 * */
	public void alteracao(Long id) {
		
		ListaDeExercicios listaDeExercicios = dao.carrega(id);

		result.include("listaDeExercicios", listaDeExercicios);
		result.include("prazo", listaDeExercicios.getPropriedades()
				.getPrazoPrazoDeEntregaEmLista());
	}

	@Put
	@Path("/listasDeExercicios/{listaDeExercicios.id}")
	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
	/**
	 * Verifica se a lista de exercícios fornecida é válida e atualiza no banco de dados.
	 * 
	 * @param listaDeExercicios
	 * @param prazoDeEntrega
	 */
	public void altera(ListaDeExercicios listaDeExercicios,
			PropriedadesDaListaDeExercicios propriedades,
			List<Integer> prazoDeEntrega) {

		ListaDeExercicios listaDoBD = dao.carrega(listaDeExercicios.getId());

		propriedades.setPrazoDeEntrega(prazoDeEntrega);
		listaDoBD.setPropriedades(propriedades);

		validator.validate(listaDeExercicios);
		validator.onErrorUsePageOf(ListasDeExerciciosController.class)
				.alteracao(listaDeExercicios.getId());

		dao.atualiza(listaDoBD);
		result.redirectTo(this).verLista(listaDoBD.getId());
	}

	@Delete
	@Path("/listasDeExercicios/{id}")
	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
	/**
	 * Remove uma lista de exercícios do banco de dados com o id fornecido.
	 * 
	 * @param id
	 */
	public void remove(Long id) {
		ListaDeExercicios lista = dao.carrega(id);
		dao.remove(lista);
		result.redirectTo(this).lista();
	}

	@Put
	@Path("/listasDeExercicios/{listaDeExercicios.id}/questoes/inclui")
	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
	/**
	 * Inclui a questão com o id fornecido na lista de exercícios.
	 * 
	 * @param listaDeExercicios
	 * @param idDaQuestao
	 */
	public void incluiQuestao(ListaDeExercicios listaDeExercicios,
			Long idDaQuestao, Integer pesoDaQuestao, Integer ordemDaQuestao) {
		
		QuestaoDaLista novaQuestao = new QuestaoDaLista();
		novaQuestao.setPeso(pesoDaQuestao);
		novaQuestao.setOrdem(ordemDaQuestao);
		Questao questao = (Questao) questaoDao.carrega(idDaQuestao);
		novaQuestao.setQuestao(questao);

		dao.recarrega(listaDeExercicios);
		List<QuestaoDaLista> questoes = listaDeExercicios.getQuestoes();
		questoes.add(novaQuestao);
		listaDeExercicios.setQuestoes(questoes);

		dao.atualiza(listaDeExercicios);
		result.redirectTo(this).verLista(listaDeExercicios.getId());
	}

	@Put
	@Path("/listasDeExercicios/{id}/questoes/{indice}")
	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
	/**
	 * Altera a questão com o indice fornecido (na lista de exercícios com o id fornecido)
	 * para a questão com id fornecido.
	 * 
	 * @param id
	 * @param indice
	 * @param idDaNovaQuestao
	 * @param ordemDaQuestao
	 */
	public void trocaQuestao(Long id, Integer indice, Long idDaNovaQuestao,
			Integer ordemDaQuestao) {
		
		ListaDeExercicios listaDeExercicios = dao.carrega(id);
		List<QuestaoDaLista> questoesDaLista = listaDeExercicios.getQuestoes();
		QuestaoDaLista questaoDaLista = listaDeExercicios.getQuestoes().get(
				indice.intValue());
		Questao questao = (Questao) questaoDao.carrega(idDaNovaQuestao);

		questaoDaLista.setQuestao(questao);
		questaoDaLista.setOrdem(ordemDaQuestao);
		questoesDaLista.set(indice.intValue(), questaoDaLista);
		listaDeExercicios.setQuestoes(questoesDaLista);

		dao.atualiza(listaDeExercicios);
		result.redirectTo(this).verLista(listaDeExercicios.getId());
	}

	@Delete
	@Path("/listasDeExercicios/{id}/questoes/{indice}")
	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
	/**
	 * Remove a questão com o indice fornecido na lista de exercícios com o id fornecido.
	 * 
	 * @param id
	 * @param indice
	 */
	public void removeQuestao(Long id, Integer indice) {		
		ListaDeExercicios listaDeExercicios = dao.carrega(id);
		List<QuestaoDaLista> questoes = listaDeExercicios.getQuestoes();

		questoes.remove(indice.intValue());
		listaDeExercicios.setQuestoes(questoes);

		dao.atualiza(listaDeExercicios);
		result.redirectTo(this).verLista(listaDeExercicios.getId());
	}

	@Get
	@Path("/listasDeExercicios/cadastro")
	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
	/**
	 * Permite acesso à página com formulário para cadastro de uma nova lista de exercícios.
	 */
	public void cadastro() {
		Professor professor = professorDao.carrega(usuarioSession.getUsuario()
				.getId());
		result.include("turmasDoProfessor", professor.getTurmas());
	}

	@Get
	@Path("/listasDeExercicios")
	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
	/**
	 * Devolve uma lista com todas as listas de exercícios cadastradas no banco de dados.
	 */
	public void lista() {
		result.include("listaDeListas", dao.listaTudo());
	}
	
	@Get
	@Path("/listasDeExercicios/listasTurma/{idTurma}")
	/**
	 * Devolve uma lista com todas as listas de exercícios de uma determinada turma.
	 */
	public void listasTurma(Long idTurma) {
		Turma turma = turmaDao.carrega(idTurma);
		result.include("listaDeListas", dao.listasDeTurma(turma));
	}
	
	@Get
    @Path("/respostas/autocorrecao/{id}")
    /** 
     * Corrige todas as respostas da lista de exercícios com o id fornecido.
     * 
     * @param id
     * */
    public void autoCorrecaoRespostas(Long id) {
        //Carrega a lista de exercícios com esse id
        ListaDeRespostas listaDeRespostas = listaDeRespostasDao.carrega(id);
        
        AutoCorrecao autoCorrecao = listaDeRespostas.getListaDeExercicios().
            getPropriedades().getAutoCorrecao();
        
        if (listaDeRespostas.getPropriedades().getEstado() == 
                EstadoDaListaDeRespostas.SALVA && 
                !VerificadorDePrazos.estaNoPrazo(listaDeRespostas.
                getListaDeExercicios().getPropriedades().getPrazoDeEntrega())) {
            listaDeRespostas.getPropriedades().
                setEstado(EstadoDaListaDeRespostas.FINALIZADA);
            listaDeRespostasDao.atualiza(listaDeRespostas);
        }
            
        //Não corrige se autocorreção estiver desativada para esse lista
        if (autoCorrecao == AutoCorrecao.AMBOS) {
        
            listaDeRespostas.autocorrecao();
            listaDeRespostasDao.atualiza(listaDeRespostas);
            //Redireciona para o menu de listas
            result.redirectTo(this).verCorrecao(listaDeRespostas);
        }
        
        else
            result.redirectTo(this).listasTurma(listaDeRespostas.
                    getListaDeExercicios().getTurma().getId());
    }
	
	@Get
	@Path("/listasDeExercicios/autocorrecao/{id}")
	/** 
	 * Corrige todas as respostas da lista de exercícios com o id fornecido.
	 * 
	 * @param id
	 * */
	public void autoCorrecaoLista(Long id) {
		//Carrega a lista de exercícios com esse id
		ListaDeExercicios listaDeExercicios = dao.carrega(id);
		
		//Carrega as propriedades da lista de exercícios
		PropriedadesDaListaDeExercicios propriedades = listaDeExercicios.getPropriedades();
		
		//Não corrige se autocorreção estiver desativada para esse lista
		if (propriedades.getAutoCorrecao() == AutoCorrecao.DESATIVADA) {
			result.redirectTo(this).lista();
			return;
		}
		
		//Pegando todas as listas de respostas. Cada elemento corresponde a Lista de um aluno
		List<ListaDeRespostas> listasDeRespostas = listaDeRespostasDao.listaRespostasDaLista(listaDeExercicios);
		
		//Para cada Lista de Resposta (Aluno)
		for (ListaDeRespostas listaDeRespostas : listasDeRespostas) {
		    listaDeRespostas.autocorrecao();
		    listaDeRespostas.getPropriedades().
		        setEstado(EstadoDaListaDeRespostas.CORRIGIDA);

			listaDeRespostasDao.atualiza(listaDeRespostas);
		}
		
		//Redireciona para o menu de listas
		result.redirectTo(this).lista();
		
	}
	
	@Get
	@Path("/listasDeExercicios/{id}/inclusaoQuestoes")
	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
	/** 
	 * Devolve a lista de questões que poderão ser inseridas na lista com o id fornecido.
	 * 
	 * @param id
	 * */
	public void inclusaoQuestoes(Long id, Integer proxPagina, String filtro) {
		List<Questao> listaDeQuestoesPaginadas;
		Integer primeiroReg, ultimaPagina;
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
		primeiroReg = (proxPagina - 1)*Constantes.NUM_REGISTROS_PAGINA;
		
		listaDeQuestoesPaginadas = questaoDao.listaPaginada(primeiroReg, Constantes.NUM_REGISTROS_PAGINA, filtro);
		ultimaPagina = questaoDao.tamanhoDaLista(filtro) / Constantes.NUM_REGISTROS_PAGINA;
		if(listaDeQuestoesPaginadas.size() % Constantes.NUM_REGISTROS_PAGINA != 0) ultimaPagina++;
		
		result.include("idDaListaDeExercicios", id);
		result.include("listaDeQuestoes", listaDeQuestoesPaginadas);
		result.include("pagina", proxPagina);
		result.include("ultimaPagina", ultimaPagina);
		result.include("filtroAtual", filtro);		
	}
	
	
}
