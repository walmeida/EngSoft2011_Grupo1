package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Entity;

import br.usp.ime.academicdevoir.infra.TipoDeQuestao;

@Entity
public class QuestaoDeSubmissaoDeArquivo extends Questao {
	public TipoDeQuestao getTipo() {
		return TipoDeQuestao.SUBMISSAODEARQUIVO;
	}	
}
