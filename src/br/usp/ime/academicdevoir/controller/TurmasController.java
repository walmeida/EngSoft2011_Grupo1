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
/**
 * Controlador de turmas.
 */
public class TurmasController {
    private final Result result;
    private TurmaDao turmaDao;
    private DisciplinaDao disciplinaDao;

	/**
	 * @param result para interação com o jsp da turma.
	 * @param turmaDao para interação com o banco de dados
	 * @param disciplinaDao para interação com o banco de dados 
	 */
    public TurmasController(Result result, TurmaDao turmaDao, DisciplinaDao disciplinaDao) {
        this.result = result;
        this.turmaDao = turmaDao;
        this.disciplinaDao = disciplinaDao;
    }

	/**
	 * Método associado à home page da turma com id fornecido.
	 * 
	 * @param id
	 */
    @Path("/turmas/home/{id}")
    public void home(Long id) {
        Turma turma = turmaDao.carrega(id);
        result.include("turma", turma);
    }

	/**
	 * Método associado ao menu da pagina da turma.
	 */
    public void menu() {

    }
    
    /**
	 * Devolve uma lista com todas as turmas de um dado professor.
	 */
    public void listaTurmasDoProfessor(Professor professor) {
        List<Turma> listaDeTurmas = turmaDao
                .buscaTurmasDoProfessor(professor);
        result.include("listaDeTurmas", listaDeTurmas);
        result.redirectTo(TurmasController.class).lista();
    }

    /**
	 * Devolve uma lista com todas as turmas cadastradas no banco de dados.
	 */
    public void listaTodas() {
        result.include("listaDeTurmas", turmaDao.listaTudo());
        result.redirectTo(TurmasController.class).lista();
    }

	/**
	 * Método associado ao .jsp que lista as turmas.
	 */
    public void lista() {

    }

	/**
     * Método está associado ao .jsp do formulário de cadastro de uma turma no sistema.
     */
    public void cadastro() {
        result.include("disciplinaDao", disciplinaDao);
    }

	/**
     * Cadastra uma turma nova no sistema.
     * 
     * @param nova
     */
    public void cadastra(final Turma nova, Long idProfessor, Long idDisciplina) {
        turmaDao.salvaTurma(nova, idDisciplina, idProfessor);
        result.redirectTo(TurmasController.class).listaTurmasDoProfessor(nova.getProfessor());
    }

	/**
	 * Método associado ao .jsp com formulário para alteração de cadastro de
	 * turma.
	 */
    public void alteracao(Long id) {
        Turma turma = turmaDao.carrega(id);
        result.include("turma", turma);
    }

	/**
	 * Altera uma turma no banco de dados com o id fornecido e set o nome
	 * da turma para novoNome.
	 * 
	 * @param id
	 */
    public void altera(Long id, String novoNome) {
        Turma t = turmaDao.carrega(id);
        if (!novoNome.equals(""))
            t.setNome(novoNome);
        turmaDao.atualizaTurma(t);
        result.redirectTo(TurmasController.class).listaTurmasDoProfessor(t.getProfessor());
    }

	/**
	 * Método associado ao .jsp com formulário para remoção de cadastro de
	 * turma.
	 */
    public void remocao() {
    }

    /**
     * Remove uma turma do banco de dados com o id fornecido.
     * 
     * @param id
     */
    public void remove(final Long id) {
        Turma turma = turmaDao.carrega(id);
        turmaDao.removeTurma(turma);
        result.redirectTo(TurmasController.class).listaTurmasDoProfessor(turma.getProfessor());
    }
}
