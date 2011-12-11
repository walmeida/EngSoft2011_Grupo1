package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Questao;

@Component
public class QuestaoDao {

	/**
	 * @uml.property name="session"
	 * @uml.associationEnd multiplicity="(0 -1)"
	 *                     elementType="br.usp.ime.academicdevoir.entidade.Questao"
	 */
	private final Session session;

	public QuestaoDao(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Devolve uma lista com todas as questões cadastradas no banco de dados.
	 * 
	 * @return List<Questao>
	 */
	public List<Questao> listaTudo() {
		return this.session.createCriteria(Questao.class).list();
	}
	
	/**
	 * Devolve uma lista com todas as questões cadastradas de acordo com a página.
	 * 
	 * @return List<Questao>
	 */
	private Criteria listaFiltrada(String filtro) {
		Criteria criteria = this.session.createCriteria(Questao.class);
		if (filtro != null && filtro != "")
			criteria.createCriteria("tags").add(Restrictions.eq("nome", filtro));		
		return criteria;
	}

	/**
	 * Devolve o tamanho da lista com todas as questões cadastradas no banco de
	 * dados.
	 * 
	 * @return List<Questao>
	 */
	public Integer tamanhoDaLista(String filtro) {
		return listaFiltrada(filtro).list().size();
	}

	@SuppressWarnings("unchecked")
	/**
	 * Devolve uma lista com todas as questões cadastradas de acordo com a página.
	 * 
	 * @return List<Questao>
	 */
	public List<Questao> listaPaginada(Integer primeiro, Integer numRegistros,
			String filtro) {
		Criteria criteria = listaFiltrada(filtro);
		criteria.setFirstResult(primeiro);
		criteria.setMaxResults(numRegistros);
		return criteria.list();
	}

	/**
	 * Devolve uma questão com o id fornecido.
	 * 
	 * @param id
	 * @return Questao
	 */
	public Questao carrega(Long id) {
		return (Questao) this.session.load(Questao.class, id);
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
	 * Devolve uma Questao com o id fornecido, se existir. Caso contrário,
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
