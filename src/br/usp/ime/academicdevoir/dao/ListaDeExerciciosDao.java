package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.Turma;

@Component
public class ListaDeExerciciosDao {

	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Session session;

	public ListaDeExerciciosDao(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Devolve uma lista com todas as listas de exercícios cadastradas no banco
	 * de dados.
	 * 
	 * @return List<ListaDeExercicios>
	 */
	public List<ListaDeExercicios> listaTudo() {
		return this.session.createCriteria(ListaDeExercicios.class).list();
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Devolve uma lista com todas as listas de exercícios de uma determinada turma.
	 * 
	 * @return List<ListaDeExercicios>
	 */
	public List<ListaDeExercicios> listaDeTurma(Turma turma) {
		List<ListaDeExercicios> listaDeExercicios = this.session.createCriteria(ListaDeExercicios.class)
        .add(Restrictions.eq("turma", turma))
        .list();
		return listaDeExercicios;
	}

	/**
	 * Cadastra a lista fornecida no banco de dados.
	 * 
	 * @param lista
	 */
	public void salva(ListaDeExercicios lista) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(lista);
		tx.commit();
	}

	/**
	 * Devolve uma lista de exercícios com o id fornecido.
	 * 
	 * @param id
	 * @return ListaDeExercicios
	 */
	public ListaDeExercicios carrega(Long id) {
		return (ListaDeExercicios) this.session.load(ListaDeExercicios.class,
				id);
	}

	/**
	 * Atualiza a lista fornecida no banco de dados.
	 * 
	 * @param lista
	 */
	public void atualiza(ListaDeExercicios lista) {
		Transaction tx = session.beginTransaction();
		this.session.update(lista);
		tx.commit();
	}

	/**
	 * Remove a lista fornecida do banco de dados.
	 * 
	 * @param lista
	 */
	public void remove(ListaDeExercicios lista) {
		Transaction tx = session.beginTransaction();
		this.session.delete(lista);
		tx.commit();
	}

	public void recarrega(ListaDeExercicios lista) {
		session.refresh(lista);
	}
}
