package br.usp.ime.academicdevoir.controller;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.controller.QuestoesDeTextoController;
import br.usp.ime.academicdevoir.dao.QuestaoDeTextoDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeTexto;

public class QuestoesDeTextoControllerTeste {
    private QuestoesDeTextoController questoesC;
    private QuestaoDeTextoDao dao;
    private MockResult result;
    private JSR303MockValidator validator;

    @Before
    public void SetUp() {
        dao = mock(QuestaoDeTextoDao.class);
        result = spy(new MockResult());
        validator = spy(new JSR303MockValidator());
        questoesC = new QuestoesDeTextoController(result, dao,
                validator);
    }

    @Test
    public void testeAdiciona() {
        QuestaoDeTexto questao = new QuestaoDeTexto();
        questoesC.cadastra(questao);

        verify(validator).validate(questao);
        verify(validator).onErrorUsePageOf(QuestoesController.class);
        verify(dao).salva(questao);
        verify(result).redirectTo(questoesC);
    }
    
    @Test
    public void testeAtualiza() {
        QuestaoDeTexto questao = new QuestaoDeTexto();
        questoesC.altera(questao);
        
        verify(validator).validate(questao);
        verify(validator).onErrorUsePageOf(QuestoesDeTextoController.class);
        verify(dao).atualiza(questao);
        verify(result).redirectTo(questoesC);
    }
    
    @Test
    public void testeRemove() {
        QuestaoDeTexto temp = new QuestaoDeTexto();
        when(dao.carrega(0L)).thenReturn(temp);
        questoesC.remove(0L);
        
        verify(dao).remove(temp);
        verify(result).redirectTo(questoesC);
    }
}
