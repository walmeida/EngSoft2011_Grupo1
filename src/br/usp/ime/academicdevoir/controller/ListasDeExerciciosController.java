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
import br.usp.ime.academicdevoir.dao.ProfessorDao;
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.PropriedadesDaListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.usp.ime.academicdevoir.entidade.QuestaoDaLista;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Resource
/**
 * Controlador de listas de exercicios.
 */
public class ListasDeExerciciosController {

	/**
	 * @uml.property  name="result"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Result result;
	/**
	 * @uml.property  name="dao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final ListaDeExerciciosDao dao;
	/**
	 * @uml.property  name="questaoDao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final QuestaoDao questaoDao;
	/**
	 * @uml.property  name="professorDao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final ProfessorDao professorDao;
	/**
	 * @uml.property  name="turmaDao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final TurmaDao turmaDao;
	/**
	 * @uml.property  name="validator"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Validator validator;
	/**
	 * @uml.property  name="usuarioLogado"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final UsuarioSession usuarioLogado;

	/**
	 * @param result para interação com o jsp da lista de exercicio.
	 * @param turmaDao para interação com o banco de dados
	 * @param validator 
	 */
	public ListasDeExerciciosController(Result result,
			ListaDeExerciciosDao dao, QuestaoDao questaoDao,
			ProfessorDao professorDao, TurmaDao turmaDao, Validator validator,
			UsuarioSession usuarioLogado) {
		super();
		this.result = result;
		this.dao = dao;
		this.questaoDao = questaoDao;
		this.professorDao = professorDao;
		this.turmaDao = turmaDao;
		this.validator = validator;
		this.usuarioLogado = usuarioLogado;
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
	public void cadastra(PropriedadesDaListaDeExercicios propriedades, final List<Integer> prazoDeEntrega, List<Long> idDasTurmas) {			

		ListaDeExercicios listaDeExercicios = new ListaDeExercicios();
		
		Turma turma;
		List<Turma> turmas = new ArrayList<Turma>();
		if(idDasTurmas == null) idDasTurmas = new ArrayList<Long>();
		for (Long id : idDasTurmas) {
			turma = turmaDao.carrega(id);
			turmas.add(turma);
		}
		
		propriedades.setPrazoDeEntrega(prazoDeEntrega);
		listaDeExercicios.setTurmas(turmas);		
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

		result.include("listaDeExercicios", listaDeExercicios);
		result.include("prazo", listaDeExercicios.getPropriedades().getPrazoDeEntregaFormatado());
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
		
		//result.include("prazo", listaDeExercicios.getPrazoDeEntrega());
		result.include("listaDeExercicios", listaDeExercicios);
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
		ListaDeExercicios listaDeExercicios = dao.carrega(id);		

		result.include("listaDeExercicios", listaDeExercicios);
		result.include("prazo", listaDeExercicios.getPropriedades().getPrazoPrazoDeEntregaEmLista());
	}

	@Put
	@Path("/listasDeExercicios/{listaDeExercicios.id}")
	/**
	 * Verifica se a lista de exercícios fornecida é válida e atualiza no banco de dados.
	 * 
	 * @param listaDeExercicios
	 * @param prazoDeEntrega
	 */
	public void altera(ListaDeExercicios listaDeExercicios, PropriedadesDaListaDeExercicios propriedades, List<Integer> prazoDeEntrega) {

		ListaDeExercicios listaDoBD = dao.carrega(listaDeExercicios.getId());
		
		propriedades.setPrazoDeEntrega(prazoDeEntrega);
		listaDoBD.setPropriedades(propriedades);
		
		validator.validate(listaDeExercicios);
		validator
				.onErrorUsePageOf(ListasDeExerciciosController.class)
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
	public void incluiQuestao(ListaDeExercicios listaDeExercicios, Long idDaQuestao) {
		QuestaoDaLista novaQuestao = new QuestaoDaLista();
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
	 */
	public void alteraQuestao(Long id, Integer indice, Long idDaNovaQuestao) {
		ListaDeExercicios listaDeExercicios = dao.carrega(id);
		List<QuestaoDaLista> questoesDaLista = listaDeExercicios.getQuestoes();
		QuestaoDaLista questaoDaLista = listaDeExercicios.getQuestoes().get(
				indice.intValue());
		Questao questao = (Questao) questaoDao.carrega(idDaNovaQuestao);

		questaoDaLista.setQuestao(questao);
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
		ListaDeExercicios listaDeExercicios = dao.carrega(id);
		List<QuestaoDaLista> questoes = listaDeExercicios.getQuestoes();

		questoes.remove(indice.intValue());
		listaDeExercicios.setQuestoes(questoes);

		dao.atualiza(listaDeExercicios);
		result.redirectTo(this).verLista(listaDeExercicios.getId());
	}

	@Put
	@Path("/listasDeExercicios/{id}/turmas/inclui")
	/**
	 * Adiciona a turma com o id fornecido na lista de exercícios com o id fornecido.
	 * @param id
	 * @param idDaTurma
	 */
	public void incluiTurma(Long id, Long idDaTurma) {
		Turma turma = (Turma) turmaDao.carrega(idDaTurma);

		ListaDeExercicios listaDeExercicios = dao.carrega(id);
		List<Turma> turmas = listaDeExercicios.getTurmas();
		turmas.add(turma);
		listaDeExercicios.setTurmas(turmas);

		dao.atualiza(listaDeExercicios);
		result.redirectTo(this).verLista(listaDeExercicios.getId());
	}

	@Delete
	@Path("/listasDeExercicios/{listaDeExercicios.id}/turmas/{indice}")
	/**
	 * Remove a turma com o índice fornecido da lista de exercícios fornecida.
	 * @param listaDeExercicios
	 * @param indice
	 */
	public void removeTurma(ListaDeExercicios listaDeExercicios, Integer indice) {
		dao.recarrega(listaDeExercicios);
		List<Turma> turmas = listaDeExercicios.getTurmas();

		turmas.remove(indice.intValue());
		listaDeExercicios.setTurmas(turmas);

		dao.atualiza(listaDeExercicios);
		result.redirectTo(this).verLista(listaDeExercicios.getId());
	}

	@Get
	@Path("/listasDeExercicios/cadastro")
	/**
	 * Permite acesso à página com formulário para cadastro de uma nova lista de exercícios.
	 */
	public void cadastro() {
		Professor professor = professorDao.carrega(usuarioLogado.getUsuario().getId());
		result.include("turmasDoProfessor", professor.getTurmas());
	}

	@Get
	@Path("/listasDeExercicios")
	/**
	 * Devolve uma lista com todas as listas de exercícios cadastradas no banco de dados.
	 */
	public void lista() {
		result.include("listaDeListas", dao.listaTudo());
	}
}
