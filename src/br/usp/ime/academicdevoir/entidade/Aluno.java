package br.usp.ime.academicdevoir.entidade;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
/**
 * Entidade que representa um aluno cadastrado no sistema.
 * @author Vinicius Rezende
 */
public class Aluno extends Usuario {
	@ManyToMany(mappedBy = "alunos", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch=FetchType.EAGER)
	private Collection<Turma> turmas = new ArrayList<Turma>();

	/**
	 * @return lista das turmas em que o aluno está matriculado
	 */
	public Collection<Turma> getTurmas() {
		return turmas;
	}

	/**
	 * @param turmas
	 *            lista das turmas em que o aluno está matriculado.
	 */
	public void setTurmas(Collection<Turma> turmas) {
		this.turmas = turmas;
	}
}
