package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Entity;

import br.usp.ime.academicdevoir.infra.TipoDeQuestao;

@Entity
public class QuestaoDeVouF extends Questao {
	
	/**
	 * @uml.property  name="resposta"
	 */
	private Boolean resposta;

	public Boolean getResposta() {
		return resposta;
	}

	/**
	 * @param resposta
	 * @uml.property  name="resposta"
	 */
	public void setResposta(Boolean resposta) {
		this.resposta = resposta;
	}
	
	public TipoDeQuestao getTipo() {
		return TipoDeQuestao.VOUF;
	}
	
	public String getRenderizacao(){
		String htmlResult = "";
		StringBuffer buffer = new StringBuffer();
		
		
	    buffer.append("<table>");
		
		buffer.append("<tr><td><input type=\"radio\" name=\"resposta.valor\" value=\"1\" />Verdadeiro </td>");
		buffer.append("<td><input type=\"radio\" name=\"resposta.valor\" value=\"0\">Falso");
		buffer.append("</td></tr>");
		
		buffer.append("<input type=\"hidden\" name=\"idDaQuestao\" value=\"");
		buffer.append(this.getId());
		buffer.append("\" />");
				
		buffer.append("</table>");
		
		htmlResult = buffer.toString();
		
		return htmlResult;
	}
	
}
