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

	/**
	 * Retorna uma lista com todas as questões cadastradas no banco de dados.
	 * 
	 * @return List<QuestaoDeMultiplaEscolha>
	 */
	public List<Questao> listaTudo() {
		return this.session.createCriteria(Questao.class).list();
	}

	/**
	 * Retorna uma questão com o id fornecido.
	 * 
	 * @param id
	 * @return Questao
	 */
	public Object carrega(Long id) {
		return this.session.load(Questao.class, id);
	}

	/**
	 * Remove a questão fornecida do banco de dados.
	 * 
	 * @param questao
	 */
	public void remove(Questao questao) {
		Transaction tx = session.beginTransaction();
		this.session.delete(questao);
		tx.commit();
	}

	/**
	 * Retorna uma Questao com o id fornecido, se existir. Caso contrário,
	 * retorna null.
	 * 
	 * @param id
	 * @return Questao
	 */
	public Questao busca(Long id) {
		return (Questao) session.createCriteria(Questao.class)
				.add(Restrictions.idEq(id)).uniqueResult();
	}

	public void recarrega(Questao questao) {
		session.refresh(questao);
	}
}
