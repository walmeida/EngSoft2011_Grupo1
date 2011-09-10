package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.AlunoDao;
import br.usp.ime.academicdevoir.entidade.Aluno;

@Resource
public class AlunosController {
	private final Result result;
	private AlunoDao alunodao;

	public AlunosController(Result result, AlunoDao alunodao) {
		this.result = result;
		this.alunodao = alunodao;
	}

	public void home() {
	}

	public void lista() {
		result.include("alunodao", alunodao);
	}

	public void cadastro() {
	}

	public void cadastra(final Aluno novo) {
		alunodao.salvaAluno(novo);
		result.redirectTo(AlunosController.class).lista();
	}

	public void alteracao() {
	}

	public void altera(Long id, String novoNome, String novoEmail, String novaSenha) {
		Aluno a = alunodao.carregaPelaId(id);
		a.setNome(novoNome);
		a.setEmail(novoEmail);
		a.setSenha(novaSenha);
		alunodao.alteraAluno(a);
		result.redirectTo(AlunosController.class).lista();
	}
	
	public void remocao() {
	}

	public void remove(final Long id) {
		Aluno aluno = alunodao.carregaPelaId(id);
		alunodao.removeAluno(aluno);
		result.redirectTo(AlunosController.class).lista();
	}
}
