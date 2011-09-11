package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class QuestaoDeMultiplaEscolhaDao {
	
	private final Session session;

	public QuestaoDeMultiplaEscolhaDao(Session session) {
		this.session = session;
	}

	/**
	 * Retorna uma lista com todas as questões de múltipla escolha cadastradas no banco de dados.
	 * @return List<QuestaoDeMultiplaEscolha>
	 */
	public List<QuestaoDeMultiplaEscolha> listaTudo() {
		return this.session.createCriteria(QuestaoDeMultiplaEscolha.class).list();
	}

	/**
	 * Cadastra a questão fornecida no banco de dados.
	 * @param questao
	 */
	public void salva(QuestaoDeMultiplaEscolha questao) {
		Transaction tx = session.beginTransaction();
		session.save(questao);
		tx.commit();
	}

	/**
	 * Retorna uma questão de múltipla escolha com o id fornecido.
	 * @param id
	 * @return QuestaoDeMultiplaEscolha
	 */
	public QuestaoDeMultiplaEscolha carrega(Long id) {
		return (QuestaoDeMultiplaEscolha) this.session.load(QuestaoDeMultiplaEscolha.class, id);
	}

	/**
	 * Atualiza a questão fornecida no banco de dados. 
	 * @param questao
	 */
	public void atualiza(QuestaoDeMultiplaEscolha questao) {
		Transaction tx = session.beginTransaction();
		this.session.update(questao);
		tx.commit();
	}

	/**
	 * Remove a questão fornecida do banco de dados.
	 * @param questao
	 */
	public void remove(QuestaoDeMultiplaEscolha questao) {
		Transaction tx = session.beginTransaction();
		this.session.delete(questao);
		tx.commit();
	}

	public List<QuestaoDeMultiplaEscolha> busca(String title) {
		return session.createCriteria(QuestaoDeMultiplaEscolha.class)
				.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE))
				.list();
	}

	public void recarrega(QuestaoDeMultiplaEscolha questao) {
		session.refresh(questao);
	}
}
