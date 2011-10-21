package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.usp.ime.academicdevoir.entidade.QuestaoDeSubmissaoDeArquivo;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class QuestaoDeSubmissaoDeArquivoDao {
	
	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="br.usp.ime.academicdevoir.entidade.QuestaoDeSubmissaoDeArquivo"
	 */
	private final Session session;

	public QuestaoDeSubmissaoDeArquivoDao(Session session) {
		this.session = session;
	}

    @SuppressWarnings("unchecked")
	/**
	 * Devolve uma lista com todas as questões de submissão de arquivo cadastradas no banco de dados.
	 * 
	 * @return List<QuestaoDeSubmissaoDeArquivo>
	 */
	public List<QuestaoDeSubmissaoDeArquivo> listaTudo() {
		return this.session.createCriteria(QuestaoDeSubmissaoDeArquivo.class)
				.list();
	}

	/**
	 * Cadastra a questão fornecida no banco de dados.
	 * 
	 * @param questao
	 */
	public void salva(QuestaoDeSubmissaoDeArquivo questao) {
		Transaction tx = session.beginTransaction();
		session.save(questao);
		tx.commit();
	}

	/**
	 * Devolve uma questão de submissão de arquivo com o id fornecido.
	 * 
	 * @param id
	 * @return QuestaoDeSubmissaoDeArquivo
	 */
	public QuestaoDeSubmissaoDeArquivo carrega(Long id) {
		return (QuestaoDeSubmissaoDeArquivo) this.session.load(
				QuestaoDeSubmissaoDeArquivo.class, id);
	}

	/**
	 * Atualiza a questão fornecida no banco de dados. 
	 * 
	 * @param questao
	 */
	public void atualiza(QuestaoDeSubmissaoDeArquivo questao) {
		Transaction tx = session.beginTransaction();
		this.session.update(questao);
		tx.commit();
	}

	/**
	 * Remove a questão fornecida do banco de dados.
	 * 
	 * @param questao
	 */
	public void remove(QuestaoDeSubmissaoDeArquivo questao) {
		Transaction tx = session.beginTransaction();
		this.session.delete(questao);
		tx.commit();
	}
	
	/**
	 * Devolve uma QuestaoDeSubmissaoDeArquivo com o id fornecido, se existir. 
	 * Caso contrário, retorna null. 
	 * 
	 * @param id
	 * @return QuestaoDeSubmissaoDeArquivo
	 */
	public QuestaoDeSubmissaoDeArquivo buscaPorId(Long id) {
		return (QuestaoDeSubmissaoDeArquivo) session
				.createCriteria(QuestaoDeSubmissaoDeArquivo.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
	}

	public void recarrega(QuestaoDeSubmissaoDeArquivo questao) {
		session.refresh(questao);
	}
}
