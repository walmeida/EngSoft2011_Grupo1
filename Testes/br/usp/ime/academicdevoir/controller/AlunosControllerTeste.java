package br.usp.ime.academicdevoir.controller;

import static org.junit.Assert.assertEquals; 
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.dao.AlunoDao;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.infra.Criptografia;

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
    //private UsuarioSession usuarioSession;
    
    @Before
    public void SetUp() {
    	result = spy(new MockResult());
        alunoDao = mock(AlunoDao.class);
        disciplinaDao = mock(DisciplinaDao.class);
        turmaDao = mock(TurmaDao.class);
        alunoC = new AlunosController(result, alunoDao, disciplinaDao, turmaDao);
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
    public void testeAtualiza() {
        Aluno a = new Aluno();
        a.setId(0L);
        when(alunoDao.carrega(0L)).thenReturn(a);
        alunoC.altera(0L, "novo nome", "novo email", "nova senha");
        assertEquals(a.getNome(), "novo nome");
        assertEquals(a.getEmail(), "novo email");
        assertEquals(a.getSenha(), new Criptografia().geraMd5("nova senha"));
        verify(alunoDao).atualizaAluno(a);
        verify(result).redirectTo(AlunosController.class);
    }

    @Test
    public void testeRemove() {
        Aluno a = new Aluno();
        a.setId(0L);
        when(alunoDao.carrega(0L)).thenReturn(a);
        alunoC.remove(0L);
        verify(alunoDao).removeAluno(a);
        verify(result).redirectTo(AlunosController.class);
    }
}
