package br.usp.ime.academicdevoir.entidade;

import java.util.Iterator;
import java.util.List;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@SecondaryTable(name = "propriedadesDaListaDeRespostas")
public class ListaDeRespostas {
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_aluno")
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_lista")
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private ListaDeExercicios listaDeExercicios;

	@ElementCollection
	@CollectionTable(name = "respostasDaLista")
	private List<Resposta> respostas;
	
	private Double notaFinal;
	
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

	public PropriedadesDaListaDeRespostas getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(PropriedadesDaListaDeRespostas propriedades) {
		this.propriedades = propriedades;
	}

	public Double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(List<Integer> pesos) {
		Iterator<Resposta> iRespostas = respostas.iterator();
		Iterator<Integer> iPesos = pesos.iterator();
		Double nota;
		Double notaFinal = new Double(0.0);
		Integer peso;
		Integer somaDosPesos = new Integer(0); 
		
		while (iRespostas.hasNext()) {
			nota = iRespostas.next().getNota();
			peso = iPesos.next();
			if (nota != null && peso != null) {
				notaFinal += nota*peso;
				somaDosPesos += peso;
			}
		}
		notaFinal /= somaDosPesos;
		this.notaFinal = notaFinal;
	}

	public void adiciona(Resposta novaResposta) {
		Long id = novaResposta.getQuestao().getId();
		
		for (Resposta resposta : respostas) {
			if (resposta.getQuestao().getId() == id) {
				if (novaResposta.getValor() == null || novaResposta.getValor().isEmpty() ) return;
				respostas.remove(resposta);
				break;
			}
		}
		respostas.add(novaResposta);
	}
}
