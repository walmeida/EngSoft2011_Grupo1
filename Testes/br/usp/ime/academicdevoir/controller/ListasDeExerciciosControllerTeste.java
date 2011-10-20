package br.usp.ime.academicdevoir.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.validator.ValidationException;
import br.usp.ime.academicdevoir.dao.ListaDeExerciciosDao;
import br.usp.ime.academicdevoir.dao.ProfessorDao;
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.QuestaoDaLista;
import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.any;

import static org.junit.Assert.*;

public class ListasDeExerciciosControllerTeste {

	/**
	 * @uml.property  name="listasDeExerciciosController"
	 * @uml.associationEnd  
	 */
	private ListasDeExerciciosController listasDeExerciciosController;
	/**
	 * @uml.property  name="result"
	 * @uml.associationEnd  
	 */
	private MockResult result;
	/**
	 * @uml.property  name="dao"
	 * @uml.associationEnd  
	 */
	private ListaDeExerciciosDao dao;
	/**
	 * @uml.property  name="questaoDao"
	 * @uml.associationEnd  
	 */
	private QuestaoDao questaoDao;
	/**
	 * @uml.property  name="turmaDao"
	 * @uml.associationEnd  
	 */
	private TurmaDao turmaDao;
	/**
	 * @uml.property  name="validator"
	 * @uml.associationEnd  
	 */
	private JSR303MockValidator validator;
	/**
	 * @uml.property  name="listaDeExercicios"
	 * @uml.associationEnd  
	 */
	private ListaDeExercicios listaDeExercicios;
	/**
	 * @uml.property  name="prazoDeEntrega"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.lang.Integer"
	 */
	private List<Integer> prazoDeEntrega;
	/**
	 * @uml.property  name="prazoProvisorio"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.lang.Integer"
	 */
	private Calendar prazoProvisorio = Calendar.getInstance();
	/**
	 * @uml.property  name="questao"
	 * @uml.associationEnd  
	 */
	private QuestaoDeMultiplaEscolha questao;
	/**
	 * @uml.property  name="turma"
	 * @uml.associationEnd  
	 */
	private Turma turma;
	/**
	 * @uml.property  name="professorDao"
	 * @uml.associationEnd  readOnly="true"
	 */
	private ProfessorDao professorDao;
	/**
	 * @uml.property  name="usuarioLogado"
	 * @uml.associationEnd  readOnly="true"
	 */
	private UsuarioSession usuarioLogado;

