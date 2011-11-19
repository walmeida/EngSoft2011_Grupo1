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
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;


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
	/**
	 * Verifica se a lista de exercícios fornecida é válida e adiciona no banco de dados.
	 * 
	 * @param listaDeExercicios
	 * @param prazoDeEntrega
	 * @param idDasTurmas
	 */
	public void cadastra(PropriedadesDaListaDeExercicios propriedades,
			final List<Integer> prazoDeEntrega, Long idDaTurma) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
		ListaDeExercicios listaDeExercicios = new ListaDeExercicios();

		Turma turma = turmaDao.carrega(idDaTurma);
		/*List<Turma> turmas = new ArrayList<Turma>();
		if (idDasTurmas == null)
			idDasTurmas = new ArrayList<Long>();
		for (Long id : idDasTurmas) {
			turma = turmaDao.carrega(id);
			turmas.add(turma);
		}*/

		propriedades.setPrazoDeEntrega(prazoDeEntrega);
		listaDeExercicios.setTurma(turma);
		listaDeExercicios.setPropriedades(propriedades);

		validator.validate(listaDeExercicios);
		validator.onErrorUsePageOf(this).cadastro();

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
		ListaDeExercicios listaDeExercicios = dao.carrega(id);
		Professor professor = professorDao.carrega(usuarioSession.getUsuario()
				.getId());

		result.include("listaDeExercicios", listaDeExercicios);
		result.include("prazo", listaDeExercicios.getPropriedades()
				.getPrazoDeEntregaFormatado());
		result.include("prazo", listaDeExercicios.getPropriedades()
				.getPrazoDeEntregaFormatado());
		result.include("turmasDoProfessor", professor.getTurmas());
	}

	@Get
	@Path("/listasDeExercicios/resolver/{id}")
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
			listaDeRespostas.setListaDeExercicios(listaDeExercicios);
			listaDeRespostas.setAluno(aluno);
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
	public void alterarRespostas(ListaDeRespostas listaDeRespostas) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
		listaDeRespostas = listaDeRespostasDao
				.carrega(listaDeRespostas.getId());
		ListaDeExercicios listaDeExercicios = listaDeRespostas
				.getListaDeExercicios();
		List<String> renders = new ArrayList<String>();
		List<Resposta> respostas = listaDeRespostas.getRespostas();
		for (Resposta resposta : respostas) {
			renders.add(resposta.getQuestao().getRenderAlteracao(resposta));
		}

		result.include("renderizacao", renders);
		result.include("listaDeRespostas", listaDeRespostas);
		result.include("listaDeExercicios", listaDeExercicios);
		result.include("numeroDeQuestoes", listaDeExercicios.getQuestoes()
				.size());
	}

	@Get
	@Path("/listasDeExercicios/altera/{id}")
	/** 
	 * Retorna uma lista de exercícios com o id fornecido.
	 * 
	 * @param id
	 * @return ListaDeExercicios 
	 * */
	public void alteracao(Long id) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
		ListaDeExercicios listaDeExercicios = dao.carrega(id);

		result.include("listaDeExercicios", listaDeExercicios);
		result.include("prazo", listaDeExercicios.getPropriedades()
				.getPrazoPrazoDeEntregaEmLista());
	}

	@Put
	@Path("/listasDeExercicios/{listaDeExercicios.id}")
	/**
	 * Verifica se a lista de exercícios fornecida é válida e atualiza no banco de dados.
	 * 
	 * @param listaDeExercicios
	 * @param prazoDeEntrega
	 */
	public void altera(ListaDeExercicios listaDeExercicios,
			PropriedadesDaListaDeExercicios propriedades,
			List<Integer> prazoDeEntrega) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
		ListaDeExercicios listaDoBD = dao.carrega(listaDeExercicios.getId());

		propriedades.setPrazoDeEntrega(prazoDeEntrega);
		listaDoBD.setPropriedades(propriedades);

		validator.validate(listaDeExercicios);
		validator.onErrorUsePageOf(ListasDeExerciciosController.class)
				.alteracao(listaDeExercicios.getId());

		dao.atualiza(listaDoBD);
		result.redirectTo(this).verLista(listaDoBD.getId());
	}

	/**
	 * Remove uma lista de exercícios do banco de dados com o id fornecido.
	 * 
	 * @param id
	 */
	@Delete
	@Path("/listasDeExercicios/{id}")
	public void remove(Long id) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
		ListaDeExercicios lista = dao.carrega(id);
		dao.remove(lista);
		result.redirectTo(this).lista();
	}

	@Put
	@Path("/listasDeExercicios/{listaDeExercicios.id}/questoes/inclui")
	/**
	 * Inclui a questão com o id fornecido na lista de exercícios.
	 * 
	 * @param listaDeExercicios
	 * @param idDaQuestao
	 */
	public void incluiQuestao(ListaDeExercicios listaDeExercicios,
			Long idDaQuestao, Integer pesoDaQuestao, Integer ordemDaQuestao) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
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
	/**
	 * Altera a questão com o indice fornecido (na lista de exercícios com o id fornecido)
	 * para a questão com id fornecido.
	 * 
	 * @param id
	 * @param indice
	 * @param idDaNovaQuestao
	 * @param ordemDaQuestao
	 */
	public void alteraQuestao(Long id, Integer indice, Long idDaNovaQuestao,
			Integer ordemDaQuestao) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
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
	/**
	 * Remove a questão com o indice fornecido na lista de exercícios com o id fornecido.
	 * 
	 * @param id
	 * @param indice
	 */
	public void removeQuestao(Long id, Integer indice) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
		ListaDeExercicios listaDeExercicios = dao.carrega(id);
		List<QuestaoDaLista> questoes = listaDeExercicios.getQuestoes();

		questoes.remove(indice.intValue());
		listaDeExercicios.setQuestoes(questoes);

		dao.atualiza(listaDeExercicios);
		result.redirectTo(this).verLista(listaDeExercicios.getId());
	}

	//@Put
	//@Path("/listasDeExercicios/{id}/turmas/inclui")
	/**
	 * Adiciona a turma com o id fornecido na lista de exercícios com o id fornecido.
	 * @param id
	 * @param idDaTurma
	 */
