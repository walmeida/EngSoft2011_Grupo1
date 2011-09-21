package br.usp.ime.academicdevoir.controller;

import java.util.List;

import br.usp.ime.academicdevoir.dao.QuestaoDeMultiplaEscolhaDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class QuestoesDeMultiplaEscolhaController {
	
	private QuestaoDeMultiplaEscolhaDao dao;
	private final Result result;
	private Validator validator;

	public QuestoesDeMultiplaEscolhaController(QuestaoDeMultiplaEscolhaDao dao,
			Result result, Validator validator) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}

	@Post
	@Path("/questoes/mult")
	/**
	 * Verifica se a questão de múltipla escolha fornecida é válida e adiciona 
	 * no banco de dados.
	 * @param questao
	 */
	public void cadastra(final QuestaoDeMultiplaEscolha questao) {
		validator.validate(questao);
		validator.onErrorUsePageOf(this).cadastro();

		dao.salva(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/mult/{id}")
	/** 
	 * Retorna uma questão de múltipla escolha com o id fornecido.
	 * @param id
	 * @return QuestaoDeMultiplaEscolha	 * 
	 * */
	public QuestaoDeMultiplaEscolha alteracao(Long id) {
		return dao.carrega(id);
	}

	@Put
	@Path("/questoes/mult/{questao.id}")
	/**
	 * Verifica se a questão de múltipla escolha fornecida é válida e atualiza no banco de dados.
	 * @param questao
	 */
	public void altera(QuestaoDeMultiplaEscolha questao) {
		validator.validate(questao);
		validator.onErrorUsePageOf(QuestoesDeMultiplaEscolhaController.class)
				.alteracao(questao.getId());

		dao.atualiza(questao);
		result.redirectTo(this).lista();
	}

	@Delete
	@Path("/questoes/mult/{id}")
	/**
	 * Remove uma questão de múltipla escolha do banco de dados com o id fornecido.
	 * @param id
	 */
	public void remove(Long id) {
		QuestaoDeMultiplaEscolha questao = dao.carrega(id);
		dao.remove(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/mult/cadastro")
	/**
	 * Redireciona para a página com formulário para cadastro de uma nova 
	 * questão de múltipla escolha.
	 */
	public void cadastro() {
	}

	@Get
	@Path("/questoes/mult")
	/**
	 * Retorna uma lista com todas as questões de múltipla escolha cadastradas 
	 * no banco de dados.
	 * @return List<QuestaoDeMultiplaEscolha>
	 */
	public List<QuestaoDeMultiplaEscolha> lista() {
		return dao.listaTudo();
	}
}
