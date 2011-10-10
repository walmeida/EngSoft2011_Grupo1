package br.usp.ime.academicdevoir.entidade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;

@Entity
public class Turma {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@ManyToOne
	private Professor professor;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "Turma_Aluno", joinColumns = @JoinColumn(name = "turma_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	@OrderBy("nome")
	private Collection<Aluno> alunos = new ArrayList<Aluno>();
	@ManyToOne
	private Disciplina disciplina;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "turma_listas", joinColumns = @JoinColumn(name = "id_turma"), inverseJoinColumns = @JoinColumn(name = "id_lista"))
	private List<ListaDeExercicios> listasDeExercicios;

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Collection<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Collection<Aluno> alunos) {
		this.alunos = alunos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ListaDeExercicios> getListas() {
		return listasDeExercicios;
	}

	public void setListas(List<ListaDeExercicios> listas) {
		this.listasDeExercicios = listas;
	}

}
