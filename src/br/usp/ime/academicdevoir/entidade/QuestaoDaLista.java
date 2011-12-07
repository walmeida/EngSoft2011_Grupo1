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
	
	/**
	 * @uml.property  name="peso"
	 */
	private Integer peso;
	
	/**
	 * @uml.property  name="peso"
	 */
	private Integer ordem;

	/**
	 * @uml.property  name="questao"
	 * @uml.associationEnd  
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questao_id")
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Questao questao;
	
	/**
	 * @return
	 * @uml.property  name="peso"
	 */
	public Integer getPeso() {
		return peso;
	}

	/**
	 * @param peso
	 * @uml.property  name="peso"
	 */
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	/**
	 * @return
	 * @uml.property  name="ordem"
	 */
	public Integer getOrdem() {
		return ordem;
	}

	/**
	 * @param ordem
	 * @uml.property  name="ordem"
	 */
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	/**
	 * @return
	 * @uml.property  name="questao"
	 */
	public Questao getQuestao() {
		return questao;
	}

	/**
	 * @param questao
	 * @uml.property  name="questao"
	 */
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

}
