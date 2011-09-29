package br.usp.ime.academicdevoir.controller;

import br.usp.ime.academicdevoir.dao.QuestaoDeVouFDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeVouF;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class QuestoesDeVouFController {

	private QuestaoDeVouFDao dao;
	private final Result result;
	private Validator validator;

	public QuestoesDeVouFController(QuestaoDeVouFDao dao, Result result,
			Validator validator) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}

	@Post
	@Path("/questoes/vouf")
	/**
	 * Verifica se a questão de V ou F fornecida é válida e adiciona no banco de dados.
	 * @param questao
	 */
	public void cadastra(final QuestaoDeVouF questao) {
		validator.validate(questao);
		validator.onErrorUsePageOf(QuestoesController.class).cadastro();

		dao.salva(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/vouf/{id}")
	/** 
	 * Retorna uma questão de V ou F com o id fornecido.
	 * @param id
	 * @return QuestaoDeMultiplaEscolha	 * 
	 * */
	public void alteracao(Long id) {
		result.include("questao", dao.carrega(id));
	}

	@Put
	@Path("/questoes/vouf/{questao.id}")
	/**
	 * Verifica se a questão de V ou F fornecida é válida e atualiza no banco de dados.
	 * @param id
	 */
	public void altera(QuestaoDeVouF questao) {
		validator.validate(questao);
		validator.onErrorUsePageOf(QuestoesDeVouFController.class).alteracao(
				questao.getId());

		dao.atualiza(questao);
		result.redirectTo(this).lista();
	}

	@Delete
	@Path("/questoes/vouf/{id}")
	/**
	 * Remove uma questão de V ou F do banco de dados com o id fornecido.
	 * @param id
	 */
	public void remove(Long id) {
		QuestaoDeVouF questao = dao.carrega(id);
		dao.remove(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/vouf")
	/**
	 * Retorna uma lista com todas as questões de V ou F cadastradas no banco de dados.
	 * @return List<QuestaoDeMultiplaEscolha>
	 */
	public void lista() {
		result.include("lista", dao.listaTudo());
	}
}
