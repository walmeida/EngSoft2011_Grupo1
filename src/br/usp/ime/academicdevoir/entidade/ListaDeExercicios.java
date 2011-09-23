package br.usp.ime.academicdevoir.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class ListaDeExercicios {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String enunciado;	
	
	private Integer peso;
	
	private Boolean visivel;
	
	@Temporal(TemporalType.DATE)
	private Date prazoDeEntrega;
	
	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "turma_id")
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Turma turma;*/
	
	@OneToMany
	@JoinTable(name="QuestoesDaLista", joinColumns = @JoinColumn(name="lista_id"), inverseJoinColumns = @JoinColumn(name="questaoDaLista_id"))
	@Cascade(CascadeType.ALL)
	private List<QuestaoDaLista> questoes = new ArrayList<QuestaoDaLista>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Boolean getVisivel() {
		return visivel;
	}

	public void setVisivel(Boolean visivel) {
		this.visivel = visivel;
	}
	/*
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}*/

	public Date getPrazoDeEntrega() {
		return prazoDeEntrega;
	}

	public void setPrazoDeEntrega(Date prazoDeEntrega) {
		this.prazoDeEntrega = prazoDeEntrega;
	}

	public List<QuestaoDaLista> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<QuestaoDaLista> questoes) {
		this.questoes = questoes;
	}
}
