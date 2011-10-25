package br.usp.ime.academicdevoir.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.Usuario;

@Component
public class ProfessorDao {

	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Session session;

	public ProfessorDao(Session session) {
		this.session = session;
	}

	/**
	 * Cadastra o professor fornecido no banco de dados.
	 * 
	 * @param professor
	 */
	@SuppressWarnings("unchecked")
	public void salvaProfessor(Professor professor) {
		String login = professor.getLogin();
	    List<Usuario> listaDeUsuarios = session.createCriteria(Usuario.class)
                .add(Restrictions.like("login", login, MatchMode.EXACT))
                .list();
        
	    if (listaDeUsuarios.size() != 0) return;
	        
	    Transaction tx = session.beginTransaction();
	    session.save(professor);
		tx.commit();
	}

	/**
	 * Atualiza o professor fornecido no banco de dados.
	 * 
	 * @param professor
	 */
	public void atualizaProfessor(Professor professor) {
		Transaction tx = session.beginTransaction();
		session.update(professor);
		tx.commit();
	}

	/**
	 * Remove o professor fornecido do banco de dados.
	 * 
	 * @param professor
	 */
	public void removeProfessor(Professor professor) {
		Transaction tx = session.beginTransaction();
		session.delete(professor);
		tx.commit();
	}

	/**
	 * Devolve um Professor com o id fornecido.
	 * 
	 * @param id
	 * @return Professor
	 */
	public Professor carrega(Long id) {
		return (Professor) session.load(Professor.class, id);
	}

    @SuppressWarnings("unchecked")
    /**
	 * Devolve uma lista com todos os professore cadastrados no banco de dados.
	 * 
	 * @return List<Professor>
	 */
	public List<Professor> listaTudo() {
		String nome = "SELECT p FROM Professor p";
		Query query = session.createQuery(nome);
		List<Professor> listaDeProfessores = query.list();
		return listaDeProfessores;
	}

	public void alteraTipo(Long id) {
		try {
			Connection conexao = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/engsoft", "root", "root123");
			String s = "UPDATE Usuario SET DTYPE='professor' WHERE id=7";
			PreparedStatement comando = conexao.prepareStatement(s);
			comando.execute();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
