package br.usp.ime.academicdevoir.dao;

import java.util.List;

import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.usp.ime.academicdevoir.entidade.QuestaoDeTexto;
import br.com.caelum.vraptor.ioc.Component;

@Component
@PrimaryKeyJoinColumn(name="id")
public class QuestaoDeTextoDao {
	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="br.usp.ime.academicdevoir.entidade.QuestaoDeTexto"
	 */
	private final Session session;

	public QuestaoDeTextoDao(Session session) {
		this.session = session;
	}

    @SuppressWarnings("unchecked")
	/**
	 * Devolve uma lista com todas as questões de texto cadastradas no banco de dados.
	 * 
	 * @return List<QuestaoDeTexto>
	 */
	public List<QuestaoDeTexto> listaTudo() {
		return this.session.createCriteria(QuestaoDeTexto.class).list();
	}

	/**
	 * Cadastra a questão fornecida no banco de dados.
	 * 
	 * @param questao
	 */
	public void salva(QuestaoDeTexto questao) {
		Transaction tx = session.beginTransaction();
		session.save(questao);
		tx.commit();
	}

	/**
	 * Devolve uma questão de texto com o id fornecido.
	 * 
	 * @param id
	 * @return QuestaoDeTexto
	 */
	public QuestaoDeTexto carrega(Long id) {
		return (QuestaoDeTexto) this.session.load(QuestaoDeTexto.class, id);
	}

	/**
	 * Atualiza a questão fornecida no banco de dados. 
	 * 
	 * @param questao
	 */
	public void atualiza(QuestaoDeTexto questao) {
		Transaction tx = session.beginTransaction();
		this.session.update(questao);
		tx.commit();
	}

	/**
	 * Remove a questão fornecida do banco de dados.
	 * 
	 * @param questao
	 */
	public void remove(QuestaoDeTexto questao) {
		Transaction tx = session.beginTransaction();
		this.session.delete(questao);
		tx.commit();
	}
	
	/**
	 * Devolve uma QuestaoDeTexto com o id fornecido, se existir. Caso contrário, retorna null. 
	 * 
	 * @param id
	 * @return QuestaoDeTexto
	 */
	public QuestaoDeTexto buscaPorId(Long id) {
		return (QuestaoDeTexto) session
				.createCriteria(QuestaoDeTexto.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
	}

	public void recarrega(QuestaoDeTexto questao) {
		session.refresh(questao);
	}
}
