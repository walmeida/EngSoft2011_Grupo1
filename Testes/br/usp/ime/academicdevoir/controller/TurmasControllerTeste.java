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
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Disciplina;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.Turma;

public class TurmasControllerTeste {
	
	TurmasController turmasController;
	TurmaDao dao;
	MockResult result;
	private Turma turma;
	private List<Disciplina> disciplinas;
	private List<Turma> turmas;
	
	@Before
	public void SetUp() {
		dao = mock(TurmaDao.class);
		result = spy(new MockResult());
		
		turmasController = new TurmasController(result, dao);
		
		turma = new Turma();
		turma.setId(0L);
		
		turmas = new ArrayList<Turma>();
		disciplinas = new ArrayList<Disciplina>();
		
		when(dao.carrega(turma.getId())).thenReturn(turma);
		when(dao.listaTudo()).thenReturn(turmas);
		when(dao.buscaDisciplinas()).thenReturn(disciplinas);
	}
	
	@Test
	public void testeHome() {
		turmasController.home(turma.getId());
		Turma turma = result.included("turma");
		
		assertNotNull(turma);
	}
	
	@Test
	public void testeListaTurmasDoProfessor() {
		Professor professor = new Professor();
		turmasController.listaTurmasDoProfessor(professor);
		List<Turma> turmas = result.included("listaDeTurmas");
		
		assertNotNull(turmas);
		verify(result).redirectTo(TurmasController.class);		
	}
	
	@Test
	public void testeListaTodasTurmas() {
		turmasController.listaTodasTurmas();
		List<Turma> turmas = result.included("listaDeTurmas");
		
		assertNotNull(turmas);
		verify(result).redirectTo(TurmasController.class);	
	}
	
	@Test
	public void testCadastro() {
		turmasController.cadastro();
		List<Disciplina> disciplinas = result.included("listaDeDisciplinas");
		
		assertNotNull(disciplinas);
	}
	
	@Test
	public void testeCadastra() {
		turmasController.cadastra(turma, 0L, 0L);
		
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
		
		verify(dao).atualizaTurma(turma);
		verify(result).redirectTo(TurmasController.class);
	}
}
