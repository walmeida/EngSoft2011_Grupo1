package br.usp.ime.academicdevoir.entidade;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity
public class QuestaoDeMultiplaEscolha extends Questao {
	
	@ElementCollection(fetch=FetchType.LAZY)
	@CollectionTable(name = "alternativasDasQuestoes")
	private List<String> alternativas;

	private Integer resposta;
	
	public List<String> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<String> alternativas) {
		this.alternativas = alternativas;
	}

	public Integer getResposta() {
		return resposta;
	}

	public void setResposta(Integer resposta) {
		this.resposta = resposta;
	}

}
