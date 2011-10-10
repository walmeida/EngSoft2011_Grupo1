package br.usp.ime.academicdevoir.teste;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.controller.AlunosController;
import br.usp.ime.academicdevoir.dao.AlunoDao;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Aluno;

import static org.junit.Assert.assertEquals;

public class AlunosControllerTeste {
    private AlunosController alunoC;
    private Result result;
    private AlunoDao alunoDao;
    private DisciplinaDao disciplinaDao;
    private TurmaDao turmaDao;
    
    @Before
    public void SetUp() {
        result = mock(Result.class);
        alunoDao = mock(AlunoDao.class);
        disciplinaDao = mock(DisciplinaDao.class);
        turmaDao = mock(TurmaDao.class);
        alunoC = new AlunosController(result, alunoDao, disciplinaDao, turmaDao);
        when(result.redirectTo(AlunosController.class)).thenReturn(alunoC);
    }

    @Test
    public void testeCadastra() {
        Aluno novo = new Aluno();
        novo.setId(0L);
        alunoC.cadastra(novo);
        verify(alunoDao).salvaAluno(novo);
        verify(result).redirectTo(AlunosController.class);
    }

    @Test
    public void testeAltera() {
        Aluno a = new Aluno();
        a.setId(0L);
        when(alunoDao.carrega(0L)).thenReturn(a);
        alunoC.altera(0L, "novo nome", "novo email", "nova senha");
        assertEquals(a.getNome(), "novo nome");
        assertEquals(a.getEmail(), "novo email");
        assertEquals(a.getSenha(), "nova senha");
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
