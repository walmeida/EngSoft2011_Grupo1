package br.usp.ime.academicdevoir.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.Disciplina;
import br.usp.ime.academicdevoir.entidade.Turma;

@Component
public class DisciplinaDao {

	private final Session session;

	public DisciplinaDao(Session session) {
		this.session = session;
	}

	public void salvaDisciplina(Disciplina disciplina) {
		Transaction tx = session.beginTransaction();
		session.save(disciplina);
		tx.commit();
	}

	public void alteraDisciplina(Disciplina disciplina) {
		Transaction tx = session.beginTransaction();
		session.update(disciplina);
		tx.commit();
	}

	public void removeDisciplina(Disciplina disciplina) {
		Transaction tx = session.beginTransaction();
		session.delete(disciplina);
		tx.commit();
	}
	
	public Disciplina carregaPelaId(Long id) {
		return (Disciplina) session.load(Disciplina.class, id);
	}
	
	
	public List<Disciplina> getLista() {
		String nome = "SELECT p FROM Disciplina p";
		Query query = session.createQuery(nome);
		List<Disciplina> listaDeDisciplinas = query.list();
		return listaDeDisciplinas;
	}

}
