package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.ListaDeRespostas;

@Component
public class ListaDeRespostasDao {
	private final Session session;

	public ListaDeRespostasDao(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Devolve uma lista com todas as listas de respostas da lista de exercícios fornecida.
	 * @return List<ListaDeRespostas>
	 */
	public List<ListaDeRespostas> listaRespostasDaLista(ListaDeExercicios lista) {
		return this.session.createCriteria(ListaDeRespostas.class)
				.add(Restrictions.eq("listaDeExercicios", lista)).list();
	}

	/**
	 * Cadastra a lista fornecida no banco de dados.
	 * 
	 * @param lista
	 */
	public void salva(ListaDeRespostas lista) {
		Transaction tx = session.beginTransaction();
		session.save(lista);
		tx.commit();
	}

	/**
	 * Devolve uma lista de respostas com o id fornecido.
	 * 
	 * @param id
	 * @return ListaDeRespostas
	 */
	public ListaDeRespostas carrega(Long id) {
		return (ListaDeRespostas) this.session.load(ListaDeRespostas.class, id);
	}

	/**
	 * Atualiza a lista fornecida no banco de dados.
	 * 
	 * @param lista
	 */
	public void atualiza(ListaDeRespostas lista) {
		Transaction tx = session.beginTransaction();
		this.session.update(lista);
		tx.commit();
	}

	/**
	 * Remove a lista fornecida do banco de dados.
	 * 
	 * @param lista
	 */
	public void remove(ListaDeRespostas lista) {
		Transaction tx = session.beginTransaction();
		this.session.delete(lista);
		tx.commit();
	}

	public void recarrega(ListaDeRespostas lista) {
		session.refresh(lista);
	}
}
