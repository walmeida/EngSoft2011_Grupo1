package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Entity;

@Entity
public class QuestaoDeVouF extends Questao {
	
	private Boolean resposta;

	public Boolean getResposta() {
		return resposta;
	}

	public void setResposta(Boolean resposta) {
		this.resposta = resposta;
	}
}
