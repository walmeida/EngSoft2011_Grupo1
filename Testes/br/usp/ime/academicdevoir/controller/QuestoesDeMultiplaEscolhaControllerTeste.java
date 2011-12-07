package br.usp.ime.academicdevoir.controller;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;

import br.usp.ime.academicdevoir.controller.QuestoesDeMultiplaEscolhaController;
import br.usp.ime.academicdevoir.dao.QuestaoDeMultiplaEscolhaDao;
import br.usp.ime.academicdevoir.dao.TagDao;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;
import br.usp.ime.academicdevoir.entidade.Tag;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

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
	/**
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

		dao = mock(QuestaoDeMultiplaEscolhaDao.class);
		tagDao = mock(TagDao.class);
		result = spy(new MockResult());
		validator = spy(new JSR303MockValidator());
		questoesC = new QuestoesDeMultiplaEscolhaController(dao, tagDao, result,
				validator, usuarioSession);
		
		when(tagDao.buscaPeloNome(any(String.class))).thenReturn(new Tag("tagQualquer"));
	}

	@Test
	public void testeAdiciona() {
		QuestaoDeMultiplaEscolha questao = new QuestaoDeMultiplaEscolha();
		questao.setId(0L);
		questao.setRespostaUnica(true);
		questoesC.cadastra(questao, null, new String("tagQualquer"));

		verify(validator).validate(questao);
		verify(validator).onErrorUsePageOf(QuestoesController.class);
		verify(dao).salva(questao);
		verify(result).redirectTo(questoesC);
	}

	@Test
	public void testeAltera() {
		QuestaoDeMultiplaEscolha questao = new QuestaoDeMultiplaEscolha();
		questao.setRespostaUnica(true);

		questoesC.altera(questao, null, new String("tagQualquer"));

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
