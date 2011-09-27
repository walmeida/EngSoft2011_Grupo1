package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.entidade.Disciplina;

@Resource
public class DisciplinasController {
	private final Result result;
	private DisciplinaDao disciplinaDao;

	public DisciplinasController(Result result, DisciplinaDao disciplinaDao) {
		this.result = result;
		this.disciplinaDao = disciplinaDao;
	}

	public void home() {
	}

	public void lista() {
		result.include("disciplinaDao", disciplinaDao);
	}

	public void cadastro() {
	}

	public void cadastra(final Disciplina nova) {
		disciplinaDao.salvaDisciplina(nova);
		result.redirectTo(DisciplinasController.class).lista();
	}

	public void alteracao() {
	}

	public void altera(Long id, String novoNome) {
		Disciplina d = disciplinaDao.carregaPelaId(id);
		if (!novoNome.equals("")) d.setNome(novoNome);
		disciplinaDao.alteraDisciplina(d);
		result.redirectTo(DisciplinasController.class).lista();
	}
	
	public void remocao() {
	}

	public void remove(final Long id) {
		Disciplina disciplina = disciplinaDao.carregaPelaId(id);
		disciplinaDao.removeDisciplina(disciplina);
		result.redirectTo(DisciplinasController.class).lista();
	}
}
