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

	/**
	 * @uml.property  name="id"
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * @uml.property  name="enunciado"
	 */
	@Column(length = 1024)
	private String enunciado;

	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return
	 * @uml.property  name="enunciado"
	 */
	public String getEnunciado() {
		return enunciado;
	}

	/**
	 * @param enunciado
	 * @uml.property  name="enunciado"
	 */
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public abstract TipoDeQuestao getTipo();
	
	public abstract String getRenderizacao();
	
	public abstract String getRenderAlteracao(Resposta resposta);
	
	public abstract Boolean respostaDoAlunoEhCorreta(Resposta respostaAluno);
}