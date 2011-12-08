package br.usp.ime.academicdevoir.entidade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

@Embeddable
public class PropriedadesDaListaDeExercicios {
	/**
	 * @uml.property name="nome"
	 */
	private String nome;

	/**
	 * @uml.property name="enunciado"
	 */
	private String enunciado;

	/**
	 * @uml.property name="peso"
	 */
	private Integer peso;

	/**
	 * @uml.property name="visivel"
	 */
	private Boolean visivel;

	/**
	 * @uml.property name="prazoDeEntrega"
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	private Date prazoDeEntrega;

	private Integer numeroMaximoDeEnvios;

	@Enumerated(EnumType.ORDINAL)
	private AutoCorrecao autoCorrecao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Boolean getVisivel() {
		return visivel;
	}

	public void setVisivel(Boolean visivel) {
		this.visivel = visivel;
	}

	public Date getPrazoDeEntrega() {
		return prazoDeEntrega;
	}
	
	public List<Integer> getPrazoPrazoDeEntregaEmLista() {
		List<Integer> prazoDeEntrega = new ArrayList<Integer>();
		Calendar data = Calendar.getInstance();
		data.setTimeInMillis(this.prazoDeEntrega.getTime());
		
		prazoDeEntrega.add(data.get(Calendar.DAY_OF_MONTH));
		prazoDeEntrega.add(data.get(Calendar.MONTH));
		prazoDeEntrega.add(data.get(Calendar.YEAR));
		prazoDeEntrega.add(data.get(Calendar.HOUR_OF_DAY));
		prazoDeEntrega.add(data.get(Calendar.MINUTE));
		
		return prazoDeEntrega;
	}
	
	public String getPrazoDeEntregaFormatado() {
		SimpleDateFormat prazo = new SimpleDateFormat(
				"EEE, dd'/'MM'/'yyyy HH:mm");
		
		return prazo.format(prazoDeEntrega);
	}

	public void setPrazoDeEntrega(Date prazoDeEntrega) {
		this.prazoDeEntrega = prazoDeEntrega;
	}	
	
	public void setPrazoDeEntrega(List<Integer> prazoDeEntrega) {
		Calendar data = Calendar.getInstance();
		data.set(prazoDeEntrega.get(2).intValue(), prazoDeEntrega.get(1).intValue() - 1, prazoDeEntrega.get(0).intValue(), prazoDeEntrega
				.get(3).intValue(), prazoDeEntrega.get(4).intValue());
		
		this.prazoDeEntrega = new Date(data.getTimeInMillis());
	}

	public Integer getNumeroMaximoDeEnvios() {
		return numeroMaximoDeEnvios;
	}

	public void setNumeroMaximoDeEnvios(Integer numeroMaximoDeEnvios) {
		this.numeroMaximoDeEnvios = numeroMaximoDeEnvios;
	}

	public AutoCorrecao getAutoCorrecao() {
		return autoCorrecao;
	}

	public void setAutoCorrecao(AutoCorrecao autoCorrecao) {
		this.autoCorrecao = autoCorrecao;
	}
}
