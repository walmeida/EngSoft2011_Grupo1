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

	public List<QuestaoDeMultiplaEscolha> listaTudo() {
		return this.session.createCriteria(QuestaoDeMultiplaEscolha.class).list();
	}

	public void salva(QuestaoDeMultiplaEscolha questao) {
		Transaction tx = session.beginTransaction();
		session.save(questao);
		tx.commit();
	}

	public QuestaoDeMultiplaEscolha carrega(Long id) {
		return (QuestaoDeMultiplaEscolha) this.session.load(QuestaoDeMultiplaEscolha.class, id);
	}

	public void atualiza(QuestaoDeMultiplaEscolha questao) {
		Transaction tx = session.beginTransaction();
		this.session.update(questao);
		tx.commit();
	}

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
