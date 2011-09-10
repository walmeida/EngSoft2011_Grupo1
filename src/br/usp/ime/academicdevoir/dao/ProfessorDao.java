package br.usp.ime.academicdevoir.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.sessao.CriadorDeSessao;

@Component
@ApplicationScoped
public class ProfessorDao {

	private final Session session;

	public ProfessorDao() {
		this.session = CriadorDeSessao.getSession();
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
	
	
	public List<Professor> getLista() {
		String nome = "SELECT p FROM Professor p";
		Query query = session.createQuery(nome);
		List<Professor> listaDeProfessores = query.list();
		return listaDeProfessores;
	}
	

}
