package br.usp.ime.academicdevoir.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

@Entity
/**
 * Entidade que representa uma lista e Exercícios cadastrada no sistema.
 * @author André
 */
public class ListaDeExercicios {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	private String enunciado;

	private Integer peso;

	private Boolean visivel;

	@Temporal(TemporalType.TIMESTAMP)
	@Future
	private Date prazoDeEntrega;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "turma_listas", joinColumns = { @JoinColumn(name = "id_lista") }, inverseJoinColumns = { @JoinColumn(name = "id_turma") })
	private List<Turma> turmas;

	@ElementCollection
	@CollectionTable(name = "questoesDaLista")
	private List<QuestaoDaLista> questoes;
	
	/**
	 * @return id da lista de exercícios
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            id da lista de exercícios
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return nome da lista de exercícios
	 */
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

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

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
