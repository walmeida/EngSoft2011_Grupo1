package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.ProfessorDao;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.infra.UsuarioSession;
import br.usp.ime.academicdevoir.infra.Permission;
import br.usp.ime.academicdevoir.infra.Privilegio;

@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
@Resource
/**
 * Controlador de professores.
 */
public class ProfessoresController {
	private final Result result;
	private ProfessorDao professorDao;

	/**
	 * @param result para interação com o jsp do professor.
	 * @param professorDao para interação com o banco de dados
	 */
	public ProfessoresController(Result result, ProfessorDao professorDao) {
		this.result = result;
		this.professorDao = professorDao;
	}

	/**
	 * Método associado ao menu da pagina do professor.
	 */
	public void menu() {
	    
	}

	/**
	 * Método associado à home page do professor.
	 */	
	public void home() {
	}

	/**
	 * Método associado ao .jsp que lista os professores.
	 */
	public void lista() {
		result.include("professorDao", professorDao);
	}

	/**
     * Método está associado ao .jsp do formulário de cadastro de um professor no sistema.
     */
	public void cadastro() {
	}

	/**
	 * Cadastra novo professor no sitema
	 * 
	 * @param novo 
	 */
	public void cadastra(final Professor novo) {
		professorDao.salvaProfessor(novo);
		result.redirectTo(ProfessoresController.class).lista();
	}

	/**
     * Método associado ao .jsp com formulário para alteração de cadastro de
     * professor.
     * 
     * @param id   identificador do professor
     */
    public void alteracao(Long id) {
        result.include("professor", professorDao.carrega(id));
    }
	/**
	 * Altera um professor no banco de dados com o id fornecido e set o nome
	 * do professor para novoNome, o email para novoEmail e a senha para novaSenha.
	 * 
	 * @param id
	 */
	public void altera(Long id, String novoNome, String novoEmail, String novaSenha) {
		Professor p = professorDao.carrega(id);
		if (!novoNome.equals("")) p.setNome(novoNome);
		if (!novoEmail.equals("")) p.setEmail(novoEmail);
		if (!novaSenha.equals("")) p.setSenha(novaSenha);
		professorDao.atualizaProfessor(p);
		result.redirectTo(ProfessoresController.class).lista();
	}

	/**
	 * Método associado ao .jsp com formulário para remoção de cadastro de
	 * professor.
	 */
	public void remocao() {
	}

	/**
	 * Remove um professor do banco de dados com o id fornecido.
	 * 
	 * @param id
	 */
	public void remove(final Long id) {
		Professor professor = professorDao.carrega(id);
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
