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
import br.usp.ime.academicdevoir.controller.QuestoesDeSubmissaoDeArquivoController;

public class QuestoesDeSubmissaoDeArquivoControllerTeste {
    private QuestoesDeSubmissaoDeArquivoController questoesC;
    private QuestaoDeSubmissaoDeArquivoDao dao;
    private MockResult result;
    private JSR303MockValidator validator;
    
    @Before
    public void SetUp() {
        dao = mock(QuestaoDeSubmissaoDeArquivoDao.class);
        result = spy(new MockResult());
        validator = spy(new JSR303MockValidator());
        questoesC = new QuestoesDeSubmissaoDeArquivoController(result, dao,
                validator);
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
