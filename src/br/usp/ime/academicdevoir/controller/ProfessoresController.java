package br.usp.ime.academicdevoir.controller;

import org.apache.commons.lang.StringUtils;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.ProfessorDao;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.Criptografia;
import br.usp.ime.academicdevoir.infra.Permission;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
@Resource
/**
 * Controlador de professores.
 */
public class ProfessoresController {
	/**
	 * @uml.property  name="result"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Result result;
	/**
	 * @uml.property  name="professorDao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ProfessorDao professorDao;
	/**
	 * @uml.property  name="usuarioSession"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private UsuarioSession usuarioSession;

	/**
	 * @param result para interação com o jsp do professor.
	 * @param professorDao para interação com o banco de dados
	 * @param usuarioSession para controle de permissões
	 */
	public ProfessoresController(Result result, ProfessorDao professorDao, UsuarioSession usuarioSession) {
		this.result = result;
		this.professorDao = professorDao;
		this.usuarioSession = usuarioSession;
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
		result.include("listaDeProfessores", professorDao.listaTudo());
	}
	
	/**
	 * Método associado ao .jsp que lista as turmas do professor
	 * @param idProfessor id do professor 
	 */
	public void listaTurmas(Long idProfessor) {
	    result.include("professor", professorDao.carrega(idProfessor));   
	}

	/**
     * Método está associado ao .jsp do formulário de cadastro de um professor no sistema.
     */
	public void cadastro() {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
	}

	/**
	 * Cadastra novo professor no sitema
	 * 
	 * @param novo 
	 */
	public void cadastra(final Professor novo) {
		novo.setSenha(new Criptografia().geraMd5(novo.getSenha()));
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
    	Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getId().longValue() == id)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
        result.include("professor", professorDao.carrega(id));
    }
    
	/**
	 * Altera um professor no banco de dados com o id fornecido e set o nome
	 * do professor para novoNome, o email para novoEmail e a senha para novaSenha.
	 * 
	 * @param id
	 * @param novoNome
	 * @param novoEmail
	 * @param novaSenha
	 */
	public void altera(Long id, String novoNome, String novoEmail, String novaSenha) {
		Professor p;
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getId().longValue() == id)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
		p = professorDao.carrega(id);
		if (!novoNome.equals("") || !StringUtils.isBlank(novoNome)) p.setNome(novoNome);
		if (!novoEmail.equals("") || !StringUtils.isBlank(novoEmail)) p.setEmail(novoEmail);
		if (!novaSenha.equals("") || !StringUtils.isBlank(novaSenha)) p.setSenha(new Criptografia().geraMd5(novaSenha));
		professorDao.atualizaProfessor(p);
		result.redirectTo(ProfessoresController.class).home();
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
		Professor professor;
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getId().longValue() == id)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
		professor = professorDao.carrega(id);
		professorDao.removeProfessor(professor);
		result.redirectTo(ProfessoresController.class).lista();
	}
	
	public void mudarTipo(Long id) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
		professorDao.alteraTipo(id);
		result.redirectTo(ProfessoresController.class).lista();
	}
	
	public void mudanca () {
	}
	
}
