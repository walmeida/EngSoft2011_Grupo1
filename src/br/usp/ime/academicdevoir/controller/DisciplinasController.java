package br.usp.ime.academicdevoir.controller;

//import java.util.List;
import java.util.Collection;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Disciplina;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.Permission;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
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
	private TurmaDao turmaDao;
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
			TurmaDao turmaDao,
			UsuarioSession usuarioSession) {
		this.result = result;
		this.disciplinaDao = disciplinaDao;
		this.turmaDao = turmaDao;
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
	}

	/**
	 * Cadastra uma disciplina nova no sistema.
	 * 
	 * @param nova
	 */
	public void cadastra(final Disciplina nova) {
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
	}

	/**
	 * Remove uma disciplina do banco de dados com o id fornecido.
	 * 
	 * @param id
	 */
	public void remove(final Long id) {
		Disciplina disciplina;

		disciplina = disciplinaDao.carrega(id);
		
		Collection<Turma> listaDeTurmas = disciplina.getTurmas();
		
		for (Turma turma : listaDeTurmas)
			turmaDao.removeTurma(turma);
		
		disciplinaDao.removeDisciplina(disciplina);
		result.redirectTo(DisciplinasController.class).lista();
	}

}
