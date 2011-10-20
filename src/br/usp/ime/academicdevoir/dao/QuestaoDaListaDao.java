package br.usp.ime.academicdevoir.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.usp.ime.academicdevoir.entidade.QuestaoDaLista;

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
}
