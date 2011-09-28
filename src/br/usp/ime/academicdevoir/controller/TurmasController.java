package br.usp.ime.academicdevoir.controller;

import java.util.List;

import com.sun.xml.internal.ws.wsdl.writer.UsingAddressing;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Resource
public class TurmasController {
    private final Result result;
    private TurmaDao turmaDao;
    private UsuarioSession usuarioSession;

    public TurmasController(Result result, TurmaDao turmaDao,
            UsuarioSession usuarioSession) {
        this.result = result;
        this.turmaDao = turmaDao;
        this.usuarioSession = usuarioSession;
    }

    public void home() {
    }

    public void menu() {

    }

    public void listaTurmasDoProfessor() {
        result.include("listaDeTurmas",
                turmaDao.buscaTurmasDoProfessor((Professor) usuarioSession
                        .getUsuario()));
        result.redirectTo(TurmasController.class).lista();
    }
    
    public void lista() {
        result.include("listaDeTurmas", turmaDao.getLista());
    }

    public void cadastro() {
    }

    public void cadastra(final Turma nova, Long idProfessor, Long idDisciplina) {
        turmaDao.salvaTurma(nova, idDisciplina, idProfessor);
        result.redirectTo(TurmasController.class).lista();
    }

    public void alteracao() {
    }

    public void altera(Long id, String novoNome) {
        Turma t = turmaDao.carregaPelaId(id);
		if (!novoNome.equals("")) t.setNome(novoNome);
        turmaDao.alteraTurma(t);
        result.redirectTo(TurmasController.class).lista();
    }

    public void remocao() {
    }

    public void remove(final Long id) {
        Turma turma = turmaDao.carregaPelaId(id);
        turmaDao.removeTurma(turma);
        result.redirectTo(TurmasController.class).lista();
    }
}
