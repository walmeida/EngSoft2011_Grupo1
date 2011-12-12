package br.usp.ime.academicdevoir.entidade;

import org.junit.Assert;
import org.junit.Test;


public class QuestaoDeMultiplaEscolhaTeste {
    
    @Test
    public void testaCorrecaoAutomaticaComRespostaCorreta () {
        QuestaoDeMultiplaEscolha questao = new QuestaoDeMultiplaEscolha();
        Resposta resposta = new Resposta();
        questao.setResposta(4);
        resposta.setValor("4");
        Assert.assertTrue(questao.respostaDoAlunoEhCorreta(resposta));
    }

}
