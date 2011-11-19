package br.usp.ime.academicdevoir.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.QuestaoDaLista;

@Component
public class QuestaoDaListaDao {

	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Session session;

	public QuestaoDaListaDao(Session session) {
		this.session = session;
	}

	/**
	 * Devolve uma questão de uma lista de exercícios com o id fornecido.
	 * 
	 * @param id
	 * @return QuestaoDaLista
	 */
	public Object carrega(Long id) {
		return this.session.load(QuestaoDaLista.class, id);
	}

	/**
	 * Remove a questão fornecida de uma lista de exercícios.
	 * 
	 * @param questao
	 */
	public void remove(QuestaoDaLista questao) {
		Transaction tx = session.beginTransaction();
		this.session.delete(questao);
		tx.commit();
	}

	public void recarrega(QuestaoDaLista questao) {
		session.refresh(questao);
	}
	
	/**
	 * Devolve a questao da lista referente a questao e lista de exercícios
	 * com o id fornecido.
	 */
	public QuestaoDaLista getQuestaoDaListaPorIds(Long idDaLista, Long idDaQuestao) {
		return (QuestaoDaLista) this.session
				.createCriteria(QuestaoDaLista.class)
				.add(Restrictions.eq("listaDeExercicios.id", idDaLista))
				.add(Restrictions.eq("questao.id", idDaQuestao)).uniqueResult();
	}
}
