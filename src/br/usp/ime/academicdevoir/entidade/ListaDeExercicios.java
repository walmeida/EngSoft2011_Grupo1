package br.usp.ime.academicdevoir.entidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

	@Embedded
	@Valid
	private PropriedadesDaListaDeExercicios propriedades;	

	/**
	 * @uml.property  name="turmas"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="br.usp.ime.academicdevoir.entidade.Turma"
	 */
	//@ManyToMany(fetch = FetchType.LAZY)
	//@JoinTable(name = "turma_listas", joinColumns = { @JoinColumn(name = "id_lista") }, inverseJoinColumns = { @JoinColumn(name = "id_turma") })
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_turma")
	@Fetch(FetchMode.JOIN)
	private Turma turma;

	/**
	 * @uml.property  name="questoes"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="br.usp.ime.academicdevoir.entidade.QuestaoDaLista"
	 */
	@ElementCollection
	@CollectionTable(name = "questoesDaLista")
	private List<QuestaoDaLista> questoes;

	@OneToMany(mappedBy = "listaDeExercicios", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ListaDeRespostas> respostas;

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

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<QuestaoDaLista> getQuestoes() {
		Collections.sort(questoes, new Comparator<QuestaoDaLista>() {  
            public int compare(QuestaoDaLista q1, QuestaoDaLista q2) {  
                return q1.getOrdem() < q2.getOrdem() ? -1 : (q1.getOrdem() > q2.getOrdem() ? +1 : 0);  
            }  
        }); 
		return questoes;
	}

	public void setQuestoes(List<QuestaoDaLista> questoes) {
		this.questoes = questoes;
	}

	public List<ListaDeRespostas> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<ListaDeRespostas> respostas) {
		this.respostas = respostas;
	}

	public PropriedadesDaListaDeExercicios getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(PropriedadesDaListaDeExercicios propriedades) {
		this.propriedades = propriedades;
	}
	
	public void getPesos() {
		List<Integer> pesos = new ArrayList<Integer>();
		for(QuestaoDaLista questao : questoes) {
			pesos.add(questao.getPeso());
		}
	}
}
