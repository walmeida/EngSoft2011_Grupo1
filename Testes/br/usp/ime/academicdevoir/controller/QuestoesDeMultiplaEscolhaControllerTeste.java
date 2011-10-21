package br.usp.ime.academicdevoir.controller;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;

import br.usp.ime.academicdevoir.controller.QuestoesDeMultiplaEscolhaController;
import br.usp.ime.academicdevoir.dao.QuestaoDeMultiplaEscolhaDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;

public class QuestoesDeMultiplaEscolhaControllerTeste {
	/**
	 * @uml.property  name="questoesC"
	 * @uml.associationEnd  
	 */
	private QuestoesDeMultiplaEscolhaController questoesC;
	/**
	 * @uml.property  name="dao"
	 * @uml.associationEnd  
	 */
	private QuestaoDeMultiplaEscolhaDao dao;
	/**
	 * @uml.property  name="result"
	 * @uml.associationEnd  
	 */
	private MockResult result;
	/**
	 * @uml.property  name="validator"
	 * @uml.associationEnd  
	 */
	private JSR303MockValidator validator;

	@Before
	public void SetUp() {
		dao = mock(QuestaoDeMultiplaEscolhaDao.class);
		result = spy(new MockResult());
		validator = spy(new JSR303MockValidator());
		questoesC = new QuestoesDeMultiplaEscolhaController(dao, result,
				validator);
	}

	@Test
	public void testeAdiciona() {
		QuestaoDeMultiplaEscolha questao = new QuestaoDeMultiplaEscolha();
		questao.setId(0L);
		questoesC.cadastra(questao);

		verify(validator).validate(questao);
		verify(validator).onErrorUsePageOf(QuestoesController.class);
		verify(dao).salva(questao);
		verify(result).redirectTo(questoesC);
	}

	@Test
	public void testeAtualiza() {
		QuestaoDeMultiplaEscolha questao = new QuestaoDeMultiplaEscolha();

		questoesC.altera(questao);

		verify(validator).validate(questao);
		verify(validator).onErrorUsePageOf(questoesC);
		verify(dao).atualiza(questao);
		verify(result).redirectTo(questoesC);
	}

	@Test
	public void testeRemove() {
		QuestaoDeMultiplaEscolha temp = new QuestaoDeMultiplaEscolha();
		when(dao.carrega(0L)).thenReturn(temp);
		questoesC.remove(0L);

		verify(dao).remove(temp);
		verify(result).redirectTo(questoesC);
	}
}
