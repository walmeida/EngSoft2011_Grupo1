package br.usp.ime.academicdevoir.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Tag {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	@Length(max = 30)
	private String nome;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tags_questoes", joinColumns = { @JoinColumn(name = "id_tag") }, inverseJoinColumns = { @JoinColumn(name = "id_questao") })
	private List<Questao> questoes;

	public Tag() {
	}

	public Tag(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String valor) {
		this.nome = valor;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}
}
