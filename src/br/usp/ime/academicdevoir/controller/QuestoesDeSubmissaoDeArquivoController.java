package br.usp.ime.academicdevoir.controller;

import br.usp.ime.academicdevoir.dao.QuestaoDeSubmissaoDeArquivoDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeSubmissaoDeArquivo;
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
 * Controlador de questões de submissão.
 */
public class QuestoesDeSubmissaoDeArquivoController {

	private QuestaoDeSubmissaoDeArquivoDao dao;
	private final Result result;
	private Validator validator;

	/**
	 * @param result para interação com o jsp da questão.
	 * @param dao para interação com o banco de dados
	 * @param validator 
	 */
	public QuestoesDeSubmissaoDeArquivoController(
			Result result, QuestaoDeSubmissaoDeArquivoDao dao,
			Validator validator) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}

	@Post
	@Path("/questoes/submissao")
	/**
	 * Verifica se a questão de submissão de arquivo fornecida é válida e adiciona no banco de dados.
	 * @param questao
	 */
	public void cadastra(final QuestaoDeSubmissaoDeArquivo questao) {
		validator.validate(questao);
		validator.onErrorUsePageOf(QuestoesController.class).cadastro();

		dao.salva(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/submissao/{id}")
	/** 
	 * Devolve uma questão de submissão de arquivo com o id fornecido.
	 * @param id
     */
	public void alteracao(Long id) {
		result.include("questao", dao.carrega(id));
	}

	@Put
	@Path("/questoes/submissao/{questao.id}")
	/**
	 * Verifica se a questão de submissão de arquivo fornecida é válida e atualiza no banco de dados.
	 * @param id
	 */
	public void altera(QuestaoDeSubmissaoDeArquivo questao) {
		validator.validate(questao);
		validator
				.onErrorUsePageOf(QuestoesDeSubmissaoDeArquivoController.class)
				.alteracao(questao.getId());

		dao.atualiza(questao);
		result.redirectTo(this).lista();
	}

	@Delete
	@Path("/questoes/submissao/{id}")
	/**
	 * Remove uma questão de submissão de arquivo do banco de dados com o id fornecido.
	 * @param id
	 */
	public void remove(Long id) {
		QuestaoDeSubmissaoDeArquivo questao = dao.carrega(id);
		dao.remove(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/submissao")
	/**
	 * Devolve uma lista com todas as questões de submissão de arquivo cadastradas no banco de dados.
	 */
	public void lista() {
		result.include("lista", dao.listaTudo());
	}
}
