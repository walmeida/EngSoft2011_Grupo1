package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Resposta {

	private Integer inteiro;
	
	private Boolean booleano;
	
	private String texto;
	
	@Embedded
	private NomeDeArquivo arquivo;
	
	public Object getValor() {
		if (inteiro != null) return inteiro;
		if (booleano != null) return booleano;
		if (texto != null) return texto;
		if (arquivo != null) return arquivo;
		return null;
	}

	public void setInteiro(Integer inteiro) {
		this.inteiro = inteiro;
		this.booleano = null;
		this.texto = null;
		this.arquivo = null;
	}

	public void setBooleano(Boolean booleano) {
		this.inteiro = null;
		this.booleano = booleano;
		this.texto = null;
		this.arquivo = null;
	}

	public void setTexto(String texto) {
		this.inteiro = null;
		this.booleano = null;
		this.texto = texto;
		this.arquivo = null;
	}

	public void setArquivo(NomeDeArquivo arquivo) {
		this.inteiro = null;
		this.booleano = null;
		this.texto = null;
		this.arquivo = arquivo;
	}	
}