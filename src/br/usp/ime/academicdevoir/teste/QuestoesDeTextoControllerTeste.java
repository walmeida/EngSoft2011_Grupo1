package br.usp.ime.academicdevoir.teste;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.usp.ime.academicdevoir.controller.QuestoesDeTextoController;
import br.usp.ime.academicdevoir.dao.QuestaoDeTextoDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeTexto;

public class QuestoesDeTextoControllerTeste {
    private QuestoesDeTextoController questoesC;
    private QuestaoDeTextoDao dao;
    private Result result;
    private Validator validator;

    @Before
    public void SetUp() {
        dao = mock(QuestaoDeTextoDao.class);
        result = mock(Result.class);
        validator = mock(Validator.class);
        questoesC = new QuestoesDeTextoController(dao, result,
                validator);
        when(
                validator
                        .onErrorUsePageOf(any(QuestoesDeTextoController.class)))
                .thenReturn(questoesC);
        when(
                validator
                        .onErrorUsePageOf(QuestoesDeTextoController.class))
                .thenReturn(questoesC);
        
        when(result.redirectTo(any(QuestoesDeTextoController.class)))
                .thenReturn(questoesC);
    }

    @Test
    public void testeAdiciona() {
        QuestaoDeTexto questao = new QuestaoDeTexto();
        questoesC.adiciona(questao);

        verify(validator).validate(questao);
        verify(validator).onErrorUsePageOf(QuestoesDeTextoController.class);
        verify(dao).salva(questao);
        verify(result).redirectTo(questoesC);
    }
    
    @Test
    public void testeAtualiza() {
        QuestaoDeTexto questao = new QuestaoDeTexto();
        questoesC.atualiza(questao);
        
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
