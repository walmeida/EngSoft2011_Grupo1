package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.AlunoDao;
import br.usp.ime.academicdevoir.entidade.Aluno;

@Resource
public class AlunosController {
	private final Result result;
	private AlunoDao alunoDao;

	public AlunosController(Result result, AlunoDao alunoDao) {
		this.result = result;
		this.alunoDao = alunoDao;
	}

	public void home() {
	}

	public void lista() {
		result.include("alunoDao", alunoDao);
	}

	public void cadastro() {
	}

	public void cadastra(final Aluno novo) {
		alunoDao.salvaAluno(novo);
		result.redirectTo(AlunosController.class).lista();
	}

	public void alteracao() {
	}

	public void altera(Long id, String novoNome, String novoEmail,
			String novaSenha) {
		Aluno a = alunoDao.carregaPelaId(id);
		if (!novoNome.equals("")) a.setNome(novoNome);
		if (!novoEmail.equals("")) a.setEmail(novoEmail);
		if (!novaSenha.equals("")) a.setSenha(novaSenha);
		alunoDao.alteraAluno(a);
		result.redirectTo(AlunosController.class).lista();
	}

	public void remocao() {
	}

	public void remove(final Long id) {
		Aluno aluno = alunoDao.carregaPelaId(id);
		alunoDao.removeAluno(aluno);
		result.redirectTo(AlunosController.class).lista();
	}

	public void matricula() {
		result.include("alunoDao", alunoDao);
	}

	public void inscreve(Long idTurma) {
		alunoDao.inscreve(idTurma);
		result.redirectTo(AlunosController.class).lista();
	}
}
