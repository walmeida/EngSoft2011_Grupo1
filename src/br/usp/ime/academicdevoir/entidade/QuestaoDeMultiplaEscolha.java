package br.usp.ime.academicdevoir.entidade;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import br.usp.ime.academicdevoir.controller.QuestoesDeMultiplaEscolhaController;
import br.usp.ime.academicdevoir.infra.TipoDeQuestao;

@Entity
public class QuestaoDeMultiplaEscolha extends Questao {
	
	/**
	 * @uml.property  name="alternativas"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.lang.String"
	 */
	@ElementCollection(fetch=FetchType.LAZY)
	@CollectionTable(name = "alternativasDasQuestoes")
	private List<String> alternativas;

	/**
	 * @uml.property  name="resposta"
	 */
	private Integer resposta;
	
	public List<String> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<String> alternativas) {
		this.alternativas = alternativas;
	}

	/**
	 * @return
	 * @uml.property  name="resposta"
	 */
	public Integer getResposta() {
		return resposta;
	}

	/**
	 * @param resposta
	 * @uml.property  name="resposta"
	 */
	public void setResposta(Integer resposta) {
		this.resposta = resposta;
	}
	
	public TipoDeQuestao getTipo() {
		return TipoDeQuestao.MULTIPLAESCOLHA;
	}
	
	public String getRenderizacao(){
		String htmlResult = "";
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<table>");
		for (int i = 0; i < alternativas.size(); i++) {
			buffer.append("<tr><td><input type=\"radio\" name=\"");
			buffer.append(this.getId());
			buffer.append("\" value=\"");
			buffer.append(this.getId());
			buffer.append("\" /></td><td>");
			buffer.append(alternativas.get(i));
			buffer.append("</td></tr>");
		}
		buffer.append("</table>");
		
		htmlResult = buffer.toString();
		
		return htmlResult;
	}

}
