package br.usp.ime.academicdevoir.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.dao.QuestaoDeSubmissaoDeArquivoDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeSubmissaoDeArquivo;
import br.usp.ime.academicdevoir.infra.UsuarioSession;
import br.usp.ime.academicdevoir.controller.QuestoesDeSubmissaoDeArquivoController;

public class QuestoesDeSubmissaoDeArquivoControllerTeste {
    /**
	 * @uml.property  name="questoesC"
	 * @uml.associationEnd  
	 */
    private QuestoesDeSubmissaoDeArquivoController questoesC;
    /**
	 * @uml.property  name="dao"
	 * @uml.associationEnd  
	 */
    private QuestaoDeSubmissaoDeArquivoDao dao;
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
    
    @Before
    public void SetUp() {
        dao = mock(QuestaoDeSubmissaoDeArquivoDao.class);
        result = spy(new MockResult());
        validator = spy(new JSR303MockValidator());
        questoesC = new QuestoesDeSubmissaoDeArquivoController(dao, result,
                validator, usuarioSession);
    }
    
    @Test
    public void testeAdiciona() {
        QuestaoDeSubmissaoDeArquivo questao = new QuestaoDeSubmissaoDeArquivo();
        questoesC.cadastra(questao);

        verify(validator).validate(questao);
        verify(validator).onErrorUsePageOf(QuestoesController.class);
        verify(dao).salva(questao);
        verify(result).redirectTo(questoesC);
    }
    
    @Test
    public void testeAtualiza() {
        QuestaoDeSubmissaoDeArquivo questao = new QuestaoDeSubmissaoDeArquivo();
        questoesC.altera(questao);
        
        verify(validator).validate(questao);
        verify(validator).onErrorUsePageOf(QuestoesDeSubmissaoDeArquivoController.class);
        verify(dao).atualiza(questao);
        verify(result).redirectTo(questoesC);
    }
    
    @Test
    public void testeRemove() {
        QuestaoDeSubmissaoDeArquivo temp = new QuestaoDeSubmissaoDeArquivo();
        when(dao.carrega(0L)).thenReturn(temp);
        questoesC.remove(0L);
        
        verify(dao).remove(temp);
        verify(result).redirectTo(questoesC);
    }


}
