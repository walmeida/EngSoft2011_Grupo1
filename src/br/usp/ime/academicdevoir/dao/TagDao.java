package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Tag;

@Component
public class TagDao {

	private final Session session;

	public TagDao(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Devolve uma lista com todas as tags cadastradas no banco
	 * de dados.
	 * 
	 * @return List<Tag>
	 */
	public List<Tag> listaTudo() {
		return this.session.createCriteria(Tag.class).list();
	}

	/**
	 * Cadastra a tag fornecida no banco de dados.
	 * 
	 * @param tag
	 */
	public void salva(Tag tag) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(tag);
		tx.commit();
	}

	/**
	 * Devolve uma tag com o id fornecido.
	 * 
	 * @param id
	 * @return Tag
	 */
	public Tag carrega(Long id) {
		return (Tag) this.session.load(Tag.class, id);
	}

	/**
	 * Atualiza a tag fornecida no banco de dados.
	 * 
	 * @param tag
	 */
	public void atualiza(Tag tag) {
		Transaction tx = session.beginTransaction();
		this.session.update(tag);
		tx.commit();
	}

	/**
	 * Remove a tag fornecida do banco de dados.
	 * 
	 * @param tag
	 */
	public void remove(Tag tag) {
		Transaction tx = session.beginTransaction();
		this.session.delete(tag);
		tx.commit();
	}

	public void recarrega(Tag tag) {
		session.refresh(tag);
	}

	public Tag buscaPeloNome(String nome) {
		return (Tag) session.createCriteria(Tag.class).add(Restrictions.eq("nome", nome)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<String> autoCompletar(String nomeDaTag) {
		return session.createCriteria(Tag.class)
				.add(Restrictions.ilike("nome", nomeDaTag, MatchMode.ANYWHERE))
				.list();
	}
}