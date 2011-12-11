package br.usp.ime.academicdevoir.entidade;

import java.util.ArrayList;
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

	public int adiciona(Resposta novaResposta) {
		Long id = novaResposta.getQuestao().getId();
		int i = -1;

		for (Resposta resposta : respostas) {
			if (resposta.getQuestao().getId() == id) {
				i = respostas.indexOf(resposta);
				break;
			}
		}
		// FIXME
		if (i < 0) {
		   // if (novaResposta.getValor() != null && 
             //       !novaResposta.getValor().isEmpty())
		    respostas.add(novaResposta);
		}
		else respostas.set(i, novaResposta);
		return i;
	}
	
	public void autocorrecao() {
	    //List para os pesos das questões usados na nota final
	    List<Integer> pesosDasQuestoes = new ArrayList<Integer>();
	    QuestaoDaLista questaoDaLista = null;
	    
	    //Para cada resposta dessa lista
	    for (Resposta resposta : respostas) {
        
            //Pegando a questao a qual a resposta se refere
            Questao questao = resposta.getQuestao();
        
            //Obtendo a Questao relacionada com a lista para obter as propriedades
            //QuestaoDaLista questaoDaLista = questaoDaListaDao.getQuestaoDaListaPorIds(id, questao.getId());
        
            for (QuestaoDaLista i : listaDeExercicios.getQuestoes())
                if (i.getQuestao().equals(questao)) {
                questaoDaLista = i;
                break;
            }
            //Montando o vetor de pesos para o cálculo da nota final
            pesosDasQuestoes.add(questaoDaLista.getPeso());
        
            //Resultado da Comparação da Resposta (Correção): True se correta, False se errada e NULL se aberta. 
            Boolean resultado = questao.respostaDoAlunoEhCorreta(resposta);
        
            //Verificando se a resposta está certa ou não.
            if(resultado == true) resposta.setNota(100.0);
            //#TODO Questões abertas?? Como faz??
            //else if (resultado == false) resposta.setNota(0.0);  Abaixo seria o NULL
            else resposta.setNota(0.0);
	    }
    
	    //Atribuindo a nota final à lista
	    setNotaFinal(pesosDasQuestoes);
	    propriedades.setEstado(EstadoDaListaDeRespostas.CORRIGIDA);
	
	}
}
