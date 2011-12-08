package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.entidade.Disciplina;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Resource
/**
 * Controlador de disciplinas.
 */
public class DisciplinasController {

	/**
	 * @uml.property name="result"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final Result result;
	/**
	 * @uml.property name="disciplinaDao"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private DisciplinaDao disciplinaDao;
	/**
	 * @uml.property name="usuarioSession"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private UsuarioSession usuarioSession;

	/**
	 * @param result
	 *            para interação com o jsp da disciplina.
	 * @param disciplinaDao
	 *            para interação com o banco de dados
	 * @param usuarioSession
	 *            para controle de permissões
	 */
	public DisciplinasController(Result result, DisciplinaDao disciplinaDao,
			UsuarioSession usuarioSession) {
		this.result = result;
		this.disciplinaDao = disciplinaDao;
		this.usuarioSession = usuarioSession;
	}

	// FIXME Arrumar home da disciplina
	@Get
	@Path("/disciplinas/home/{id}")
	/**
	 * Método associado à home page da disciplina com o id fornecido.
	 * @param id identificador da disciplina
	 */
	public void home(Long id) {
		result.include("disciplina", disciplinaDao.carrega(id));
	}

	/**
	 * Método associado ao .jsp que lista as disciplinas.
	 */
	public void lista() {
		result.include("lista", disciplinaDao.listaTudo());
	}

	/**
	 * Método está associado ao .jsp do formulário de cadastro de uma disciplina
	 * no sistema.
	 */
	public void cadastro() {
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u
				.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
	}

	/**
	 * Cadastra uma disciplina nova no sistema.
	 * 
	 * @param nova
	 */
	public void cadastra(final Disciplina nova) {
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u
				.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}

		disciplinaDao.salvaDisciplina(nova);
		result.redirectTo(DisciplinasController.class).lista();
	}

	@Get
	@Path("/disciplinas/alteracao/{id}")
	/**
	 * Método associado ao .jsp com formulário para alteração de cadastro de
	 * disciplina com o id fornecido.
	 * @param id
	 */
	public void alteracao(Long id) {
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u
				.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}

		result.include("disciplina", disciplinaDao.carrega(id));
	}

	/**
	 * Altera uma disciplina no banco de dados com o id fornecido e set o nome
	 * da disciplina para novoNome.
	 * 
	 * @param id
	 */
	public void altera(Long id, String novoNome) {
		Disciplina d;
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u
				.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}

		d = disciplinaDao.carrega(id);
		if (!novoNome.equals(""))
			d.setNome(novoNome);
		disciplinaDao.atualizaDisciplina(d);
		result.redirectTo(DisciplinasController.class).lista();
	}

	/**
	 * Método associado ao .jsp com formulário para remoção de cadastro de
	 * disciplina.
	 */
	public void remocao() {
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u
				.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
	}

	/**
	 * Remove uma disciplina do banco de dados com o id fornecido.
	 * 
	 * @param id
	 */
	public void remove(final Long id) {
		Disciplina disciplina;
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u
				.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
/*TODO: apagar turmas associadas*/
		disciplina = disciplinaDao.carrega(id);
		disciplinaDao.removeDisciplina(disciplina);
		result.redirectTo(DisciplinasController.class).lista();
	}

}
