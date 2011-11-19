package br.usp.ime.academicdevoir.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.dao.QuestaoDeVouFDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeVouF;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

public class QuestoesDeVouFControllerTeste {
	/**
	 * @uml.property  name="questoesDeVouFController"
	 * @uml.associationEnd  
	 */
	private QuestoesDeVouFController questoesDeVouFController;
	/**
	 * @uml.property  name="dao"
	 * @uml.associationEnd  
	 */
	private QuestaoDeVouFDao dao;
	/**
	 * @uml.property  name="result"
	 * @uml.associationEnd  
	 */
	private MockResult result;
	/**
	 * @uml.property  name="validator"
	 * @uml.associationEnd  
	 */
	private JSR303MockValidator validator;/**
	 * @uml.property  name="usuarioSession"
	 * @uml.associationEnd
	 */
	private UsuarioSession usuarioSession;

	@Before
	public void SetUp() {
		dao = mock(QuestaoDeVouFDao.class);
		result = spy(new MockResult());
		validator = spy(new JSR303MockValidator());
		questoesDeVouFController = new QuestoesDeVouFController(dao, result,
				validator, usuarioSession);
	}

	@Test
	public void testeAdiciona() {
		QuestaoDeVouF questao = new QuestaoDeVouF();
		questao.setId(0L);
		questoesDeVouFController.cadastra(questao);

		verify(validator).validate(questao);
		verify(validator).onErrorUsePageOf(QuestoesController.class);
		verify(dao).salva(questao);
		verify(result).redirectTo(questoesDeVouFController);
	}

	@Test
	public void testeAtualiza() {
		QuestaoDeVouF questao = new QuestaoDeVouF();

		questoesDeVouFController.altera(questao);

		verify(validator).validate(questao);
		verify(validator).onErrorUsePageOf(QuestoesDeVouFController.class);
		verify(dao).atualiza(questao);
		verify(result).redirectTo(questoesDeVouFController);
	}

	@Test
	public void testeRemove() {
		QuestaoDeVouF temp = new QuestaoDeVouF();
		when(dao.carrega(0L)).thenReturn(temp);
		questoesDeVouFController.remove(0L);

		verify(dao).remove(temp);
		verify(result).redirectTo(questoesDeVouFController);
	}
}
