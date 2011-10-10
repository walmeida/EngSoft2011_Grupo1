package br.usp.ime.academicdevoir.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.usp.ime.academicdevoir.entidade.QuestaoDaLista;
import br.usp.ime.academicdevoir.entidade.Turma;

@Resource
/**
 * Controlador de listas de exercicios.
 */
public class ListasDeExerciciosController {

	private final Result result;
	private final ListaDeExerciciosDao dao;
	private final QuestaoDao questaoDao;
	private final TurmaDao turmaDao;
	private final Validator validator;

	/**
	 * @param result para interação com o jsp da lista de exercicio.
	 * @param turmaDao para interação com o banco de dados
	 * @param validator 
	 */
	public ListasDeExerciciosController(Result result,
			ListaDeExerciciosDao dao, QuestaoDao questaoDao, TurmaDao turmaDao,
			Validator validator) {
		this.dao = dao;
		this.questaoDao = questaoDao;
		this.turmaDao = turmaDao;
		this.result = result;
		this.validator = validator;
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
	public void cadastra(ListaDeExercicios listaDeExercicios,
			final List<Integer> prazoDeEntrega, List<Long> idDasTurmas) {

		Calendar data = Calendar.getInstance();
		data.set(prazoDeEntrega.get(2).intValue(), prazoDeEntrega.get(1)
				.intValue(), prazoDeEntrega.get(0).intValue(), prazoDeEntrega
				.get(3).intValue(), prazoDeEntrega.get(4).intValue());
		Date dataDoBD = new Date(data.getTimeInMillis());

		Turma turma;
		List<Turma> turmas = new ArrayList<Turma>();
		if(idDasTurmas == null) idDasTurmas = new ArrayList<Long>();
		for (Long id : idDasTurmas) {
			turma = turmaDao.carrega(id);
			turmas.add(turma);
		}

		listaDeExercicios.setTurmas(turmas);
		listaDeExercicios.setPrazoDeEntrega(dataDoBD);

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
		SimpleDateFormat dataFormatada = new SimpleDateFormat(
				"EEE, dd'/'MM'/'YYYY HH:mm");

		result.include("prazo",
				dataFormatada.format(listaDeExercicios.getPrazoDeEntrega()));
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
		Calendar data = Calendar.getInstance();
		data.setTimeInMillis(listaDeExercicios.getPrazoDeEntrega().getTime());
		List<Integer> prazo = new ArrayList<Integer>();
		prazo.add(data.get(Calendar.DAY_OF_MONTH));
		prazo.add(data.get(Calendar.MONTH));
		prazo.add(data.get(Calendar.YEAR));
		prazo.add(data.get(Calendar.HOUR_OF_DAY));
		prazo.add(data.get(Calendar.MINUTE));

		result.include("listaDeExercicios", listaDeExercicios);
		result.include("prazo", prazo);
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
			final List<Integer> prazoDeEntrega) {

		ListaDeExercicios listaDoBD = dao.carrega(listaDeExercicios.getId());
		Calendar data = Calendar.getInstance();
		data.set(prazoDeEntrega.get(2).intValue(), prazoDeEntrega.get(1)
				.intValue(), prazoDeEntrega.get(0).intValue(), prazoDeEntrega
				.get(3).intValue(), prazoDeEntrega.get(4).intValue());
		Date dataDoBD = new Date(data.getTimeInMillis());

		listaDoBD.setNome(listaDeExercicios.getNome());
		listaDoBD.setEnunciado(listaDeExercicios.getEnunciado());
		listaDoBD.setPeso(listaDeExercicios.getPeso());
		listaDoBD.setVisivel(listaDeExercicios.getVisivel());
		listaDoBD.setPrazoDeEntrega(dataDoBD);

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
		Questao questao = questaoDao.carrega(idDaQuestao);
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
		Questao questao = questaoDao.carrega(idDaNovaQuestao);

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
	 * Redireciona para a página com formulário para cadastro de uma nova lista de exercícios.
	 */
	public void cadastro() {
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
