package br.usp.ime.academicdevoir.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.usp.ime.academicdevoir.dao.QuestaoDeCodigoDao;
import br.usp.ime.academicdevoir.dao.TagDao;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.QuestaoDeCodigo;
import br.usp.ime.academicdevoir.entidade.Tag;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

public class QuestoesDeCodigoControllerTeste {

    private QuestoesDeCodigoController questoesC;
    private QuestaoDeCodigoDao dao;

    private MockResult result;

    private JSR303MockValidator validator;

    private UsuarioSession usuarioSession;
    private TagDao tagDao;

    @Before
    public void SetUp() {       
        Professor professor = new Professor();
        professor.setPrivilegio(Privilegio.ADMINISTRADOR);
        
        usuarioSession = new UsuarioSession();
        usuarioSession.setUsuario(professor);
    
        dao = mock(QuestaoDeCodigoDao.class);
        tagDao = mock(TagDao.class);
        result = spy(new MockResult());
        validator = spy(new JSR303MockValidator());
        questoesC = new QuestoesDeCodigoController(dao, tagDao, result,
                validator, usuarioSession);
        
        when(tagDao.buscaPeloNome(any(String.class))).thenReturn(new Tag("tagQualquer"));
    }

    @Test
    public void testeAdiciona() {
        QuestaoDeCodigo questao = new QuestaoDeCodigo();
        questoesC.cadastra(questao, new String("tagQualquer"));
    
        verify(validator).validate(questao);
        verify(validator).onErrorUsePageOf(QuestoesController.class);
        verify(dao).salva(questao);
        verify(result).redirectTo(questoesC);
    }

    @Test
    public void testeAtualiza() {
        QuestaoDeCodigo questao = new QuestaoDeCodigo();
        questoesC.altera(questao, new String("tagQualquer"));
        
        verify(validator).validate(questao);
        verify(validator).onErrorUsePageOf(QuestoesDeCodigoController.class);
        verify(dao).atualiza(questao);
        verify(result).redirectTo(questoesC);
    }
    
    @Test
    public void testeRemove() {
        QuestaoDeCodigo temp = new QuestaoDeCodigo();
        when(dao.carrega(0L)).thenReturn(temp);
        questoesC.remove(0L);
        
        verify(dao).remove(temp);
        verify(result).redirectTo(questoesC);
    }
}