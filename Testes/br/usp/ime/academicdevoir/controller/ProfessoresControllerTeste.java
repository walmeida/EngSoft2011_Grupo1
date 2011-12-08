package br.usp.ime.academicdevoir.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import br.usp.ime.academicdevoir.infra.Criptografia;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

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
    /**
	 * @uml.property  name="usuarioSession"
	 * @uml.associationEnd  readOnly="true"
	 */
    private UsuarioSession usuarioSession;
	private Professor professor;

    @Before
    public void SetUp() {
		Professor admin = new Professor();
		admin.setId(0L);
		admin.setPrivilegio(Privilegio.ADMINISTRADOR);
		
		usuarioSession = new UsuarioSession();
		usuarioSession.setUsuario(admin);
		
        result = spy(new MockResult());
        professordao = mock(ProfessorDao.class);
        profC = new ProfessoresController(result, professordao, usuarioSession);
        
        professor = new Professor();
        professor.setId(0L);
        
        when(professordao.carrega(professor.getId())).thenReturn(professor);
    }

    @Test
    public void testeCadastra() {
    	professor.setSenha("senhadoprofessor");
        profC.cadastra(professor);
        verify(professordao).salvaProfessor(professor);
        verify(result).redirectTo(ProfessoresController.class);
    }
    
    @Test
    public void testeListaTurmas() {
    	profC.listaTurmas(this.professor.getId());
    	
    	Professor professor = result.included("professor");
    	assertNotNull(professor);
    }

    @Test
    public void testeAlteracao() {
    	profC.alteracao(this.professor.getId());
    	
    	Professor professor = result.included("professor");
    	assertNotNull(professor);
    }
    
    @Test
    public void testeAltera() {        
        profC.altera(professor.getId(), "novo nome", "novo email", "nova senha");
        assertEquals(professor.getNome(), "novo nome");
        assertEquals(professor.getEmail(), "novo email");
        assertEquals(professor.getSenha(), new Criptografia().geraMd5("nova senha"));
        verify(professordao).atualizaProfessor(professor);
        verify(result).redirectTo(ProfessoresController.class);
    }

    @Test
    public void testeRemove() {
        profC.remove(professor.getId());
        verify(professordao).removeProfessor(professor);
        verify(result).redirectTo(ProfessoresController.class);
    }
    
    @Test
    public void testeMudartipo() {
    	profC.mudarTipo(professor.getId());
    	
    	verify(professordao).alteraTipo(professor.getId());
    	verify(result).redirectTo(ProfessoresController.class);
    }
}
