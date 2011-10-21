package br.usp.ime.academicdevoir.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl.Subcriteria;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.Criptografia;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Component
public class AlunoDao {

	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Session session;

	public AlunoDao(Session session) {
		this.session = session;
	}

	/**
	 * Cadastra o aluno fornecido no banco de dados.
	 * 
	 * @param aluno
	 */
	@SuppressWarnings("unchecked")
	public void salvaAluno(Aluno aluno) {
			// Criptografando a senha
	    String login = aluno.getLogin();
	    List<Usuario> listaDeUsuarios = session.createCriteria(Usuario.class)
                .add(Restrictions.like("login", login, MatchMode.EXACT))
                .list();
        
	    if (listaDeUsuarios.size() != 0) return;
	        
		aluno.setSenha(new Criptografia().geraMd5(aluno.getSenha()));
	    Transaction tx = session.beginTransaction();
		session.save(aluno);
		tx.commit();
	}

	/**
	 * Atualiza o aluno fornecido no banco de dados.
	 * 
	 * @param aluno
	 */
	public void atualizaAluno(Aluno aluno) {
		Transaction tx = session.beginTransaction();
		session.update(aluno);
		tx.commit();
	}

	/**
	 * Remove o aluno fornecido do banco de dados.
	 * 
	 * @param aluno
	 */
	public void removeAluno(Aluno aluno) {
		Transaction tx = session.beginTransaction();
		session.delete(aluno);
		tx.commit();
	}

	/**
	 * Devolve um Aluno com o id fornecido.
	 * 
	 * @param id
	 * @return Aluno
	 */
	public Aluno carrega(Long id) {
		return (Aluno) session.load(Aluno.class, id);
	}

	@SuppressWarnings("unchecked")
	/**
	 * Devolve uma lista com todos os alunos cadastrados no banco de dados.
	 * 
	 * @return List<Aluno>
	 */
	public List<Aluno> listaTudo() {
		String nome = "SELECT a FROM Aluno a";
		Query query = session.createQuery(nome);
		List<Aluno> listaDeAlunos = query.list();
		return listaDeAlunos;
	}

	/**
	 * Inscreve o aluno na turma com id fornecido.
	 * 
	 * @param idTurma
	 */
	public void inscreve(Aluno aluno, Turma turma) {
		Transaction tx = session.beginTransaction();
		aluno.getTurmas().add(turma);
		turma.getAlunos().add(aluno);
		session.update(aluno);
		session.update(turma);
		tx.commit();
	}

	/**
	 * Remove a matricula do aluno na turma especificada
	 * @param aluno
	 * @param turma
	 */
    public void removeMatricula(Aluno aluno, Turma turma) {
        Transaction tx = session.beginTransaction();
        aluno.getTurmas().remove(turma);
        turma.getAlunos().remove(aluno);
        session.update(aluno);
        session.update(turma);
        tx.commit();
    }


}
