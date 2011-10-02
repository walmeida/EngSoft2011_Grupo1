package br.usp.ime.academicdevoir.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.validator.ValidationException;
import br.usp.ime.academicdevoir.dao.ListaDeExerciciosDao;
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import static org.junit.Assert.*;

public class ListasDeExerciciosControllerTeste {

	private ListasDeExerciciosController listasDeExerciciosController;
	private MockResult result;
	private ListaDeExerciciosDao dao;
	private QuestaoDao questaoDao;
	private TurmaDao turmaDao;
	private JSR303MockValidator validator;
	private ListaDeExercicios listaDeExercicios;
	private List<Integer> prazoDeEntrega;
	private Calendar prazoProvisorio = Calendar.getInstance();

	@Before
	public void SetUp() {
		result = spy(new MockResult());
		dao = mock(ListaDeExerciciosDao.class);
		questaoDao = mock(QuestaoDao.class);
		turmaDao = mock(TurmaDao.class);
		validator = spy(new JSR303MockValidator());

		listasDeExerciciosController = new ListasDeExerciciosController(result,
				dao, questaoDao, turmaDao, validator);
		listaDeExercicios = new ListaDeExercicios();

		listaDeExercicios.setId(0L);
		Calendar prazoProvisorio = Calendar.getInstance();
		prazoProvisorio.setTimeInMillis(System.currentTimeMillis());
		prazoDeEntrega = new ArrayList<Integer>();

		when(dao.carrega(listaDeExercicios.getId())).thenReturn(
				listaDeExercicios);
		when(dao.listaTudo()).thenReturn(new ArrayList<ListaDeExercicios>());
	}

	@Test
	public void testeCadastra() {
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.DAY_OF_MONTH));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.MONTH));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.YEAR));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.HOUR_OF_DAY));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.MINUTE) + 1
				+ new Random().nextInt(1000));

		listasDeExerciciosController.cadastra(listaDeExercicios,
				prazoDeEntrega, null);

		verify(validator).validate(listaDeExercicios);
		verify(validator).onErrorUsePageOf(listasDeExerciciosController);
		verify(dao).salva(listaDeExercicios);
		verify(result).redirectTo(listasDeExerciciosController);
	}

	@Test(expected = ValidationException.class)
	public void testeNaoDeveCadastrarQuestaoComPrazoPassado() {
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.DAY_OF_MONTH));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.MONTH));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.YEAR));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.HOUR_OF_DAY));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.MINUTE)
				- new Random().nextInt(1000));

		listasDeExerciciosController.cadastra(listaDeExercicios,
				prazoDeEntrega, null);

		verify(validator).validate(listaDeExercicios);
		verify(validator).onErrorUsePageOf(listasDeExerciciosController);
	}

	@Test
	public void testeVerListaIncluiListaEDataEmResult() {
		Date prazoDeEntrega = new Date(System.currentTimeMillis() + 1000000L);
		listaDeExercicios.setPrazoDeEntrega(prazoDeEntrega);
		listasDeExerciciosController.verLista(listaDeExercicios.getId());
		ListaDeExercicios lista = result.included("listaDeExercicios");
		String prazo = result.included("prazo");
		assertNotNull(lista);
		assertNotNull(prazo);
	}
	
	@Test
	public void testeAlteracao() {		
		Date prazoDeEntrega = new Date(System.currentTimeMillis() + 1000000L);
		listaDeExercicios.setPrazoDeEntrega(prazoDeEntrega);
		listasDeExerciciosController.alteracao(listaDeExercicios.getId());
		ListaDeExercicios lista = result.included("listaDeExercicios");
		List<Integer> prazo = result.included("prazo");
		assertNotNull(lista);
		assertNotNull(prazo);
	}
	
	
}
