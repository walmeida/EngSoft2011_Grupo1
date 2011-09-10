package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.usp.ime.academicdevoir.entidade.Questao;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class QuestaoDao {

	private final Session session;

	public QuestaoDao(Session session) {
		this.session = session;
	}

	public List<Questao> listaTudo() {
		return this.session.createCriteria(Questao.class).list();
	}

	public void salva(Questao questao) {
		Transaction tx = session.beginTransaction();
		session.save(questao);
		tx.commit();
	}

	public Questao carrega(Long id) {
		return (Questao) this.session.load(Questao.class, id);
	}

	public void atualiza(Questao questao) {
		Transaction tx = session.beginTransaction();
		this.session.update(questao);
		tx.commit();
	}

	public void remove(Questao questao) {
		Transaction tx = session.beginTransaction();
		this.session.delete(questao);
		tx.commit();
	}

	public List<Questao> busca(String title) {
		return session.createCriteria(Questao.class)
				.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE))
				.list();
	}

	public void recarrega(Questao questao) {
		session.refresh(questao);
	}

}
