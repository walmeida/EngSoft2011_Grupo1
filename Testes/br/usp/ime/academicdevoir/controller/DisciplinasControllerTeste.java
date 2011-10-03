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

public class DisciplinasControllerTeste {

	private DisciplinasController disciplinasController;
	private MockResult result;
	private DisciplinaDao dao;
	private Disciplina disciplina;
	private List<Disciplina> disciplinas;

	@Before
	public void SetUp() {
		dao = mock(DisciplinaDao.class);
		result = spy(new MockResult());
		disciplinasController = new DisciplinasController(result, dao);

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
	
	public void testaRemove() {
		disciplinasController.remove(disciplina.getId());
		
		verify(dao).removeDisciplina(disciplina);
		verify(result).redirectTo(DisciplinasController.class);
	}
}
