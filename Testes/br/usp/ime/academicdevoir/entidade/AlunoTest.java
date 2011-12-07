package br.usp.ime.academicdevoir.entidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hibernate.Transaction;
import org.junit.Test;

import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.Turma;

public class AlunoTest extends TestaBase {
    
	@Test
	public void testeDeCadastro() {
		final Turma t = new Turma();
		final Professor p1 = new Professor();
		final Turma t2 = new Turma();
		final Aluno a1 = new Aluno();
		
		a1.setNome("Vinicius G. de Rezende");
		a1.setLogin("rezende");
		a1.setSenha("root");
		
		p1.setNome("Gerosa");
		p1.setEmail("gerosa@ime.usp.br");
		p1.setLogin("gerosa");
		
		t.setNome("MAC110-2001");
		t2.setNome("MAC122-2001");
		
		t.setProfessor(p1);
		p1.getTurmas().add(t);
		t2.setProfessor(p1);
		p1.getTurmas().add(t2);
		
		a1.getTurmas().add(t2);
		a1.getTurmas().add(t);
						
		t.getAlunos().add(a1);
		t2.getAlunos().add(a1);
		
		Transaction tx = session.beginTransaction();
		session.save(p1);
		session.save(t2);
		session.save(t);
		session.save(a1);
		tx.commit();
		
		verify(session).save(p1);
        verify(session).save(t2);
        verify(session).save(t);
        verify(session).save(a1);
        verify(tx).commit();
		
        when(session.load(Aluno.class, p1.getId())).thenReturn(p1);
		final Professor p2 = (Professor) session.load(Aluno.class, p1.getId());
        assertEquals("Gerosa", p2.getNome());
	}
	
	@Test
    public void testeDeRemocao(){
		final Aluno a1 = new Aluno();
		a1.setNome("Prateleira");
		a1.setEmail("prateleira@ime.usp.br");
		a1.setLogin("pratelozovski");
		
		Transaction tx = session.beginTransaction();
		session.delete(a1);
		tx.commit();
		
		verify(session).delete(a1);
		verify(tx).commit();
		
		when(session.load(Aluno.class, a1.getId())).thenReturn(null);
		final Aluno a2 = (Aluno) session.load(Aluno.class, a1.getId());
        assertNull("Prateleira", a2);
    }
}
