package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Turma;

@Component
public class TurmaDao {

	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Session session;

	public TurmaDao(Session session) {
		this.session = session;
	}

	/**
	 * Cadastra a turma no banco de dados.
	 * 
	 * @param turma
	 */
	public void salvaTurma(Turma turma) {
		Transaction tx = session.beginTransaction();
		session.save(turma);
		tx.commit();
	}
	
	/**
	 * Atualiza a turma fornecida no banco de dados.
	 *  
	 * @param turma
	 */
	public void atualizaTurma(Turma turma) {
		Transaction tx = session.beginTransaction();
		session.update(turma);
		tx.commit();
	}

	/**
	 * Remove a turma fornecida do banco de dados.
	 * 
	 * @param turma
	 */
	public void removeTurma(Turma turma) {
		Transaction tx = session.beginTransaction();
		session.delete(turma);
		tx.commit();
	}

	/**
	 * Devolve uma turma com o id fornecido.
	 * 
	 * @param id
	 * @return Turma
	 */
	public Turma carrega(Long id) {
		return (Turma) session.load(Turma.class, id);
	}
	
    @SuppressWarnings("unchecked")
    /**
	 * Devolve uma lista com todas as turmas cadastradas no banco de dados.
	 * 
	 * @return List<Turma>
	 */
	public List<Turma> listaTudo() {
        List<Turma> listaDeTurmas = session.createCriteria(Turma.class).list();
		return listaDeTurmas;
	}

    /**
     * Carrega os dados da questão fornecida a partir do banco de dados.
     * @param turma
     */
	public void recarrega(Turma turma) {
		session.refresh(turma);
	}
}
