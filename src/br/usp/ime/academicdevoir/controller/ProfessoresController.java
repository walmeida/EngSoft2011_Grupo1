package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.ProfessorDao;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Resource
public class ProfessoresController {
	private final Result result;
	private ProfessorDao professorDao;
	private UsuarioSession usuarioSession;
	
	public ProfessoresController(Result result, ProfessorDao professorDao, UsuarioSession usarioSession) {
		this.result = result;
		this.professorDao = professorDao;
		this.usuarioSession = usarioSession;
	}

	public void menu() {
	    
	}
	
	public void home() {
	    result.include("professor", usuarioSession.getUsuario());
	}

	public void lista() {
		result.include("professorDao", professorDao);
	}

	public void cadastro() {
	}

	public void cadastra(final Professor novo) {
		professorDao.salvaProfessor(novo);
		result.redirectTo(ProfessoresController.class).lista();
	}

	public void alteracao() {
	}

	public void altera(Long id, String novoNome, String novoEmail, String novaSenha) {
		Professor p = professorDao.carregaPelaId(id);
		if (!novoNome.equals("")) p.setNome(novoNome);
		if (!novoEmail.equals("")) p.setEmail(novoEmail);
		if (!novaSenha.equals("")) p.setSenha(novaSenha);
		professorDao.alteraProfessor(p);
		result.redirectTo(ProfessoresController.class).lista();
	}
	
	public void remocao() {
	}

	public void remove(final Long id) {
		Professor professor = professorDao.carregaPelaId(id);
		professorDao.removeProfessor(professor);
		result.redirectTo(ProfessoresController.class).lista();
	}
	
	public void mudarTipo(Long id) {
		professorDao.alteraTipo(id);
		result.redirectTo(ProfessoresController.class).lista();
	}
	
	public void mudanca () {
	}
	
}