/*	public void incluiTurma(Long id, Long idDaTurma) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
		Turma turma = (Turma) turmaDao.carrega(idDaTurma);

		ListaDeExercicios listaDeExercicios = dao.carrega(id);
		listaDeExercicios.setTurma(turma);
//		List<Turma> turmas = listaDeExercicios.getTurmas();
//		if (!turmas.contains(turma)) {
//			turmas.add(turma);
//			listaDeExercicios.setTurmas(turmas);
//			dao.atualiza(listaDeExercicios);
//		}
		result.redirectTo(this).verLista(listaDeExercicios.getId());
	}
*/
	//@Delete
	//@Path("/listasDeExercicios/{listaDeExercicios.id}/turmas/{indice}")
	/**
	 * Remove a turma com o índice fornecido da lista de exercícios fornecida.
	 * @param listaDeExercicios
	 * @param indice
	 */
	/*public void removeTurma(ListaDeExercicios listaDeExercicios, Integer indice) {
		dao.recarrega(listaDeExercicios);
		List<Turma> turmas = listaDeExercicios.getTurmas();

		turmas.remove(indice.intValue());
		listaDeExercicios.setTurmas(turmas);

		dao.atualiza(listaDeExercicios);
		result.redirectTo(this).verLista(listaDeExercicios.getId());
	}*/

	@Get
	@Path("/listasDeExercicios/cadastro")
	/**
	 * Permite acesso à página com formulário para cadastro de uma nova lista de exercícios.
	 */
	public void cadastro() {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
		Professor professor = professorDao.carrega(usuarioSession.getUsuario()
				.getId());
		result.include("turmasDoProfessor", professor.getTurmas());
	}

	@Get
	@Path("/listasDeExercicios")
	/**
	 * Devolve uma lista com todas as listas de exercícios cadastradas no banco de dados.
	 */
	public void lista() {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
		result.include("listaDeListas", dao.listaTudo());
	}
}
