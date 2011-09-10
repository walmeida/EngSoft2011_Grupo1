package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Entity;

@Entity
public class QuestaoDeMultiplaEscolha extends Questao {
	
	private String alternativas;

	private Integer resposta;
	
	public String getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(String alternativas) {
		this.alternativas = alternativas;
	}

	public Integer getResposta() {
		return resposta;
	}

	public void setResposta(Integer resposta) {
		this.resposta = resposta;
	}

}
