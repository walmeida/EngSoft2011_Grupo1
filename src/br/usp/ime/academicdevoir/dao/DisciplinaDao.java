package br.usp.ime.academicdevoir.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Disciplina;

@Component
public class DisciplinaDao {

	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Session session;
	
	public DisciplinaDao(Session session) {
		this.session = session;
	}

	/**
	 * Cadastra a disciplina fornecida no banco de dados.
	 * 
	 * @param disciplina
	 */
	@SuppressWarnings("unchecked")
	public void salvaDisciplina(Disciplina disciplina) {
		String nome = disciplina.getNome();
	    List<Disciplina> listaDeDisciplinas = session.createCriteria(Disciplina.class)
                .add(Restrictions.like("nome", nome, MatchMode.EXACT))
                .list();
        
	    if (listaDeDisciplinas.size() != 0) return;
	    
		Transaction tx = session.beginTransaction();
		session.save(disciplina);
		tx.commit();
	}

	/**
	 * Atualiza a disciplina fornecida no banco de dados. 
	 * 
	 * @param disciplina
	 */
	public void atualizaDisciplina(Disciplina disciplina) {
		Transaction tx = session.beginTransaction();
		session.update(disciplina);
		tx.commit();
	}
	
	/**
	 * Remove a disciplina fornecida do banco de dados.
	 * 
	 * @param disciplina
	 */
	public void removeDisciplina(Disciplina disciplina) {
		try{
			Transaction tx = session.beginTransaction();
			session.delete(disciplina);
			tx.commit();
		} catch (Exception e) { 
    		return; /*Não foi possível remover a disciplina, pois tem alguma turma associada.*/
    	}
	}
	
	/**
	 * Devolve uma disciplina com o id fornecido.
	 * 
	 * @param id
	 * @return Disciplina
	 */
	public Disciplina carrega(Long id) {
		return (Disciplina) session.load(Disciplina.class, id);
	}
	
    @SuppressWarnings("unchecked")
    /**
	 * Devolve uma lista com todas as disciplinas cadastradas no banco de dados.
	 * 
	 * @return List<Disciplina>
	 */
   /* public List<Disciplina> listaTudo() {
		String nome = "SELECT p FROM Disciplina p";
		Query query = session.createQuery(nome);
		List<Disciplina> listaDeDisciplinas = query.list();
		return listaDeDisciplinas;
	}*/
    
    public List<Disciplina> listaTudo() {
		return this.session.createCriteria(Disciplina.class).list();
	}

}
