package br.usp.ime.academicdevoir.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.entidade.Disciplina;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

public class DisciplinasControllerTeste {

	/**
	 * @uml.property name="disciplinasController"
	 * @uml.associationEnd
	 */
	private DisciplinasController disciplinasController;
	/**
	 * @uml.property name="result"
	 * @uml.associationEnd
	 */
	private MockResult result;
	/**
	 * @uml.property name="dao"
	 * @uml.associationEnd
	 */
	private DisciplinaDao dao;
	/**
	 * @uml.property name="disciplina"
	 * @uml.associationEnd
	 */
	private Disciplina disciplina;
	/**
	 * @uml.property name="disciplinas"
	 */
	private List<Disciplina> disciplinas;
	/**
	 * @uml.property name="usuarioSession"
	 * @uml.associationEnd readOnly="true"
	 */
	private UsuarioSession usuarioSession;

	@Before
	public void SetUp() {
		Professor professor = new Professor();
		professor.setId(0L);
		professor.setPrivilegio(Privilegio.ADMINISTRADOR);

		usuarioSession = new UsuarioSession();
		usuarioSession.setUsuario(professor);

		dao = mock(DisciplinaDao.class);
		result = spy(new MockResult());
		disciplinasController = new DisciplinasController(result, dao,
				usuarioSession);

		disciplina = new Disciplina();
		disciplina.setId(0L);

		disciplinas = new ArrayList<Disciplina>();

		when(dao.carrega(disciplina.getId())).thenReturn(disciplina);
		when(dao.listaTudo()).thenReturn(disciplinas);
	}

	@Test
	public void testeLista() {
		disciplinasController.lista();
		List<Disciplina> disciplinas = result.included("lista");

		assertNotNull(disciplinas);
	}

	@Test
	public void testeCadastra() {
		disciplinasController.cadastra(disciplina);

		verify(dao).salvaDisciplina(disciplina);
		verify(result).redirectTo(DisciplinasController.class);
	}

	@Test
	public void testeAltera() {
		disciplinasController.altera(disciplina.getId(), "xpto");

		verify(dao).atualizaDisciplina(disciplina);
		verify(result).redirectTo(DisciplinasController.class);
	}

	@Test
	public void testeAlteracao() {
		disciplinasController.alteracao(this.disciplina.getId());

		Disciplina disciplina = result.included("disciplina");
		assertNotNull(disciplina);
	}

	public void testaRemove() {
		disciplinasController.remove(disciplina.getId());

		verify(dao).removeDisciplina(disciplina);
		verify(result).redirectTo(DisciplinasController.class);
	}
}
