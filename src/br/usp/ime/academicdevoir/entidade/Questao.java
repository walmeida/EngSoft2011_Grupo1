package br.usp.ime.academicdevoir.entidade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import br.usp.ime.academicdevoir.dao.TagDao;
import br.usp.ime.academicdevoir.infra.Constantes;
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

    @Column(length = Constantes.MAX_TAM_CAMPO)
	protected String enunciado;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tags_questoes", joinColumns = { @JoinColumn(name = "id_questao") }, inverseJoinColumns = { @JoinColumn(name = "id_tag") })
	protected List<Tag> tags = new ArrayList<Tag>();

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

	public List<Tag> getTags() {
		return tags;
	}
	
	public String getTagsEmString() {
		StringBuffer buffer = new StringBuffer();
		for (Tag tag : this.tags) {
			buffer.append(tag.getNome());
			buffer.append(", ");
		}
		return buffer.toString();
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public void setTags(String stringTags, TagDao dao) {
		if (stringTags == null) return;
		
		List<String> tags = Arrays.asList( stringTags.split(",[ ]*") );
		for (String nome : tags) {
			Tag tag = dao.buscaPeloNome(nome);
			if (tag == null) {
				tag = new Tag(nome);
				dao.salva(tag);
			}
			this.tags.add(tag);
		}		
	}
	
	public abstract TipoDeQuestao getTipo();
	
	public abstract String getRenderizacao();
	
	public abstract String getRenderAlteracao(Resposta resposta);
	
	public String getRenderCorrecao (Resposta resposta) {
        if (resposta == null)
            resposta = new Resposta();
        
        String htmlResult = "";
        StringBuffer buffer = new StringBuffer();

        buffer.append("<p>");
        if (resposta.getValor() != null)
            buffer.append(resposta.getValor());
        buffer.append("</p>");
        buffer.append("<p> Comentários: ");
        if (resposta.getComentario() != null)
            buffer.append(resposta.getComentario());
        buffer.append("</p>");
        buffer.append("<p> Nota: ");
        if (resposta.getNota() != null)
            buffer.append(resposta.getNota());
        buffer.append("</p>");

        htmlResult = buffer.toString();

        return htmlResult;
        
    }
	public abstract Boolean respostaDoAlunoEhCorreta(Resposta respostaAluno);
	
	public abstract Questao copia(TagDao tagDao);
}