package br.usp.ime.academicdevoir.controller;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.AlunoDao;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.dao.ListaDeExerciciosDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.Disciplina;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.Permission;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Resource
/**
 * Controlador de turmas.
 */
public class TurmasController {
    /**
     * @uml.property name="result"
     * @uml.associationEnd multiplicity="(1 1)"
     */
    private final Result result;
    /**
     * @uml.property name="turmaDao"
     * @uml.associationEnd multiplicity="(1 1)"
     */
    private TurmaDao turmaDao;
    /**
     * @uml.property name="disciplinaDao"
     * @uml.associationEnd multiplicity="(1 1)"
     */
    private DisciplinaDao disciplinaDao;
    /**
     * @uml.property name="alunoDao"
     * @uml.associationEnd multiplicity="(1 1)"
     */
    private AlunoDao alunoDao;
    /**
     * @uml.property name="usuarioSession"
     * @uml.associationEnd multiplicity="(1 1)"
     */
    private UsuarioSession usuarioSession;
	private ListaDeExerciciosDao listaDeExerciciosDao;

    /**
     * @param result
     *            para interação com o jsp da turma.
     * @param turmaDao
     *            para interação com o banco de dados
     * @param disciplinaDao
     *            para interação com o banco de dados
     * @param alunoDao
     *            para interação com o banco de dados
     */
    public TurmasController(Result result, TurmaDao turmaDao,
            DisciplinaDao disciplinaDao, AlunoDao alunoDao, ListaDeExerciciosDao listaDeExerciciosDao,
            UsuarioSession usuarioSession) {
        this.result = result;
        this.turmaDao = turmaDao;
        this.disciplinaDao = disciplinaDao;
        this.alunoDao = alunoDao;
        this.listaDeExerciciosDao = listaDeExerciciosDao;
        this.usuarioSession = usuarioSession;
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
        result.include("listaDeListas", turma.getListas());
    }

    /**
     * Método associado ao menu da pagina da turma.
     */
    public void menu() {

    }

    /**
     * Método associado ao .jsp que lista as turmas cadastradas no banco de
     * dados.
     */
    public void lista() {
        result.include("listaDeTurmas", turmaDao.listaTudo());
    }

    /**
     * Método associado ao .jsp que lista os alunos inscritos na turma
     * 
     * @param idTurma
     *            id da turma
     */
    public void listaAlunos(long idTurma) {
        result.include("turma", turmaDao.carrega(idTurma));
    }
    
    /**
     * Método associado ao .jsp que lista os alunos e listas de uma turma
     * 
     * @param idTurma
     *            id da turma
     */
    public void listaAlunosEListas(long idTurma) {
        result.include("turma", turmaDao.carrega(idTurma));
    }

    @Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
    /**
     * Método está associado ao .jsp do formulário de cadastro de uma turma no
     * sistema.
     */
    public void cadastro(long idDisciplina) {    	
		List<Disciplina> listaDeDisciplinas = disciplinaDao.listaTudo();
		if(listaDeDisciplinas.isEmpty()) {
			result.redirectTo(DisciplinasController.class).cadastro();
			return;
		}
		
        //result.include("listaDeDisciplinas", listaDeDisciplinas);
		result.include("idDisciplina", idDisciplina);
        
        Calendar dataDeHoje = Calendar.getInstance();
        result.include("diaAtual", dataDeHoje.get(Calendar.DAY_OF_MONTH));
        result.include("mesAtual", dataDeHoje.get(Calendar.MONTH) + 1);
        result.include("anoAtual", dataDeHoje.get(Calendar.YEAR));
    }

    @Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
    /**
     * Cadastra uma turma nova no sistema.
     * 
     * @param nova
     */
    public void cadastra(Turma nova, final List<Integer> prazoDeMatricula) {		
		if(nova.getDisciplina() == null) {
			result.redirectTo(DisciplinasController.class).cadastro();
			return;
		}
		nova.setPrazoDeMatricula(prazoDeMatricula);
        turmaDao.salvaTurma(nova);
        result.redirectTo(ProfessoresController.class).listaTurmas(nova.getProfessor().getId());
    }

    @Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
    /**
     * Método associado ao .jsp com formulário para alteração de cadastro de
     * turma.
     */
    public void alteracao(Long id) {
        Turma turma = turmaDao.carrega(id);
        Usuario u = usuarioSession.getUsuario();
		if(u.getPrivilegio() == Privilegio.PROFESSOR && u.getId() != turma.getProfessor().getId()) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
        result.include("turma", turma);
        
        Calendar dataDeHoje = Calendar.getInstance();
        result.include("diaAtual", dataDeHoje.get(Calendar.DAY_OF_MONTH));
        result.include("mesAtual", dataDeHoje.get(Calendar.MONTH) + 1);
        result.include("anoAtual", dataDeHoje.get(Calendar.YEAR));
        
        
    }

    @Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
    /**
     * Altera uma turma no banco de dados com o id fornecido e set o nome da
     * turma para novoNome.
     * 
     * @param id
     */
    public void altera(Long id, String novoNome, String novoTemPrazo, List<Integer> prazoDeMatricula) {
        Turma turma = turmaDao.carrega(id);
        Usuario u = usuarioSession.getUsuario();
		if(u.getPrivilegio() == Privilegio.PROFESSOR && u.getId() != turma.getProfessor().getId()) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
        if (!novoNome.equals(""))
            turma.setNome(novoNome);
        turma.setTemPrazo(novoTemPrazo);
        turma.setPrazoDeMatricula(prazoDeMatricula);
        turmaDao.atualizaTurma(turma);
        result.redirectTo(TurmasController.class).home(turma.getId());
    }

    @Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
    /**
     * Método associado ao .jsp com formulário para remoção de cadastro de
     * turma.
     */
    public void remocao() {
    }

    @Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
    /**
     * Remove uma turma do banco de dados com o id fornecido.
     * 
     * @param id
     */
    public void remove(final Long id) {
        Turma turma = turmaDao.carrega(id);
        Usuario u = usuarioSession.getUsuario();
		if(u.getPrivilegio() == Privilegio.PROFESSOR && u.getId() != turma.getProfessor().getId()) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}

		List<ListaDeExercicios> listasDeExercicios = turma.getListas();
		
		for (ListaDeExercicios lista : listasDeExercicios)
			listaDeExerciciosDao.remove(lista);
		
        turmaDao.removeTurma(turma);
        result.redirectTo(ProfessoresController.class).listaTurmas(u.getId());    
    }

    /**
     * Remove a matricula do aluno na turma.
     * 
     * @param idAluno
     *            id do aluno
     * @param idTurma
     *            id da turma
     */
    public void removeMatricula(Long idAluno, Long idTurma) {
        Aluno aluno = alunoDao.carrega(idAluno);
        Turma turma = turmaDao.carrega(idTurma);
        Usuario u = usuarioSession.getUsuario();
		if(u.getPrivilegio() != Privilegio.ADMINISTRADOR && !(u.getId() == turma.getProfessor().getId() || u.getId() == aluno.getId())) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
        
        alunoDao.removeMatricula(aluno, turma);
        result.redirectTo(TurmasController.class).listaAlunos(idTurma);
    }
}
