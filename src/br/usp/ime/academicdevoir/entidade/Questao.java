package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.usp.ime.academicdevoir.infra.TipoDeQuestao;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Questao {

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 1024)
	private String enunciado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public abstract TipoDeQuestao getTipo();
	
	public String renderiza(){
		return null;
	}
}