package br.usp.ime.academicdevoir.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Disciplina;
import br.usp.ime.academicdevoir.entidade.Turma;

@Component
public class DisciplinaDao {

	private final Session session;
	
	public DisciplinaDao(Session session) {
		this.session = session;
	}

	/**
	 * Cadastra a disciplina fornecida no banco de dados.
	 * 
	 * @param disciplina
	 */
	public void salvaDisciplina(Disciplina disciplina) {
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
		Transaction tx = session.beginTransaction();
		session.delete(disciplina);
		tx.commit();
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
	public List<Disciplina> listaTudo() {
		String nome = "SELECT p FROM Disciplina p";
		Query query = session.createQuery(nome);
		List<Disciplina> listaDeDisciplinas = query.list();
		return listaDeDisciplinas;
	}

}
