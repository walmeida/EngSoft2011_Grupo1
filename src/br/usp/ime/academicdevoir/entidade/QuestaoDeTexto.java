package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Entity;

import br.usp.ime.academicdevoir.infra.TipoDeQuestao;

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
	
	public TipoDeQuestao getTipo() {
		return TipoDeQuestao.TEXTO;
	}
	
	public String getRenderizacao(){
		String htmlResult = "";
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<div><textarea id=\" ");
		buffer.append(this.getId());
		buffer.append("\" name=\"listaDeRespostas.respostas[].valor\"");
		buffer.append("\" rows=\"10\" cols=\"50\"></textarea>");
		buffer.append("</div>");
		buffer.append("<input type=\"hidden\" name=\"listaDeIdsQuestoes[]\" value=\"");
		buffer.append(this.getId());
		buffer.append("\" />");
				
		htmlResult = buffer.toString();
		
		return htmlResult;
	}
	
}
