package br.usp.ime.academicdevoir.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Disciplina;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.Turma;

@Component
public class TurmaDao {

	private final Session session;

	public TurmaDao(Session session) {
		this.session = session;
	}

	public void salvaTurma(Turma turma, Long idDaDisciplina, Long idDoProfessor) {
		Disciplina d = (Disciplina) session.load(Disciplina.class, idDaDisciplina);
		Professor p = (Professor) session.load(Professor.class, idDoProfessor);
		turma.setDisciplina(d);
		turma.setProfessor(p);
		Transaction tx = session.beginTransaction();
		session.save(turma);
		tx.commit();
	}

	public void alteraTurma(Turma turma) {
		Transaction tx = session.beginTransaction();
		session.update(turma);
		tx.commit();
	}

	public void removeTurma(Turma turma) {
		Transaction tx = session.beginTransaction();
		session.delete(turma);
		tx.commit();
	}
	
	public Turma carregaPelaId(Long id) {
		return (Turma) session.load(Turma.class, id);
	}
	
    @SuppressWarnings("unchecked")
	public List<Turma> getLista() {
		/*String nome = "SELECT id, nome FROM Turma p";
		Query query = session.createQuery(nome);
		List<Turma> listaDeTurmas = query.list(); */
        List<Turma> listaDeTurmas = session.createCriteria(Turma.class).list();
		return listaDeTurmas;
	}

    @SuppressWarnings("unchecked")
    public List<Turma> buscaTurmasDoProfessor(Professor professor) {
        return session.createCriteria(Turma.class)
        .add(Restrictions.like("professor", professor)).addOrder(Order.asc("disciplina"))
        .list();
    }
}
