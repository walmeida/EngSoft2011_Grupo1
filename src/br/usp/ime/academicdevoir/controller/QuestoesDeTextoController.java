package br.usp.ime.academicdevoir.controller;

import br.usp.ime.academicdevoir.dao.QuestaoDeTextoDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeTexto;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
/**
 * Controlador de questões de texto.
 */
public class QuestoesDeTextoController {

	private QuestaoDeTextoDao dao;
	private final Result result;
	private Validator validator;

	/**
	 * @param result para interação com o jsp da questão.
	 * @param dao para interação com o banco de dados
	 * @param validator 
	 */
	public QuestoesDeTextoController(Result result, QuestaoDeTextoDao dao,
			Validator validator) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}

	@Post
	@Path("/questoes/texto")
	/**
	 * Verifica se a questão de texto fornecida é válida e adiciona no banco de dados.
	 * @param questao
	 */
	public void cadastra(final QuestaoDeTexto questao) {
		validator.validate(questao);
		validator.onErrorUsePageOf(QuestoesController.class).cadastro();

		dao.salva(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/texto/{id}")
	/** 
	 * Devolve uma questão de texto com o id fornecido.
	 * @param id
     */
	public void alteracao(Long id) {
		result.include("questao", dao.carrega(id));
	}

	@Put
	@Path("/questoes/texto/{questao.id}")
	/**
	 * Verifica se a questão de texto fornecida é válida e atualiza no banco de dados.
	 * @param id
	 */
	public void altera(QuestaoDeTexto questao) {
		validator.validate(questao);
		validator.onErrorUsePageOf(QuestoesDeTextoController.class).alteracao(
				questao.getId());

		dao.atualiza(questao);
		result.redirectTo(this).lista();
	}

	@Delete
	@Path("/questoes/texto/{id}")
	/**
	 * Remove uma questão de texto do banco de dados com o id fornecido.
	 * @param id
	 */
	public void remove(Long id) {
		QuestaoDeTexto questao = dao.carrega(id);
		dao.remove(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/texto")
	/**
	 * Devolve uma lista com todas as questões de texto cadastradas no banco de dados.
	 */
	public void lista() {
		result.include("lista", dao.listaTudo());
	}
}
