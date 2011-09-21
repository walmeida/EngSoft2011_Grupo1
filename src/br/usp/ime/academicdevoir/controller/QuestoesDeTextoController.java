package br.usp.ime.academicdevoir.controller;

import java.util.List;

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
public class QuestoesDeTextoController {
	
	private QuestaoDeTextoDao dao;
	private final Result result;
	private Validator validator;

	public QuestoesDeTextoController(QuestaoDeTextoDao dao, Result result,
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
		validator.onErrorUsePageOf(QuestoesDeTextoController.class).cadastro();

		dao.salva(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/texto/{id}")
	/** 
	 * Retorna uma questão de texto com o id fornecido.
	 * @param id
	 * @return QuestaoDeMultiplaEscolha	 * 
	 * */
	public QuestaoDeTexto alteracao(Long id) {
		return dao.carrega(id);
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
	@Path("/questoes/texto/cadastro")
	/**
	 * Redireciona para a página com formulário para cadastro de uma nova questão de texto.
	 */
	public void cadastro() {
	}

	@Get
	@Path("/questoes/texto")
	/**
	 * Retorna uma lista com todas as questões de texto cadastradas no banco de dados.
	 * @return List<QuestaoDeMultiplaEscolha>
	 */
	public List<QuestaoDeTexto> lista() {
		return dao.listaTudo();
	}
}
