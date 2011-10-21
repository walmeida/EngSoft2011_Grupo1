package br.usp.ime.academicdevoir.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.controller.ProfessoresController;
import br.usp.ime.academicdevoir.dao.ProfessorDao;
import br.usp.ime.academicdevoir.entidade.Professor;

public class ProfessoresControllerTeste {
    /**
	 * @uml.property  name="profC"
	 * @uml.associationEnd  
	 */
    private ProfessoresController profC;
    /**
	 * @uml.property  name="result"
	 * @uml.associationEnd  
	 */
    private MockResult result;
    /**
	 * @uml.property  name="professordao"
	 * @uml.associationEnd  
	 */
    private ProfessorDao professordao;

    @Before
    public void SetUp() {
        result = spy(new MockResult());
        professordao = mock(ProfessorDao.class);
        profC = new ProfessoresController(result, professordao);
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
    public void testeAtualiza() {
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
