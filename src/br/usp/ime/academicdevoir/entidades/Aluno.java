package br.usp.ime.academicdevoir.entidades;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Aluno extends Usuario {
	@ManyToMany(mappedBy = "alunos", cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Collection<Turma> turmas = new ArrayList<Turma>();


	public Collection<Turma> getTurmas() {
		return turmas;	
	}

	public void setTurmas(Collection<Turma> turmas) {
		this.turmas = turmas;
	}
}
