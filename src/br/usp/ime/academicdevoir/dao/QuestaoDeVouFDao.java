package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.usp.ime.academicdevoir.entidade.QuestaoDeVouF;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class QuestaoDeVouFDao {

	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="br.usp.ime.academicdevoir.entidade.QuestaoDeVouF"
	 */
	private final Session session;

	public QuestaoDeVouFDao(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Devolve uma lista com todas as questões de V ou F cadastradas
	 * no banco de dados.
	 * 
	 * @return List<QuestaoDeVouF>
	 */
	public List<QuestaoDeVouF> listaTudo() {
		return this.session.createCriteria(QuestaoDeVouF.class).list();
	}

	/**
	 * Cadastra a questão fornecida no banco de dados.
	 * 
	 * @param questao
	 */
	public void salva(QuestaoDeVouF questao) {
		Transaction tx = session.beginTransaction();
		session.save(questao);
		tx.commit();
	}

	/**
	 * Devolve uma questão de V ou F com o id fornecido.
	 * 
	 * @param id
	 * @return QuestaoDeVouF
	 */
	public QuestaoDeVouF carrega(Long id) {
		return (QuestaoDeVouF) this.session.load(QuestaoDeVouF.class, id);
	}

	/**
	 * Atualiza a questão fornecida no banco de dados.
	 * 
	 * @param questao
	 */
	public void atualiza(QuestaoDeVouF questao) {
		Transaction tx = session.beginTransaction();
		this.session.update(questao);
		tx.commit();
	}

	/**
	 * Remove a questão fornecida do banco de dados.
	 * 
	 * @param questao
	 */
	public void remove(QuestaoDeVouF questao) {
		Transaction tx = session.beginTransaction();
		this.session.delete(questao);
		tx.commit();
	}

	/**
	 * Devolve uma QuestaoDeVouF com o id fornecido, se existir. Caso contrário,
	 * retorna null.
	 * 
	 * @param id
	 * @return QuestaoDeVouF
	 */
	public QuestaoDeVouF buscaPorId(Long id) {
		return (QuestaoDeVouF) session.createCriteria(QuestaoDeVouF.class)
				.add(Restrictions.idEq(id)).uniqueResult();
	}

	public void recarrega(QuestaoDeVouF questao) {
		session.refresh(questao);
	}
}
