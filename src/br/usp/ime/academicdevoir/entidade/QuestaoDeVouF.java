package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Entity;

import br.usp.ime.academicdevoir.dao.TagDao;
import br.usp.ime.academicdevoir.infra.TipoDeQuestao;

@Entity
public class QuestaoDeVouF extends Questao {

	/**
	 * @uml.property name="resposta"
	 */
	private Boolean resposta;

	public Boolean getResposta() {
		return resposta;
	}

	/**
	 * @param resposta
	 * @uml.property name="resposta"
	 */
	public void setResposta(Boolean resposta) {
		this.resposta = resposta;
	}

	public TipoDeQuestao getTipo() {
		return TipoDeQuestao.VOUF;
	}

	public String getRenderizacao() {
		String htmlResult = "";
		StringBuffer buffer = new StringBuffer();

		buffer.append("<table>");

		buffer.append("<tr><td><input type=\"radio\" name=\"resposta.valor\" value=\"true\" />Verdadeiro </td>");
		buffer.append("<td><input type=\"radio\" name=\"resposta.valor\" value=\"false\">Falso");
		buffer.append("</td></tr>");

		buffer.append("<input type=\"hidden\" name=\"idDaQuestao\" value=\"");
		buffer.append(this.getId());
		buffer.append("\" />");

		buffer.append("</table>");

		htmlResult = buffer.toString();

		return htmlResult;
	}

	public String getRenderAlteracao(Resposta resposta) {
		if (resposta == null || resposta.getValor() == null
				|| resposta.getValor().isEmpty())
			return getRenderizacao();

		String htmlResult = "";
		StringBuffer buffer = new StringBuffer();

		buffer.append("<table>");
		buffer.append("<tr><td><input type=\"radio\" name=\"resposta.valor\" value=\"true\"");
		if (resposta.getValor().equals("true"))
			buffer.append(" checked=\"checked\"");
		buffer.append(" />Verdadeiro </td><td><input type=\"radio\" name=\"resposta.valor\" value=\"false\"");
		if (resposta.getValor().equals("false"))
			buffer.append(" checked=\"checked\"");
		buffer.append("/>Falso</td></tr>");

		buffer.append("<input type=\"hidden\" name=\"idDaQuestao\" value=\"");
		buffer.append(this.getId());
		buffer.append("\" />");

		buffer.append("</table>");

		htmlResult = buffer.toString();

		return htmlResult;
	}
	
	public String getRenderCorrecao (Resposta resposta) {
        if (resposta == null)
            resposta = new Resposta();
        
        String htmlResult = "";
        StringBuffer buffer = new StringBuffer();

        buffer.append("<p>");
        
        if (resposta.getValor() != null) {
            if (resposta.getValor().equals("true"))
                buffer.append("Verdadeiro");
            else if (resposta.getValor().equals("false"))
                buffer.append("Falso");
        }
        
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
	
	public Boolean respostaDoAlunoEhCorreta(Resposta respostaAluno) {
	   return respostaAluno.getValor().equals(resposta.toString());
	}
	
	public QuestaoDeVouF copia(TagDao dao) {
		QuestaoDeVouF questao = new QuestaoDeVouF();    	
    	questao.enunciado = this.enunciado;
		questao.resposta = this.resposta;
		
		questao.setTags(this.getTagsEmString(), dao);
		
		return questao;
	}
}
