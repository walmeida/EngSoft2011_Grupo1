package br.usp.ime.academicdevoir.entidade;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("professor")
public class Professor extends Usuario {
	@OneToMany(mappedBy = "professor")
	private Collection<Turma> turmas = new ArrayList<Turma>();


	public Collection<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Collection<Turma> turmas) {
		this.turmas = turmas;
	}
}