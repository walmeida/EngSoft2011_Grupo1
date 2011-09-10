package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.usp.ime.academicdevoir.entidade.QuestaoDeSubmissaoDeArquivo;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class QuestaoDeSubmissaoDeArquivoDao {
	
	private final Session session;

	public QuestaoDeSubmissaoDeArquivoDao(Session session) {
		this.session = session;
	}

	public List<QuestaoDeSubmissaoDeArquivo> listaTudo() {
		return this.session.createCriteria(QuestaoDeSubmissaoDeArquivo.class)
				.list();
	}

	public void salva(QuestaoDeSubmissaoDeArquivo questao) {
		Transaction tx = session.beginTransaction();
		session.save(questao);
		tx.commit();
	}

	public QuestaoDeSubmissaoDeArquivo carrega(Long id) {
		return (QuestaoDeSubmissaoDeArquivo) this.session.load(
				QuestaoDeSubmissaoDeArquivo.class, id);
	}

	public void atualiza(QuestaoDeSubmissaoDeArquivo questao) {
		Transaction tx = session.beginTransaction();
		this.session.update(questao);
		tx.commit();
	}

	public void remove(QuestaoDeSubmissaoDeArquivo questao) {
		Transaction tx = session.beginTransaction();
		this.session.delete(questao);
		tx.commit();
	}

	public List<QuestaoDeSubmissaoDeArquivo> busca(String title) {
		return session.createCriteria(QuestaoDeSubmissaoDeArquivo.class)
				.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE))
				.list();
	}

	public void recarrega(QuestaoDeSubmissaoDeArquivo questao) {
		session.refresh(questao);
	}
}
