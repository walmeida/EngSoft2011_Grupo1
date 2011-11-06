package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Entity;

import br.usp.ime.academicdevoir.infra.TipoDeQuestao;

@Entity
public class QuestaoDeSubmissaoDeArquivo extends Questao {
	public TipoDeQuestao getTipo() {
		return TipoDeQuestao.SUBMISSAODEARQUIVO;
	}

	public String getRenderizacao() {
		String htmlResult = "";
		StringBuffer buffer = new StringBuffer();

		buffer.append("<div><input type=\"file\" id=\"resposta");
		buffer.append(this.getId());
		buffer.append("\" name=\"arquivo\" />");
		buffer.append("</div>");
		buffer.append("<input type=\"hidden\" name=\"idDaQuestao\" value=\"");
		buffer.append(this.getId());
		buffer.append("\" />");

		htmlResult = buffer.toString();

		return htmlResult;
	}

	public String getRenderAlteracao(Resposta resposta) {
		if (resposta == null || resposta.getValor() == null || resposta.getValor().isEmpty()) return getRenderizacao();
		
		String htmlResult = "";
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<a href=\"/academic-devoir/arquivos/");
		buffer.append(resposta.getQuestao().getId());
		buffer.append("\"/>");
		buffer.append(resposta.getValor());
		buffer.append("</a>");
		buffer.append("<button id=\"liberaUpload");
		buffer.append(this.getId());
		buffer.append("\" class=\"liberaUpload\" type=\"button\">");
		buffer.append("Atualizar arquivo");
		buffer.append("</button>");
		buffer.append("<div><input class=\"upload\" type=\"file\" id=\"resposta");
		buffer.append(this.getId());
		buffer.append("\" name=\"arquivo\" disabled=\"disabled\" />");
		buffer.append("</div>");
		buffer.append("<input type=\"hidden\" name=\"idDaQuestao\" value=\"");
		buffer.append(this.getId());
		buffer.append("\" />");

		htmlResult = buffer.toString();

		return htmlResult;
	}
}
