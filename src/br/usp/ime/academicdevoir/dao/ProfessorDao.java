package br.usp.ime.academicdevoir.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Professor;

@Component
public class ProfessorDao {

	private final Session session;

	public ProfessorDao(Session session) {
		this.session = session;
	}

	public void salvaProfessor(Professor professor) {
		Transaction tx = session.beginTransaction();
		session.save(professor);
		tx.commit();
	}

	public void alteraProfessor(Professor professor) {
		Transaction tx = session.beginTransaction();
		session.update(professor);
		tx.commit();
	}

	public void removeProfessor(Professor professor) {
		Transaction tx = session.beginTransaction();
		session.delete(professor);
		tx.commit();
	}

	public Professor carregaPelaId(Long id) {
		return (Professor) session.load(Professor.class, id);
	}

    @SuppressWarnings("unchecked")
	public List<Professor> getLista() {
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
