package br.usp.ime.academicdevoir.entidade;

import org.junit.Assert;
import org.junit.Test;


public class QuestaoDeVouFTeste {
    @Test
    public void testaCorrecaoAutomaticaComRespostaCorreta () {
        QuestaoDeVouF questao = new QuestaoDeVouF();
        Resposta resposta = new Resposta();
        questao.setResposta(true);
        resposta.setValor("true");
        Assert.assertTrue(questao.respostaDoAlunoEhCorreta(resposta));
    }
    
    @Test
    public void testaCorrecaoAutomaticaComRespostaErrada () {
        QuestaoDeVouF questao = new QuestaoDeVouF();
        Resposta resposta = new Resposta();
        questao.setResposta(true);
        resposta.setValor("false");
        Assert.assertFalse(questao.respostaDoAlunoEhCorreta(resposta));
    }

}
