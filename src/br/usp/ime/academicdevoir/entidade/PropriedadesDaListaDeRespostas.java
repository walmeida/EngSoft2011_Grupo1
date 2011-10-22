package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class PropriedadesDaListaDeRespostas {
	
	@Enumerated(EnumType.ORDINAL)
	private EstadoDaListaDeRespostas estado;
	
	private Integer numeroDeEnvios;

	public EstadoDaListaDeRespostas getEstado() {
		return estado;
	}

	public void setEstado(EstadoDaListaDeRespostas estado) {
		this.estado = estado;
	}

	public Integer getNumeroDeEnvios() {
		return numeroDeEnvios;
	}

	public void setNumeroDeEnvios(Integer numeroDeEnvios) {
		this.numeroDeEnvios = numeroDeEnvios;
	}
}
