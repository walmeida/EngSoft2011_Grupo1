package br.usp.ime.academicdevoir.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import static org.junit.Assert.*;

import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeMultiplaEscolhaDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeSubmissaoDeArquivoDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeTextoDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeVouFDao;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;
import br.usp.ime.academicdevoir.entidade.QuestaoDeSubmissaoDeArquivo;
import br.usp.ime.academicdevoir.entidade.QuestaoDeTexto;
import br.usp.ime.academicdevoir.entidade.QuestaoDeVouF;
import br.usp.ime.academicdevoir.infra.TipoDeQuestao;

public class QuestoesControllerTeste {

	private QuestoesController questoesController;
	private QuestaoDao dao;
	private QuestaoDeMultiplaEscolhaDao questaoDeMultiplaEscolhaDao;
	private QuestaoDeSubmissaoDeArquivoDao questaoDeSubmissaoDeArquivoDao;
	private QuestaoDeTextoDao questaoDeTextoDao;
	private QuestaoDeVouFDao questaoDeVouFDao;
	private MockResult result;
	private QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha;
	private QuestaoDeSubmissaoDeArquivo questaoDeSubmissaoDeArquivo;
	private QuestaoDeTexto questaoDeTexto;
	private QuestaoDeVouF questaoDeVouF;

	@Before
	public void SetUp() {
		result = spy(new MockResult());
		dao = mock(QuestaoDao.class);
		questaoDeMultiplaEscolhaDao = mock(QuestaoDeMultiplaEscolhaDao.class);
		questaoDeSubmissaoDeArquivoDao = mock(QuestaoDeSubmissaoDeArquivoDao.class);
		questaoDeTextoDao = mock(QuestaoDeTextoDao.class);
		questaoDeVouFDao = mock(QuestaoDeVouFDao.class);

		questoesController = new QuestoesController(result, dao,
				questaoDeMultiplaEscolhaDao, questaoDeSubmissaoDeArquivoDao,
				questaoDeTextoDao, questaoDeVouFDao);
		questaoDeMultiplaEscolha = new QuestaoDeMultiplaEscolha();
		questaoDeSubmissaoDeArquivo = new QuestaoDeSubmissaoDeArquivo();
		questaoDeTexto = new QuestaoDeTexto();
		questaoDeVouF = new QuestaoDeVouF();

		questaoDeMultiplaEscolha.setId(0L);
		questaoDeSubmissaoDeArquivo.setId(1L);
		questaoDeTexto.setId(2L);
		questaoDeVouF.setId(3L);

		when(dao.listaTudo()).thenReturn(new ArrayList<Questao>());
		when(
				questaoDeMultiplaEscolhaDao.buscaPorId(questaoDeMultiplaEscolha
						.getId())).thenReturn(questaoDeMultiplaEscolha);
		when(
				questaoDeSubmissaoDeArquivoDao
						.buscaPorId(questaoDeSubmissaoDeArquivo.getId()))
				.thenReturn(questaoDeSubmissaoDeArquivo);
		when(questaoDeTextoDao.buscaPorId(questaoDeTexto.getId())).thenReturn(
				questaoDeTexto);
		when(questaoDeVouFDao.buscaPorId(questaoDeVouF.getId())).thenReturn(
				questaoDeVouF);
	}

	@Test
	public void testeDeveRetornarMULTIPLAESCOLHA() {
		TipoDeQuestao tipoDeQuestao = questoesController
				.getTipoDeQuestao(questaoDeMultiplaEscolha.getId());
		assertEquals(TipoDeQuestao.MULTIPLAESCOLHA, tipoDeQuestao);
	}

	@Test
	public void testeDeveRetornarTipoDeQuestaoSUBMISSAODEARQUIVO() {
		TipoDeQuestao tipoDeQuestao = questoesController
				.getTipoDeQuestao(questaoDeSubmissaoDeArquivo.getId());
		assertEquals(TipoDeQuestao.SUBMISSAODEARQUIVO, tipoDeQuestao);
	}

	@Test
	public void testeDeveRetornarTipoDeQuestaoTEXTO() {
		TipoDeQuestao tipoDeQuestao = questoesController
				.getTipoDeQuestao(questaoDeTexto.getId());
		assertEquals(TipoDeQuestao.TEXTO, tipoDeQuestao);
	}

	@Test
	public void testeDeveRetornarTipoDeQuestaoVOUF() {
		TipoDeQuestao tipoDeQuestao = questoesController
				.getTipoDeQuestao(questaoDeVouF.getId());
		assertEquals(TipoDeQuestao.VOUF, tipoDeQuestao);
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
