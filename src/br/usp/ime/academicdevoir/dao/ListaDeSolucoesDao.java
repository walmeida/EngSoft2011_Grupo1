package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.ListaDeSolucoes;


@Component
public class ListaDeSolucoesDao {
	private final Session session;

	public ListaDeSolucoesDao(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Devolve uma lista com todas as listas de solucoes da lista de exercícios fornecida.
	 * @return List<ListaDeSolucoes>
	 */
	public List<ListaDeSolucoes> listaSolucoesDaLista(ListaDeExercicios lista) {
		return this.session.createCriteria(ListaDeSolucoes.class)
				.add(Restrictions.eq("listaDeExercicios", lista)).list();
	}

	/**
	 * Devolve a lista de solucoes referente a lista de exercícios
	 * com o id fornecido.
	 */
	public ListaDeSolucoes getSolucoes(Long idDaLista, Aluno aluno) {
		return (ListaDeSolucoes) this.session
				.createCriteria(ListaDeSolucoes.class)
				.add(Restrictions.eq("listaDeExercicios.id", idDaLista))
				.add(Restrictions.eq("aluno.id", aluno.getId())).uniqueResult();
	}

	/**
	 * Cadastra a lista fornecida no banco de dados.
	 * 
	 * @param lista
	 */
	public void salva(ListaDeSolucoes lista) {
		Transaction tx = session.beginTransaction();
		session.save(lista);
		tx.commit();
	}

	/**
	 * Devolve uma lista de respostas com o id fornecido.
	 * 
	 * @param id
	 * @return ListaDeSolucoes
	 */
	public ListaDeSolucoes carrega(Long id) {
		return (ListaDeSolucoes) this.session.load(ListaDeSolucoes.class, id);
	}

	/**
	 * Devolve uma ListaDeSolucoes com o id fornecido, se existir. Caso
	 * contrário, devolve null.
	 * 
	 * @param id
	 * @return ListaDeSolucoes
	 */
	public ListaDeSolucoes buscaPorId(Long id) {
		return (ListaDeSolucoes) session
				.createCriteria(ListaDeSolucoes.class)
				.add(Restrictions.idEq(id)).uniqueResult();
	}

	/**
	 * Atualiza a lista fornecida no banco de dados.
	 * 
	 * @param lista
	 */
	public void atualiza(ListaDeSolucoes lista) {
		Transaction tx = session.beginTransaction();
		this.session.update(lista);
		tx.commit();
	}

	/**
	 * Remove a lista fornecida do banco de dados.
	 * 
	 * @param lista
	 */
	public void remove(ListaDeSolucoes lista) {
		Transaction tx = session.beginTransaction();
		this.session.delete(lista);
		tx.commit();
	}

	public void recarrega(ListaDeSolucoes lista) {
		session.refresh(lista);
	}
}
