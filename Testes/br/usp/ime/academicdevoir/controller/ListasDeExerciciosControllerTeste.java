package br.usp.ime.academicdevoir.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import br.com.caelum.vraptor.validator.ValidationException;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.dao.ListaDeExerciciosDao;
import br.usp.ime.academicdevoir.dao.ListaDeRespostasDao;
import br.usp.ime.academicdevoir.dao.ProfessorDao;
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.PropriedadesDaListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.QuestaoDaLista;
import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

public class ListasDeExerciciosControllerTeste {

	/**
	 * @uml.property  name="listasDeExerciciosController"
	 * @uml.associationEnd  
	 */
	//@SuppressWarnings("unused")
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
	 * @uml.property  name="dao"
	 * @uml.associationEnd  
	 */
	private ListaDeRespostasDao respostasDao;
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
	
	private PropriedadesDaListaDeExercicios propriedadesDaListaDeExercicios;
	
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
	private UsuarioSession usuarioSession;
	private ArrayList<Turma> turmas;
	
	@Before
	public void SetUp() {		
		Professor professor = new Professor();
		professor.setId(0L);
		professor.setPrivilegio(Privilegio.ADMINISTRADOR);
		
		usuarioSession = new UsuarioSession();
		usuarioSession.setUsuario(professor);

		result = spy(new MockResult());
		dao = mock(ListaDeExerciciosDao.class);
		respostasDao = mock(ListaDeRespostasDao.class);
		questaoDao = mock(QuestaoDao.class);
		professorDao = mock(ProfessorDao.class);
		turmaDao = mock(TurmaDao.class);
		validator = spy(new JSR303MockValidator());

		listasDeExerciciosController = new ListasDeExerciciosController(result,
				dao, respostasDao, questaoDao, professorDao, turmaDao, validator,
				usuarioSession);
		
		listaDeExercicios = new ListaDeExercicios();
		listaDeExercicios.setId(0L);
		
		propriedadesDaListaDeExercicios = new PropriedadesDaListaDeExercicios();
		
		Calendar prazoProvisorio = Calendar.getInstance();
		prazoProvisorio.setTimeInMillis(System.currentTimeMillis());

		prazoDeEntrega = new ArrayList<Integer>();
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.DAY_OF_MONTH));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.MONTH) + 1);
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.YEAR));
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.HOUR_OF_DAY));
		
		questao = new QuestaoDeMultiplaEscolha();
		questao.setId(0L);
		
		turma = new Turma();
		turma.setId(0L);
		
		turmas = new ArrayList<Turma>();
		turmas.add(turma);
		professor.setTurmas(turmas);

		when(dao.carrega(listaDeExercicios.getId())).thenReturn(
				listaDeExercicios);
		when(dao.listaTudo()).thenReturn(new ArrayList<ListaDeExercicios>());

		when(questaoDao.carrega(questao.getId())).thenReturn(questao);
		
		when(professorDao.carrega(professor.getId())).thenReturn(professor);
		when(turmaDao.carrega(turma.getId())).thenReturn(turma);
	}

	private void prazoFuturo(List<Integer> prazoDeEntrega) {
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.MINUTE) + 1 + new Random().nextInt(100000));
	}

	private Date prazoFuturo() {
		Date prazoDeEntrega = new Date(System.currentTimeMillis() + 1L + (long) (new Random().nextInt(1000000)) );
		return prazoDeEntrega;
	}

	private void prazoPassado(List<Integer> prazoDeEntrega) {
		prazoDeEntrega.add(prazoProvisorio.get(Calendar.MINUTE)
				- new Random().nextInt(100000));
	}

	@Test
	public void testeCadastra() {
		prazoFuturo(prazoDeEntrega);

		listasDeExerciciosController.cadastra(propriedadesDaListaDeExercicios,
				prazoDeEntrega, turma.getId());

		verify(validator).validate(propriedadesDaListaDeExercicios);
		verify(validator).onErrorUsePageOf(listasDeExerciciosController);
		verify(dao).salva(any(ListaDeExercicios.class));
		verify(result).redirectTo(listasDeExerciciosController);
	}

	@Test(expected = ValidationException.class)
	public void testeNaoDeveCadastrarQuestaoComPrazoPassado() {
		prazoPassado(prazoDeEntrega);

		listasDeExerciciosController.cadastra(propriedadesDaListaDeExercicios,
				prazoDeEntrega, turma.getId());

		verify(validator).validate(propriedadesDaListaDeExercicios);
		verify(validator).onErrorUsePageOf(listasDeExerciciosController);
	}

	@Test
	public void testeVerListaIncluiListaEDataEmResult() {
		Date prazoDeEntrega = prazoFuturo();
		propriedadesDaListaDeExercicios.setPrazoDeEntrega(prazoDeEntrega);
		listaDeExercicios.setPropriedades(propriedadesDaListaDeExercicios);
		listasDeExerciciosController.verLista(listaDeExercicios.getId());
		
		ListaDeExercicios lista = result.included("listaDeExercicios");
		String prazo = result.included("prazo");
		List<Turma> turmas = result.included("turmasDoProfessor");
		
		assertNotNull(lista);
		assertNotNull(prazo);
		assertNotNull(turmas);
	}

	@Test
	public void testeAlteracao() {
		Date prazoDeEntrega = prazoFuturo();
		propriedadesDaListaDeExercicios.setPrazoDeEntrega(prazoDeEntrega);
		listaDeExercicios.setPropriedades(propriedadesDaListaDeExercicios);		
		listasDeExerciciosController.alteracao(listaDeExercicios.getId());
		
		ListaDeExercicios lista = result.included("listaDeExercicios");
		List<Integer> prazo = result.included("prazo");
		
		assertNotNull(lista);
		assertNotNull(prazo);
	}

	@Test
	public void testeAltera() {
		prazoFuturo(prazoDeEntrega);
		listasDeExerciciosController.altera(listaDeExercicios,propriedadesDaListaDeExercicios, prazoDeEntrega);

		verify(validator).validate(listaDeExercicios);
		verify(validator).onErrorUsePageOf(ListasDeExerciciosController.class);
		verify(dao).atualiza(listaDeExercicios);
		verify(result).redirectTo(listasDeExerciciosController);
	}

	@Test(expected = ValidationException.class)
	public void testeNaoDeveAlterarQuestaoComPrazoPassado() {
		prazoPassado(prazoDeEntrega);
		listasDeExerciciosController.altera(listaDeExercicios,propriedadesDaListaDeExercicios, prazoDeEntrega);

		verify(validator).validate(listaDeExercicios);
		verify(validator).onErrorUsePageOf(ListasDeExerciciosController.class);
		verify(dao).atualiza(listaDeExercicios);
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
				questao.getId(), 1);

		assertEquals(questao, listaDeExercicios.getQuestoes().get(0)
				.getQuestao());
		verify(dao).atualiza(listaDeExercicios);
		verify(result).redirectTo(listasDeExerciciosController);
	}

