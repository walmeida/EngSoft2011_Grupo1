package br.usp.ime.academicdevoir.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class ListaDeRespostas {
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_aluno")
	@Fetch(FetchMode.JOIN)
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_lista")
	@Fetch(FetchMode.JOIN)
	private ListaDeExercicios listaDeExercicios;

	@ElementCollection
	@CollectionTable(name = "respostasDaLista")
	private List<Resposta> respostas;
	
	@ElementCollection
	@CollectionTable(name = "notasDaLista")
	private List<Integer> notas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public ListaDeExercicios getListaDeExercicios() {
		return listaDeExercicios;
	}

	public void setListaDeExercicios(ListaDeExercicios lista) {
		this.listaDeExercicios = lista;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public List<Integer> getNotas() {
		return notas;
	}

	public void setNotas(List<Integer> notas) {
		this.notas = notas;
	}
}
