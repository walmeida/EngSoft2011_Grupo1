package br.usp.ime.academicdevoir.controller;

import java.util.List;

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
public class QuestoesDeSubmissaoDeArquivoController {
	private QuestaoDeSubmissaoDeArquivoDao dao;
	private final Result result;
	private Validator validator;

	public QuestoesDeSubmissaoDeArquivoController(
			QuestaoDeSubmissaoDeArquivoDao dao, Result result,
			Validator validator) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}

	@Post
	@Path("/questoes/submissao")
	public void adiciona(final QuestaoDeSubmissaoDeArquivo questao) {
		validator.validate(questao);
		validator.onErrorUsePageOf(this).form();

		dao.salva(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/submissao/{id}")
	public QuestaoDeSubmissaoDeArquivo altera(Long id) {
		return dao.carrega(id);
	}

	@Put
	@Path("/questoes/submissao/{questao.id}")
	public void atualiza(QuestaoDeSubmissaoDeArquivo questao) {
		validator.validate(questao);
		validator
				.onErrorUsePageOf(QuestoesDeSubmissaoDeArquivoController.class)
				.altera(questao.getId());

		dao.atualiza(questao);
		result.redirectTo(this).lista();
	}

	@Delete
	@Path("/questoes/submissao/{id}")
	public void remove(Long id) {
		QuestaoDeSubmissaoDeArquivo questao = dao.carrega(id);
		dao.remove(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/submissao/cadastro")
	public void form() {
	}

	@Get
	@Path("/questoes/submissao")
	public List<QuestaoDeSubmissaoDeArquivo> lista() {
		return dao.listaTudo();
	}
}