//	@Test
//	public void testeTrocaQuestao() {
//		List<QuestaoDaLista> questoesDaLista = new ArrayList<QuestaoDaLista>();
//		questoesDaLista.add(new QuestaoDaLista());
//		listaDeExercicios.setQuestoes(questoesDaLista);
//		listasDeExerciciosController.trocaQuestao(listaDeExercicios.getId(), 0, questao.getId(), 0);
//		
//		assertEquals(questao, listaDeExercicios.getQuestoes().get(0)
//				.getQuestao());
//		verify(dao).atualiza(listaDeExercicios);
//		verify(result).redirectTo(listasDeExerciciosController);
//	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testeRemoveQuestao() {
		QuestaoDaLista questao0 = new QuestaoDaLista();
		questao0.setOrdem(1);
		QuestaoDaLista questao1 = new QuestaoDaLista();
		questao1.setOrdem(2);
		
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
	public void testeCadastro() {
		listasDeExerciciosController.cadastro();
		List<Turma> turmas = result.included("turmasDoProfessor");
		assertNotNull(turmas);
	}
	
	@Test
	public void testeLista() {
		listasDeExerciciosController.lista();
		List<ListaDeExercicios> listaDeListas = result.included("listaDeListas");
		assertNotNull(listaDeListas);
	}
	
	@Test
	public void testeListasTurma() {
		listasDeExerciciosController.listasTurma(this.turma.getId());
		List<ListaDeExercicios> listaDeListas = result.included("listaDeListas");
		assertNotNull(listaDeListas);
	}
	
	//TODO Teste para Autocorreção
}