	@Before
	public void SetUp() {
		result = spy(new MockResult());
		dao = mock(ListaDeExerciciosDao.class);
		questaoDao = mock(QuestaoDao.class);
		turmaDao = mock(TurmaDao.class);
		validator = spy(new JSR303MockValidator());

		listasDeExerciciosController = new ListasDeExerciciosController(result,
				dao, questaoDao, professorDao, turmaDao, validator, usuarioLogado);
		
		listaDeExercicios = new ListaDeExercicios();
		listaDeExercicios.setId(0L);
		
		Calendar prazoProvisorio = Calendar.getInstance();
		prazoProvisorio.setTimeInMillis(System.currentTimeMillis());

		prazoDeEntrega = new ArrayList<Integer>();
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.DAY_OF_MONTH));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.MONTH));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.YEAR));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.HOUR_OF_DAY));

		questao = new QuestaoDeMultiplaEscolha();
		questao.setId(0L);
		
		turma = new Turma();
		turma.setId(0L);

		when(dao.carrega(listaDeExercicios.getId())).thenReturn(
				listaDeExercicios);
		when(dao.listaTudo()).thenReturn(new ArrayList<ListaDeExercicios>());

		when(questaoDao.carrega(questao.getId())).thenReturn(questao);
		
		when(turmaDao.carrega(turma.getId())).thenReturn(turma);
	}

	private void prazoFuturo(List<Integer> prazoDeEntrega) {
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.MINUTE) + 1
				+ new Random().nextInt(100000));
	}

	private Date prazoFuturo() {
		Date prazoDeEntrega = new Date(System.currentTimeMillis() + 1000000L);
		return prazoDeEntrega;
	}

	private void prazoPassado(List<Integer> prazoDeEntrega) {
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.MINUTE)
				- new Random().nextInt(100000));
	}

	@Test
	public void testeCadastra() {
		prazoFuturo(prazoDeEntrega);

		listasDeExerciciosController.cadastra(listaDeExercicios,
				prazoDeEntrega, null);

		verify(validator).validate(listaDeExercicios);
		verify(validator).onErrorUsePageOf(listasDeExerciciosController);
		verify(dao).salva(listaDeExercicios);
		verify(result).redirectTo(listasDeExerciciosController);
	}

	@Test(expected = ValidationException.class)
	public void testeNaoDeveCadastrarQuestaoComPrazoPassado() {
		prazoPassado(prazoDeEntrega);

		listasDeExerciciosController.cadastra(listaDeExercicios,
				prazoDeEntrega, null);

		verify(validator).validate(listaDeExercicios);
		verify(validator).onErrorUsePageOf(listasDeExerciciosController);
	}

	@Test
	public void testeVerListaIncluiListaEDataEmResult() {
		Date prazoDeEntrega = prazoFuturo();
		listaDeExercicios.setPrazoDeEntrega(prazoDeEntrega);
		listasDeExerciciosController.verLista(listaDeExercicios.getId());
		ListaDeExercicios lista = result.included("listaDeExercicios");
		String prazo = result.included("prazo");
		assertNotNull(lista);
		assertNotNull(prazo);
	}

	@Test
	public void testeAlteracao() {
		Date prazoDeEntrega = prazoFuturo();
		listaDeExercicios.setPrazoDeEntrega(prazoDeEntrega);
		listasDeExerciciosController.alteracao(listaDeExercicios.getId());
		ListaDeExercicios lista = result.included("listaDeExercicios");
		List<Integer> prazo = result.included("prazo");
		assertNotNull(lista);
		assertNotNull(prazo);
	}

	@Test
	public void testeAltera() {
		prazoFuturo(prazoDeEntrega);
		listasDeExerciciosController.altera(listaDeExercicios, prazoDeEntrega);

		verify(validator).validate(listaDeExercicios);
		verify(validator).onErrorUsePageOf(ListasDeExerciciosController.class);
		verify(dao).atualiza(any(ListaDeExercicios.class));
		verify(result).redirectTo(listasDeExerciciosController);
	}

	@Test(expected = ValidationException.class)
	public void testeNaoDeveAlterarQuestaoComPrazoPassado() {
		prazoPassado(prazoDeEntrega);
		listasDeExerciciosController.altera(listaDeExercicios, prazoDeEntrega);

		verify(validator).validate(listaDeExercicios);
		verify(validator).onErrorUsePageOf(ListasDeExerciciosController.class);
		verify(dao).atualiza(any(ListaDeExercicios.class));
		verify(result).redirectTo(listasDeExerciciosController);
	}

	@Test
	public void testeRemove() {
		listasDeExerciciosController.remove(listaDeExercicios.getId());
		verify(dao).remove(listaDeExercicios);
		verify(result).redirectTo(listasDeExerciciosController);
	}

	@Test
	public void testeIncluiQuestao() {
		listaDeExercicios.setQuestoes(new ArrayList<QuestaoDaLista>());
		listasDeExerciciosController.incluiQuestao(listaDeExercicios,
				questao.getId());

		assertEquals(questao, listaDeExercicios.getQuestoes().get(0)
				.getQuestao());
		verify(dao).atualiza(listaDeExercicios);
		verify(result).redirectTo(listasDeExerciciosController);
	}

	@Test
	public void testeAlteraQuestao() {
		List<QuestaoDaLista> questoesDaLista = new ArrayList<QuestaoDaLista>();
		questoesDaLista.add(new QuestaoDaLista());
		listaDeExercicios.setQuestoes(questoesDaLista);
		listasDeExerciciosController.alteraQuestao(listaDeExercicios.getId(), 0, questao.getId());
		
		assertEquals(questao, listaDeExercicios.getQuestoes().get(0)
				.getQuestao());
		verify(dao).atualiza(listaDeExercicios);
		verify(result).redirectTo(listasDeExerciciosController);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testeRemoveQuestao() {
		QuestaoDaLista questao0 = new QuestaoDaLista();
		QuestaoDaLista questao1 = new QuestaoDaLista();
		
		List<QuestaoDaLista> questoesDaLista = new ArrayList<QuestaoDaLista>();
		questoesDaLista.add(questao0);
		questoesDaLista.add(questao1);
		
		listaDeExercicios.setQuestoes(questoesDaLista);
		int numeroInicialDeQuestoes = listaDeExercicios.getQuestoes().size();
		listasDeExerciciosController.removeQuestao(listaDeExercicios.getId(), 0);
		
		assertEquals(questao1, listaDeExercicios.getQuestoes().get(0));
		listaDeExercicios.getQuestoes().get(numeroInicialDeQuestoes);
		
		verify(dao).atualiza(listaDeExercicios);
		verify(result).redirectTo(listasDeExerciciosController);
	}
	
	@Test
	public void testeIncluiTurma() {
		listaDeExercicios.setTurmas(new ArrayList<Turma>());
		listasDeExerciciosController.incluiTurma(listaDeExercicios.getId(), turma.getId());
		
		assertEquals(turma, listaDeExercicios.getTurmas().get(0));
		
		verify(dao).atualiza(listaDeExercicios);
		verify(result).redirectTo(listasDeExerciciosController);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testeRemoveTurma() {
		Turma turma0 = new Turma();
		Turma turma1 = new Turma();
		
		List<Turma> turmas = new ArrayList<Turma>();
		turmas.add(turma0);
		turmas.add(turma1);
		
		listaDeExercicios.setTurmas(turmas);
		int numeroInicialDeTurmas = listaDeExercicios.getTurmas().size();
		listasDeExerciciosController.removeTurma(listaDeExercicios, 0);
		
		assertEquals(turma1, listaDeExercicios.getTurmas().get(0));
		listaDeExercicios.getTurmas().get(numeroInicialDeTurmas);
		
		verify(dao).atualiza(listaDeExercicios);
		verify(result).redirectTo(listasDeExerciciosController);
	}
}
