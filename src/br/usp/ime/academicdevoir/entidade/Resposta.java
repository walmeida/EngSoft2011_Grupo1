package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.usp.ime.academicdevoir.infra.Constantes;

@Embeddable
public class Resposta {
    @Column(length = Constantes.MAX_TAM_CAMPO)
	private String valor;
	
    @Min(0)
    @Max(100)
	private Double nota;
	
    @Column(length = Constantes.MAX_TAM_CAMPO)
	private String comentario;
	
    private String caminhoParaDiretorioDeTeste;
    
    /**
	 * @uml.property  name="questao"
	 * @uml.associationEnd  
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questao_id")
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Questao questao;
	
	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}
	
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public void setCaminhoParaDiretorioDeTeste(String caminhoParaDiretorioDeTeste) {
        this.caminhoParaDiretorioDeTeste = caminhoParaDiretorioDeTeste;
    }

    public String getCaminhoParaDiretorioDeTeste() {
        return caminhoParaDiretorioDeTeste;
    }

}