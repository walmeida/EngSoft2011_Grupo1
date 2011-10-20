package br.usp.ime.academicdevoir.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.dao.AlunoDao;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Disciplina;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.Turma;

public class TurmasControllerTeste {
	
	/**
	 * @uml.property  name="turmasController"
	 * @uml.associationEnd  readOnly="true"
	 */
	TurmasController turmasController;
	/**
	 * @uml.property  name="turmaDao"
	 * @uml.associationEnd  readOnly="true"
	 */
	TurmaDao turmaDao;
	/**
	 * @uml.property  name="disciplinaDao"
	 * @uml.associationEnd  readOnly="true"
	 */
	DisciplinaDao disciplinaDao;
	/**
	 * @uml.property  name="alunoDao"
	 * @uml.associationEnd  readOnly="true"
	 */
	AlunoDao alunoDao;
	/**
	 * @uml.property  name="result"
	 * @uml.associationEnd  readOnly="true"
	 */
	MockResult result;
	/**
	 * @uml.property  name="turma"
	 * @uml.associationEnd  readOnly="true"
	 */
	private Turma turma;
	/**
	 * @uml.property  name="disciplinas"
	 */
	private List<Disciplina> disciplinas;
	/**
	 * @uml.property  name="turmas"
	 */
	private List<Turma> turmas;
	
	@Before
	public void SetUp() {
		turmaDao = mock(TurmaDao.class);
		disciplinaDao = mock(DisciplinaDao.class);
		alunoDao = mock(AlunoDao.class);
		result = spy(new MockResult());
		
		turmasController = new TurmasController(result, turmaDao, disciplinaDao, alunoDao);
		
		turma = new Turma();
		turma.setId(0L);
		
		turmas = new ArrayList<Turma>();
		disciplinas = new ArrayList<Disciplina>();
		
		when(turmaDao.carrega(turma.getId())).thenReturn(turma);
		when(turmaDao.listaTudo()).thenReturn(turmas);
		// FIXME when(turmaDao.buscaDisciplinas()).thenReturn(disciplinas);
	}
	
	@Test
	public void testeHome() {
		turmasController.home(turma.getId());
		Turma turma = result.included("turma");
		
		assertNotNull(turma);
	}
	
	// TODO @Test
	/*public void testeListaTurmasDoProfessor() {
		Professor professor = new Professor();
		turmasController.listaTurmasDoProfessor(professor);
		List<Turma> turmas = result.included("listaDeTurmas");
		
		assertNotNull(turmas);
		verify(result).redirectTo(TurmasController.class);		
	}*/
	
	@Test
	public void testeLista() {
		turmasController.lista();
		List<Turma> turmas = result.included("listaDeTurmas");
		
		assertNotNull(turmas);
	}
	
	@Test
	public void testCadastro() {
		turmasController.cadastro();
		List<Disciplina> disciplinas = result.included("listaDeDisciplinas");
		
		assertNotNull(disciplinas);
	}
	
	@Test
	public void testeCadastra() {
		turmasController.cadastra(turma);
		
		verify(result).redirectTo(TurmasController.class);
	}
	
	@Test
	public void testeAlteracao() {
		turmasController.alteracao(turma.getId());
		Turma turma = result.included("turma");
		
		assertNotNull(turma);
	}
	
	@Test
	public void testeAltera() {
		turmasController.altera(turma.getId(), "xpto");
		
		verify(turmaDao).atualizaTurma(turma);
		verify(result).redirectTo(TurmasController.class);
	}
}
