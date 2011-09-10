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
	public void adiciona(final QuestaoDeTexto questao) {
		validator.validate(questao);
		validator.onErrorUsePageOf(QuestoesDeTextoController.class).form();

		dao.salva(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/texto/{id}")
	public QuestaoDeTexto altera(Long id) {
		return dao.carrega(id);
	}

	@Put
	@Path("/questoes/texto/{questao.id}")
	public void atualiza(QuestaoDeTexto questao) {
		validator.validate(questao);
		validator.onErrorUsePageOf(QuestoesDeTextoController.class).altera(
				questao.getId());

		dao.atualiza(questao);
		result.redirectTo(this).lista();
	}

	@Delete
	@Path("/questoes/texto/{id}")
	public void remove(Long id) {
		QuestaoDeTexto questao = dao.carrega(id);
		dao.remove(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/texto/cadastro")
	public void form() {
	}

	@Get
	@Path("/questoes/texto")
	public List<QuestaoDeTexto> lista() {
		return dao.listaTudo();
	}
}
