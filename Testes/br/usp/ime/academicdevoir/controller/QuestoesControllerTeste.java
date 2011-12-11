package br.usp.ime.academicdevoir.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import sun.rmi.transport.LiveRef;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.any;

import static org.junit.Assert.*;

import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.dao.ListaDeExerciciosDao;
import br.usp.ime.academicdevoir.dao.ListaDeRespostasDao;
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.TagDao;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;
import br.usp.ime.academicdevoir.entidade.QuestaoDeSubmissaoDeArquivo;
import br.usp.ime.academicdevoir.entidade.QuestaoDeTexto;
import br.usp.ime.academicdevoir.entidade.QuestaoDeVouF;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

public class QuestoesControllerTeste {

	/**
	 * @uml.property  name="questoesController"
	 * @uml.associationEnd  
	 */
	private QuestoesController questoesController;
	/**
	 * @uml.property  name="dao"
	 * @uml.associationEnd  
	 */
	private QuestaoDao dao;
	/**
	 * @uml.property  name="result"
	 * @uml.associationEnd  
	 */
	private MockResult result;
	/**
	 * @uml.property  name="questaoDeMultiplaEscolha"
	 * @uml.associationEnd  
	 */
	private QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha;
	/**
	 * @uml.property  name="questaoDeSubmissaoDeArquivo"
	 * @uml.associationEnd  
	 */
	private QuestaoDeSubmissaoDeArquivo questaoDeSubmissaoDeArquivo;
	/**
	 * @uml.property  name="questaoDeTexto"
	 * @uml.associationEnd  
	 */
	private QuestaoDeTexto questaoDeTexto;
	/**
	 * @uml.property  name="questaoDeVouF"
	 * @uml.associationEnd  
	 */
	private QuestaoDeVouF questaoDeVouF;
	/**
	 * @uml.property  name="usuarioSession"
	 * @uml.associationEnd
	 */
	private UsuarioSession usuarioSession;
	
	private TagDao tagDao;
	
	private ListaDeExerciciosDao listaDeExerciciosDao;
	
	private ListaDeRespostasDao listaDeRespostasDao;
	
	@Before
	public void SetUp() {		
		Professor professor = new Professor();
		professor.setPrivilegio(Privilegio.ADMINISTRADOR);
		
		usuarioSession = new UsuarioSession();
		usuarioSession.setUsuario(professor);

		result = spy(new MockResult());
		dao = mock(QuestaoDao.class);
		listaDeExerciciosDao = mock(ListaDeExerciciosDao.class);

		questoesController = new QuestoesController(dao, tagDao, 
		        listaDeExerciciosDao, listaDeRespostasDao, result, usuarioSession);

		questaoDeMultiplaEscolha = new QuestaoDeMultiplaEscolha();
		questaoDeSubmissaoDeArquivo = new QuestaoDeSubmissaoDeArquivo();
		questaoDeTexto = new QuestaoDeTexto();
		questaoDeVouF = new QuestaoDeVouF();

		questaoDeMultiplaEscolha.setId(0L);
		questaoDeSubmissaoDeArquivo.setId(1L);
		questaoDeTexto.setId(2L);
		questaoDeVouF.setId(3L);

		when(dao.listaTudo()).thenReturn(new ArrayList<Questao>());
		
		when(dao.carrega(questaoDeMultiplaEscolha.getId().longValue())).thenReturn(questaoDeMultiplaEscolha);
		when(dao.carrega(questaoDeSubmissaoDeArquivo.getId().longValue())).thenReturn(questaoDeSubmissaoDeArquivo);
		when(dao.carrega(questaoDeTexto.getId().longValue())).thenReturn(questaoDeTexto);
		when(dao.carrega(questaoDeVouF.getId().longValue())).thenReturn(questaoDeVouF);
		
		when(listaDeExerciciosDao.buscaListasQueContemQuestao(any(Long.class))).thenReturn(new ArrayList<BigInteger>());
	}

	@Test
	public void testeAlteracaoQuestaoDeMultiplaEscolha() {
		questoesController.alteracao(questaoDeMultiplaEscolha.getId());
		verify(result).redirectTo(QuestoesDeMultiplaEscolhaController.class);
	}

	@Test
	public void testeAlteracaoQuestaoDeSubmissaoDeArquivo() {
		questoesController.alteracao(questaoDeSubmissaoDeArquivo.getId());
		verify(result).redirectTo(QuestoesDeSubmissaoDeArquivoController.class);
	}

	@Test
	public void testeAlteracaoQuestaoDeTexto() {
		questoesController.alteracao(questaoDeTexto.getId());
		verify(result).redirectTo(QuestoesDeTextoController.class);
	}

	@Test
	public void testeAlteracaoQuestaoDeVouF() {
		questoesController.alteracao(questaoDeVouF.getId());
		verify(result).redirectTo(QuestoesDeVouFController.class);
	}

	@Test
	public void testeRemove() {
		questoesController.remove(new Random().nextLong() % 4);
		verify(result).redirectTo(QuestoesController.class);
	}

	@Test
	public void testeLista() {
		questoesController.lista();
		List<Questao> questoes = result.included("lista");
		assertNotNull(questoes);
	}
}
