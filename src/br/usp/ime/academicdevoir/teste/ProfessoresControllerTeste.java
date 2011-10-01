package br.usp.ime.academicdevoir.teste;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.controller.ProfessoresController;
import br.usp.ime.academicdevoir.dao.ProfessorDao;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

public class ProfessoresControllerTeste {
    private ProfessoresController profC;
    private Result result;
    private ProfessorDao professordao;

    @Before
    public void SetUp() {
        result = mock(Result.class);
        professordao = mock(ProfessorDao.class);
        profC = new ProfessoresController(result, professordao);
        when(result.redirectTo(ProfessoresController.class)).thenReturn(profC);
    }

    @Test
    public void testeCadastra() {
        Professor novo = new Professor();
        novo.setId(0L);
        profC.cadastra(novo);
        verify(professordao).salvaProfessor(novo);
        verify(result).redirectTo(ProfessoresController.class);
    }

    @Test
    public void testeAltera() {
        Professor a = new Professor();
        a.setId(0L);
        when(professordao.carrega(0L)).thenReturn(a);
        profC.altera(0L, "novo nome", "novo email", "nova senha");
        assertEquals(a.getNome(), "novo nome");
        assertEquals(a.getEmail(), "novo email");
        assertEquals(a.getSenha(), "nova senha");
        verify(professordao).atualizaProfessor(a);
        verify(result).redirectTo(ProfessoresController.class);
    }

    @Test
    public void testeRemove() {
        Professor a = new Professor();
        a.setId(0L);
        when(professordao.carrega(0L)).thenReturn(a);
        profC.remove(0L);
        verify(professordao).removeProfessor(a);
        verify(result).redirectTo(ProfessoresController.class);
    }
}
