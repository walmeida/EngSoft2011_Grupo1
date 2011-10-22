package br.usp.ime.academicdevoir.entidade;

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
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@SecondaryTable(name = "propriedadesDaListaDeRespostas")
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
	
	private Integer notaFinal;
	
	@Embedded
	private PropriedadesDaListaDeRespostas propriedades;

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

	public PropriedadesDaListaDeRespostas getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(PropriedadesDaListaDeRespostas propriedades) {
		this.propriedades = propriedades;
	}

	public Integer getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal() {
		Integer notaFinal = new Integer(0);
		for(Integer nota : notas) {
			notaFinal += nota;
		}
		this.notaFinal = notaFinal;
	}
}
