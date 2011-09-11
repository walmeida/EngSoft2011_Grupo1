package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Turma;

@Resource
public class TurmasController {
	private final Result result;
	private TurmaDao turmaDao;

	public TurmasController(Result result, TurmaDao turmaDao) {
		this.result = result;
		this.turmaDao = turmaDao;
	}

	public void home() {
	}

	public void lista() {
		result.include("turmaDao", turmaDao);
	}

	public void cadastro() {
	}

	public void cadastra(final Turma nova, Long idProfessor, Long idDisciplina) {
		turmaDao.salvaTurma(nova, idDisciplina, idProfessor);
		result.redirectTo(TurmasController.class).lista();
	}

	public void alteracao() {
	}

	public void altera(Long id, String novoNome) {
		Turma t = turmaDao.carregaPelaId(id);
		t.setNome(novoNome);
		turmaDao.alteraTurma(t);
		result.redirectTo(TurmasController.class).lista();
	}
	
	public void remocao() {
	}

	public void remove(final Long id) {
		Turma turma = turmaDao.carregaPelaId(id);
		turmaDao.removeTurma(turma);
		result.redirectTo(TurmasController.class).lista();
	}
}
