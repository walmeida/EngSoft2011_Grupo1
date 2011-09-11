package br.usp.ime.academicdevoir.teste;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

import br.usp.ime.academicdevoir.controller.QuestoesDeMultiplaEscolhaController;
import br.usp.ime.academicdevoir.dao.QuestaoDeMultiplaEscolhaDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;

public class QuestoesDeMultiplaEscolhaControllerTeste {
    private QuestoesDeMultiplaEscolhaController questoesC;
    private QuestaoDeMultiplaEscolhaDao dao;
    private Result result;
    private Validator validator;

    @Before
    public void SetUp() {
        dao = mock(QuestaoDeMultiplaEscolhaDao.class);
        result = mock(Result.class);
        validator = mock(Validator.class);
        questoesC = new QuestoesDeMultiplaEscolhaController(dao, result,
                validator);
        when(
                validator
                        .onErrorUsePageOf(any(QuestoesDeMultiplaEscolhaController.class)))
                .thenReturn(questoesC);
        when(
                validator
                        .onErrorUsePageOf(QuestoesDeMultiplaEscolhaController.class))
                .thenReturn(questoesC);
        
        when(result.redirectTo(any(QuestoesDeMultiplaEscolhaController.class)))
                .thenReturn(questoesC);
    }

    @Test
    public void testeAdiciona() {
        QuestaoDeMultiplaEscolha questao = new QuestaoDeMultiplaEscolha();
        questoesC.adiciona(questao);

        verify(validator).validate(questao);
        verify(validator).onErrorUsePageOf(questoesC);
        verify(dao).salva(questao);
        verify(result).redirectTo(questoesC);
    }
    
    @Test
    public void testeAtualiza() {
        QuestaoDeMultiplaEscolha questao = new QuestaoDeMultiplaEscolha();
        questoesC.atualiza(questao);
        
        verify(validator).validate(questao);
        verify(validator).onErrorUsePageOf(QuestoesDeMultiplaEscolhaController.class);
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
