package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Entity;

@Entity
public class QuestaoDeTexto extends Questao {
	
	/**
	 * @uml.property  name="resposta"
	 */
	private String resposta;

	/**
	 * @return
	 * @uml.property  name="resposta"
	 */
	public String getResposta() {
		return resposta;
	}

	/**
	 * @param resposta
	 * @uml.property  name="resposta"
	 */
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	public String getRenderizacao(){
		String htmlResult = "";
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<div><p>questao.order) ");
		buffer.append(this.getEnunciado());
	    buffer.append("</p></div>");
		
		buffer.append("<div><textarea id=\" ");
		buffer.append("Q + questao.order");
		buffer.append("\" rows=\"30\" cols=\"50\"></textarea>");
		buffer.append("</div>");
				
		htmlResult = buffer.toString();
		
		return htmlResult;
	}
	
}
