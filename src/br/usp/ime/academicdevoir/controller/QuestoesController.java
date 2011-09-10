package br.usp.ime.academicdevoir.controller;

import java.util.List;

import br.usp.ime.academicdevoir.controller.QuestoesController;
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class QuestoesController {
	
	private final QuestaoDao dao;

	public QuestoesController(QuestaoDao dao) {
		this.dao = dao;
	}
		
	@Get
	@Path("/questoes")
	public List<Questao> lista() {
		return dao.listaTudo();	
	}
}
