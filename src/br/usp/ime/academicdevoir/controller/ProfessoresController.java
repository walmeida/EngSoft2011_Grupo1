package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.ProfessorDao;
import br.usp.ime.academicdevoir.entidade.Professor;

@Resource
public class ProfessoresController {
	private final Result result;
	private ProfessorDao professordao;

	public ProfessoresController(Result result, ProfessorDao professordao) {
		this.result = result;
		this.professordao = professordao;
	}

	public void home() {
	}

	public void lista() {
		result.include("professordao", professordao);
	}

	public void cadastro() {
	}

	public void cadastra(final Professor novo) {
		professordao.salvaProfessor(novo);
		result.redirectTo(ProfessoresController.class).lista();
	}

	public void alteracao() {
	}

	public void altera(Long id, String novoNome, String novoEmail, String novaSenha) {
		Professor p = professordao.carregaPelaId(id);
		p.setNome(novoNome);
		p.setEmail(novoEmail);
		p.setSenha(novaSenha);
		professordao.alteraProfessor(p);
		result.redirectTo(ProfessoresController.class).lista();
	}
	
	public void remocao() {
	}

	public void remove(final Long id) {
		Professor professor = professordao.carregaPelaId(id);
		professordao.removeProfessor(professor);
		result.redirectTo(ProfessoresController.class).lista();
	}
}
