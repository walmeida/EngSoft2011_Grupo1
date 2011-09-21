package br.usp.ime.academicdevoir.teste;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.usp.ime.academicdevoir.dao.QuestaoDeSubmissaoDeArquivoDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeSubmissaoDeArquivo;
import br.usp.ime.academicdevoir.controller.QuestoesDeSubmissaoDeArquivoController;

public class QuestoesDeSubmissaoDeArquivoControllerTeste {
    private QuestoesDeSubmissaoDeArquivoController questoesC;
    private QuestaoDeSubmissaoDeArquivoDao dao;
    private Result result;
    private Validator validator;
    
    @Before
    public void SetUp() {
        dao = mock(QuestaoDeSubmissaoDeArquivoDao.class);
        result = mock(Result.class);
        validator = mock(Validator.class);
        questoesC = new QuestoesDeSubmissaoDeArquivoController(dao, result,
                validator);
        when(
                validator
                        .onErrorUsePageOf(any(QuestoesDeSubmissaoDeArquivoController.class)))
                .thenReturn(questoesC);
        when(
                validator
                        .onErrorUsePageOf(QuestoesDeSubmissaoDeArquivoController.class))
                .thenReturn(questoesC);
        
        when(result.redirectTo(any(QuestoesDeSubmissaoDeArquivoController.class)))
                .thenReturn(questoesC);
    }
    
    @Test
    public void testeAdiciona() {
        QuestaoDeSubmissaoDeArquivo questao = new QuestaoDeSubmissaoDeArquivo();
        questoesC.adiciona(questao);

        verify(validator).validate(questao);
        verify(validator).onErrorUsePageOf(questoesC);
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
