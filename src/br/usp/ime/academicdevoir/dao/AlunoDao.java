package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.infra.Criptografia;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Component
public class AlunoDao {

	private final Session session;
	private UsuarioSession usuarioAtual;

	public AlunoDao(Session session, UsuarioSession usuarioAtual) {
		this.session = session;
		this.usuarioAtual = usuarioAtual;
	}

	public void salvaAluno(Aluno aluno) {
			// Criptografando a senha
			aluno.setSenha(new Criptografia().geraMd5(aluno.getSenha()));
	
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

	@SuppressWarnings("unchecked")
	public List<Aluno> getLista() {
		String nome = "SELECT a FROM Aluno a";
		Query query = session.createQuery(nome);
		List<Aluno> listaDeAlunos = query.list();
		return listaDeAlunos;
	}

	public void inscreve(Long idTurma) {
		Transaction tx = session.beginTransaction();
		Aluno a = (Aluno) usuarioAtual.getUsuario();
		Turma t = (Turma) session.load(Turma.class, idTurma);
		a.getTurmas().add(t);
		t.getAlunos().add(a);
		session.update(a);
		session.update(t);
		tx.commit();
	}

//	@SuppressWarnings("unchecked")
//	public List<Turma> getTurmas() {
//		String texto = "SELECT t.nome,t.id FROM Turma t INNER JOIN Turma_Aluno WHERE aluno_id=8";
////				+ usuarioAtual.getUsuario().getId();
//		Query query = session.createQuery(texto);
//		List<Turma> listaDeTurmas = query.list();
//		return listaDeTurmas;
//	}

}
