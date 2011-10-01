package br.usp.ime.academicdevoir.controller;

import java.util.List;

import com.sun.xml.internal.ws.wsdl.writer.UsingAddressing;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.Professor;
import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Resource
public class TurmasController {
    private final Result result;
    private TurmaDao turmaDao;
    private DisciplinaDao disciplinaDao;

    public TurmasController(Result result, TurmaDao turmaDao, DisciplinaDao disciplinaDao) {
        this.result = result;
        this.turmaDao = turmaDao;
        this.disciplinaDao = disciplinaDao;
    }

    @Path("/turmas/home/{id}")
    public void home(Long id) {
        Turma turma = turmaDao.carregaPelaId(id);
        result.include("turma", turma);
    }

    public void menu() {

    }

    public void listaTurmasDoProfessor(Professor professor) {
        List<Turma> listaDeTurmas = turmaDao
                .buscaTurmasDoProfessor(professor);
        result.include("listaDeTurmas", listaDeTurmas);
        result.redirectTo(TurmasController.class).lista();
    }

    public void listaTodas() {
        result.include("listaDeTurmas", turmaDao.getLista());
        result.redirectTo(TurmasController.class).lista();
    }

    public void lista() {

    }

    public void cadastro() {
        result.include("disciplinaDao", disciplinaDao);
    }

    public void cadastra(final Turma nova, Long idProfessor, Long idDisciplina) {
        turmaDao.salvaTurma(nova, idDisciplina, idProfessor);
        result.redirectTo(TurmasController.class).listaTurmasDoProfessor(nova.getProfessor());
    }

    public void alteracao(Long id) {
        Turma turma = turmaDao.carregaPelaId(id);
        result.include("turma", turma);
    }

    public void altera(Long id, String novoNome) {
        Turma t = turmaDao.carregaPelaId(id);
        if (!novoNome.equals(""))
            t.setNome(novoNome);
        turmaDao.alteraTurma(t);
        result.redirectTo(TurmasController.class).listaTurmasDoProfessor(t.getProfessor());
    }

    public void remocao() {
    }

    /**
     * Remove uma turma do banco de dados com o id fornecido.
     * 
     * @param id
     */
    public void remove(final Long id) {
        Turma turma = turmaDao.carregaPelaId(id);
        turmaDao.removeTurma(turma);
        result.redirectTo(TurmasController.class).listaTurmasDoProfessor(turma.getProfessor());
    }
}
