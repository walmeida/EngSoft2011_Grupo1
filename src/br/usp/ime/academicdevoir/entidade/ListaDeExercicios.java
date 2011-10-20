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

	/**
	 * @uml.property  name="id"
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * @uml.property  name="nome"
	 */
	private String nome;

	/**
	 * @uml.property  name="enunciado"
	 */
	private String enunciado;

	/**
	 * @uml.property  name="peso"
	 */
	private Integer peso;

	/**
	 * @uml.property  name="visivel"
	 */
	private Boolean visivel;

	/**
	 * @uml.property  name="prazoDeEntrega"
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	private Date prazoDeEntrega;

	/**
	 * @uml.property  name="turmas"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="br.usp.ime.academicdevoir.entidade.Turma"
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "turma_listas", joinColumns = { @JoinColumn(name = "id_lista") }, inverseJoinColumns = { @JoinColumn(name = "id_turma") })
	private List<Turma> turmas;

	/**
	 * @uml.property  name="questoes"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="br.usp.ime.academicdevoir.entidade.QuestaoDaLista"
	 */
	@ElementCollection
	@CollectionTable(name = "questoesDaLista")
	private List<QuestaoDaLista> questoes;
	
	/**
	 * @return  id da lista de exercícios
	 * @uml.property  name="id"
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id  id da lista de exercícios
	 * @uml.property  name="id"
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return  nome da lista de exercícios
	 * @uml.property  name="nome"
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 * @uml.property  name="nome"
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return
	 * @uml.property  name="enunciado"
	 */
	public String getEnunciado() {
		return enunciado;
	}

	/**
	 * @param enunciado
	 * @uml.property  name="enunciado"
	 */
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

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

	public Boolean getVisivel() {
		return visivel;
	}

	/**
	 * @param visivel
	 * @uml.property  name="visivel"
	 */
	public void setVisivel(Boolean visivel) {
		this.visivel = visivel;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	/**
	 * @return
	 * @uml.property  name="prazoDeEntrega"
	 */
	public Date getPrazoDeEntrega() {
		return prazoDeEntrega;
	}

	/**
	 * @param prazoDeEntrega
	 * @uml.property  name="prazoDeEntrega"
	 */
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
