package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;

@Embeddable
public class QuestaoDaLista {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questao_id")
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Questao questao;

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
}
