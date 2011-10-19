package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Entity;

@Entity
public class QuestaoDeVouF extends Questao {
	
	private Boolean resposta;

	public Boolean getResposta() {
		return resposta;
	}

	public void setResposta(Boolean resposta) {
		this.resposta = resposta;
	}
	
	public String getRenderizacao(){
		String htmlResult = "";
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<div><p>questao.order) ");
		buffer.append(this.getEnunciado());
	    buffer.append("</p></div><table>");
		
		buffer.append("<tr><td><input type=\"radio\" name=\"");
		buffer.append("Q + questao.order");
		buffer.append("\" value=\"1\" />Verdadeiro </td>");
		buffer.append("<td><input type=\"radio\" name=\"");
		buffer.append("Q + questao.order");
		buffer.append("\" value=\"0\">Falso");
		buffer.append("</td></tr>");
				
		buffer.append("</table>");
		
		htmlResult = buffer.toString();
		
		return htmlResult;
	}
	
}
