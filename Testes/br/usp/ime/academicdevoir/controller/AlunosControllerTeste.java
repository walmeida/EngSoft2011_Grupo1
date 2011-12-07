package br.usp.ime.academicdevoir.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.dao.AlunoDao;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.Disciplina;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.infra.Criptografia;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

public class AlunosControllerTeste {
    /**
	 * @uml.property  name="alunoC"
	 * @uml.associationEnd  
	 */
    private AlunosController alunoC;
    /**
	 * @uml.property  name="result"
	 * @uml.associationEnd  
	 */
    private MockResult result;
    /**
	 * @uml.property  name="alunoDao"
	 * @uml.associationEnd  
	 */
    private AlunoDao alunoDao;
	/**
	 * @uml.property  name="turmaDao"
	 * @uml.associationEnd  
	 */
	private TurmaDao turmaDao;
    /**
	 * @uml.property  name="disciplinaDao"
	 * @uml.associationEnd  
	 */
    private DisciplinaDao disciplinaDao;
    /**
	 * @uml.property  name="usuarioSession"
	 * @uml.associationEnd  readOnly="true"
	 */
    private UsuarioSession usuarioSession;
	private Aluno aluno;
	private Turma turma;
    
    @Before
    public void SetUp() {		
		Professor professor = new Professor();
		professor.setPrivilegio(Privilegio.ADMINISTRADOR);
		
		usuarioSession = new UsuarioSession();
		usuarioSession.setUsuario(professor);

    	result = spy(new MockResult());
        alunoDao = mock(AlunoDao.class);
        disciplinaDao = mock(DisciplinaDao.class);
        turmaDao = mock(TurmaDao.class);
        alunoC = new AlunosController(result, alunoDao, disciplinaDao, turmaDao, usuarioSession);
        
        aluno = new Aluno();
        aluno.setId(0L);
        
        turma = new Turma();
        turma.setId(0L);
        
        when(alunoDao.listaTudo()).thenReturn(new ArrayList<Aluno>());
        when(alunoDao.carrega(aluno.getId())).thenReturn(aluno);        
        when(disciplinaDao.listaTudo()).thenReturn(new ArrayList<Disciplina>());
        when(turmaDao.carrega(turma.getId())).thenReturn(turma);
    }

    @Test
    public void testeLista() {
    	alunoC.lista();
    	List<Aluno> lista = result.included("listaDeAlunos");
    	assertNotNull(lista);
    }
    
    @Test
    public void testeListaTurmas() {
    	alunoC.listaTurmas(this.aluno.getId());
    	Aluno aluno = result.included("aluno");
    	assertNotNull(aluno);
    }
    
    @Test
    public void testeCadastra() {
        Aluno novo = new Aluno();
        novo.setId(0L);
        novo.setSenha("senha");
        alunoC.cadastra(novo);
        verify(alunoDao).salvaAluno(novo);
        verify(result).redirectTo(AlunosController.class);
    }

    @Test
    public void testeAlteracao() {
    	alunoC.alteracao(this.aluno.getId());
    	Aluno aluno = result.included("aluno");
    	assertNotNull(aluno);
    }
    
    @Test
    public void testeAtualiza() {
        alunoC.altera(0L, "novo nome", "novo email", "nova senha");
        assertEquals(aluno.getNome(), "novo nome");
        assertEquals(aluno.getEmail(), "novo email");
        assertEquals(aluno.getSenha(), new Criptografia().geraMd5("nova senha"));
        verify(alunoDao).atualizaAluno(aluno);
        verify(result).redirectTo(AlunosController.class);
    }
    
    @Test
    public void testeRemove() {
        alunoC.remove(0L);
        verify(alunoDao).removeAluno(aluno);
        verify(result).redirectTo(AlunosController.class);
    }
    
    @Test    
    public void testeMatricula() {
    	alunoC.matricula();
    	List<Disciplina> lista = result.included("listaDeDisciplinas");
    	assertNotNull(lista);
    }
    
    @Test
    public void testeInscreve() {
    	alunoC.inscreve(aluno.getId(), turma.getId());
    	verify(alunoDao).inscreve(aluno, turma);
    	verify(result).redirectTo(AlunosController.class);
    }
    
    @Test
    public void testeRemoveMatricula() {
    	alunoC.removeMatricula(aluno.getId(), turma.getId());
    	verify(alunoDao).carrega(aluno.getId());
    	verify(turmaDao).carrega(turma.getId());
    	verify(alunoDao).removeMatricula(aluno, turma);
    	verify(result).redirectTo(AlunosController.class);
    }
}
