package br.usp.ime.academicdevoir.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Aluno;

@Component
public class AlunoDao {

	private final Session session;

	public AlunoDao(Session session) {
		this.session = session;
	}

	public void salvaAluno(Aluno aluno) {
		Transaction tx = session.beginTransaction();
		session.save(aluno);
		tx.commit();
	}

	public void alteraAluno(Aluno aluno) {
		Transaction tx = session.beginTransaction();
		session.update(aluno);
		tx.commit();
	}

	public void removeAluno(Aluno aluno) {
		Transaction tx = session.beginTransaction();
		session.delete(aluno);
		tx.commit();
	}
	
	public Aluno carregaPelaId(Long id) {
		return (Aluno) session.load(Aluno.class, id);
	}
	
	
	public List<Aluno> getLista() {
		String nome = "SELECT p FROM Aluno p";
		Query query = session.createQuery(nome);
		List<Aluno> listaDeAlunos = query.list();
		return listaDeAlunos;
	}
	

}
