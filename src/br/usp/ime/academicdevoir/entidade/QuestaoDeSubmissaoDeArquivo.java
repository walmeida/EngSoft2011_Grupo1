package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "questao_id")
public class QuestaoDeSubmissaoDeArquivo extends Questao {
}
