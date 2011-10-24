package br.usp.ime.academicdevoir.entidade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
/**
 * Entidade que representa um aluno cadastrado no sistema.
 * @author Vinicius Rezende
 */
public class Aluno extends Usuario {
	/**
	 * @uml.property  name="turmas"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="alunos:br.usp.ime.academicdevoir.entidade.Turma"
	 */
	@ManyToMany(mappedBy = "alunos", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.EAGER)
	@OrderBy("disciplina")
	private Collection<Turma> turmas = new ArrayList<Turma>();

	@OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ListaDeRespostas> listasDeRespostas;

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

	public List<ListaDeRespostas> getListasDeRespostas() {
		return listasDeRespostas;
	}

	public void setListasDeRespostas(List<ListaDeRespostas> listasDeRespostas) {
		this.listasDeRespostas = listasDeRespostas;
	}
}
