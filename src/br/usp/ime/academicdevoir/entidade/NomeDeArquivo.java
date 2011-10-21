package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Embeddable;

@Embeddable
public class NomeDeArquivo {
	private String nome;
	
	public NomeDeArquivo(String valor) {
		this.setValor(valor);
	}

	public String getValor() {
		return nome;
	}

	public void setValor(String valor) {
		this.nome = valor;
	}
}
