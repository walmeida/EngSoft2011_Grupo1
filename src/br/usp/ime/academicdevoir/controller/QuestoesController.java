package br.usp.ime.academicdevoir.controller;

import java.util.List;

import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class QuestoesController {

	private final QuestaoDao dao;
	private final Result result;

	public QuestoesController(QuestaoDao dao, Result result) {
		this.dao = dao;
		this.result = result;
	}
	
	@Delete
	@Path("/questoes/{id}")
	public void remove(Long id) {
		Questao questao = dao.carrega(id);
		dao.remove(questao);
		result.redirectTo(this).lista();
	}	

	@Get
	@Path("/questoes")
	public List<Questao> lista() {
		return dao.listaTudo();
	}

}
