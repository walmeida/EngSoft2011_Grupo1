package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
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
	private Validator validator;

	/**
	 * @param result para interação com o jsp do professor.
	 * @param professorDao para interação com o banco de dados
	 * @param usuarioSession para controle de permissões
	 */
	public ProfessoresController(Result result, Validator validator, ProfessorDao professorDao, UsuarioSession usuarioSession) {
		this.result = result;
		this.validator = validator;
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
		result.redirectTo(ProfessoresController.class).listaTurmas(usuarioSession.getUsuario().getId());
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

	@Permission(Privilegio.ADMINISTRADOR)
	/**
     * Método está associado ao .jsp do formulário de cadastro de um professor no sistema.
     */
	public void cadastro() {
	}


	@Permission(Privilegio.ADMINISTRADOR)
	/**
	 * Cadastra novo professor no sitema
	 * 
	 * @param novo 
	 */
	public void cadastra(final Professor novo) {
		validator.validate(novo);
		validator.onErrorUsePageOf(ProfessoresController.class).cadastro();
		
		novo.setSenha(new Criptografia().geraMd5(novo.getSenha()));
		professorDao.salvaProfessor(novo);
		result.redirectTo(ProfessoresController.class).lista();
	}

	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
	/**
     * Método associado ao .jsp com formulário para alteração de cadastro de
     * professor.
     * 
     * @param id   identificador do professor
     */
    public void alteracao(Long id) {
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getId().longValue() == id)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
        result.include("professor", professorDao.carrega(id));
    }

	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
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
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getId().longValue() == id)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
		Professor p;
		
		p = professorDao.carrega(id);
		p.setNome(novoNome);
		p.setEmail(novoEmail);
		p.setSenha(novaSenha);
		
		validator.validate(p);
		validator.onErrorUsePageOf(ProfessoresController.class).alteracao(id);
		
		p.setSenha(new Criptografia().geraMd5(novaSenha));		
		professorDao.atualizaProfessor(p);
		result.redirectTo(ProfessoresController.class).home();
	}

	@Permission(Privilegio.ADMINISTRADOR)
	/**
	 * Remove um professor do banco de dados com o id fornecido.
	 * 
	 * @param id
	 */
	public void remove(final Long id) {
		Professor professor;
		
		professor = professorDao.carrega(id);
		professorDao.removeProfessor(professor);
		result.redirectTo(ProfessoresController.class).lista();
	}
	
	@Permission(Privilegio.ADMINISTRADOR)
	public void mudarTipo(Long id) {
		
		professorDao.alteraTipo(id);
		result.redirectTo(ProfessoresController.class).lista();
	}
	
	public void mudanca () {
	}
	
}
