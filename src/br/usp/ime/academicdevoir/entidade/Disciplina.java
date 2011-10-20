package br.usp.ime.academicdevoir.entidade;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
/**
 * Entidade que representa uma disciplina cadastrada no sistema.
 * @author Vinicius Rezende
 */
public class Disciplina {
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
	 * @uml.property  name="turmas" multiplicity="(0 -1)"
	 */
    @OneToMany(mappedBy = "disciplina")
    private Collection<Turma> turmas = new ArrayList<Turma>();

    /**
	 * @return  id da disciplina
	 * @uml.property  name="id"
	 */
    public Long getId() {
        return id;
    }

    /**
	 * @param id  id da disciplina
	 * @uml.property  name="id"
	 */
    public void setId(Long id) {
        this.id = id;
    }

    /**
	 * @return  nome da disciplina
	 * @uml.property  name="nome"
	 */
    public String getNome() {
        return nome;
    }

    /**
	 * @param nome  nome da disciplina
	 * @uml.property  name="nome"
	 */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return lista de turmas da disciplina
     */
    public Collection<Turma> getTurmas() {
        return turmas;
    }

    /**
     * @param turmas lista de turmas da disciplina
     */
    public void setTurmas(Collection<Turma> turmas) {
        this.turmas = turmas;
    }

}
