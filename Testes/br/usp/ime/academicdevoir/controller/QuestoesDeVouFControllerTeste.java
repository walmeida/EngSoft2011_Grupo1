package br.usp.ime.academicdevoir.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.dao.QuestaoDeVouFDao;
import br.usp.ime.academicdevoir.dao.TagDao;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.QuestaoDeVouF;
import br.usp.ime.academicdevoir.entidade.Tag;
import br.usp.ime.academicdevoir.infra.Privilegio;
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
	private TagDao tagDao;

	@Before
	public void SetUp() {		
		Professor professor = new Professor();
		professor.setPrivilegio(Privilegio.ADMINISTRADOR);
		
		usuarioSession = new UsuarioSession();
		usuarioSession.setUsuario(professor);

		dao = mock(QuestaoDeVouFDao.class);
		tagDao = mock(TagDao.class);
		result = spy(new MockResult());
		validator = spy(new JSR303MockValidator());
		questoesDeVouFController = new QuestoesDeVouFController(dao, tagDao, result,
				validator, usuarioSession);
        
		when(tagDao.buscaPeloNome(any(String.class))).thenReturn(new Tag("tagQualquer"));
	}

	@Test
	public void testeAdiciona() {
		QuestaoDeVouF questao = new QuestaoDeVouF();
		questao.setId(0L);
		questoesDeVouFController.cadastra(questao, new String("tagQualquer"));

		verify(validator).validate(questao);
		verify(validator).onErrorUsePageOf(QuestoesController.class);
		verify(dao).salva(questao);
		verify(result).redirectTo(questoesDeVouFController);
	}

	@Test
	public void testeAtualiza() {
		QuestaoDeVouF questao = new QuestaoDeVouF();

		questoesDeVouFController.altera(questao, new String("tagQualquer"));

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
