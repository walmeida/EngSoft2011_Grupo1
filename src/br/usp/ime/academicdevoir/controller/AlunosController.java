package br.usp.ime.academicdevoir.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.InterceptionException;
import br.usp.ime.academicdevoir.dao.AlunoDao;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.Public;
import br.usp.ime.academicdevoir.infra.UsuarioSession;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

@Resource
/**
 * Controlador de alunos.
 * @author Vinicius Rezende
 */
public class AlunosController {
	
	/**
	 * @uml.property  name="result"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Result result;
	/**
	 * @uml.property  name="alunoDao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private AlunoDao alunoDao;
	/**
	 * @uml.property  name="disciplinaDao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private DisciplinaDao disciplinaDao;
	/**
	 * @uml.property  name="turmaDao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private TurmaDao turmaDao;
	/**
	 * @uml.property  name="usuarioSession"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private UsuarioSession usuarioSession;

	/**
	 * @param result para interação com o jsp do aluno.
	 * @param alunoDao para interação com o banco de dados
	 * @param disciplinaDao para interação com o banco de dados
	 * @param turmaDao para interação com o banco de dados
	 */
	public AlunosController(Result result, AlunoDao alunoDao, 
	        DisciplinaDao disciplinaDao, TurmaDao turmaDao, UsuarioSession alunodao) {
		this.result = result;
		this.alunoDao = alunoDao;
		this.disciplinaDao = disciplinaDao;
		this.turmaDao = turmaDao;
		this.usuarioSession = alunodao;
	}

	/**
	 * Método associado à home page do aluno.
	 */

	public void home() {
	}

	/**
	 * Método associado ao .jsp que lista os alunos cadastrados no banco de 
	 * dados.
	 */
	public void lista() {
	    result.include("listaDeAlunos", alunoDao.listaTudo());
	}
	
	/**
	 * Método associado ao .jsp que lista as turmas em que o aluno está 
	 * matriculado.
	 * @param idAluno id do aluno
	 */
	public void listaTurmas(Long idAluno) {
	    result.include("aluno", alunoDao.carrega(idAluno));
	}
	
	/**
     * Método está associado ao .jsp do formulário de cadastro de um aluno no sistema.
     */
	@Public
	public void cadastro() {
	}

	/**
	 * Cadastra novo aluno no sitema
	 * 
	 * @param novo 
	 */
	@Public
	public void cadastra(final Aluno novo) {        
	    alunoDao.salvaAluno(novo);
	    result.redirectTo(AlunosController.class).lista();
	}

	/**
	 * Método associado ao .jsp com formulário para alteração de cadastro de
	 * aluno.
	 * 
	 * @param id   identificador do aluno
	 */
	public void alteracao(Long id) {
		result.include("aluno", alunoDao.carrega(id));
	}
	
	/**
	 * Altera um aluno no banco de dados com o id fornecido e set o nome
	 * do aluno para novoNome, o email para novoEmail e a senha para novaSenha.
	 * 
	 * @param id
	 */
	public void altera(Long id, String novoNome, String novoEmail,
			String novaSenha) {
		Aluno a = alunoDao.carrega(id);
		if (!novoNome.equals("") || !StringUtils.isBlank(novoNome)) a.setNome(novoNome);
		if (!novoEmail.equals("") || !StringUtils.isBlank(novoEmail)) a.setEmail(novoEmail);
		if (!novaSenha.equals("") || !StringUtils.isBlank(novaSenha)) a.setSenha(novaSenha);
		alunoDao.atualizaAluno(a);
		result.redirectTo(AlunosController.class).home();
	}

	/**
	 * Método associado ao .jsp com formulário para remoção de cadastro de
	 * aluno.
	 */
	public void remocao() {
	}
	
	/**
	 * Remove um aluno do banco de dados com o id fornecido.
	 * 
	 * @param id
	 */
	public void remove(final Long id) {
		Aluno aluno = alunoDao.carrega(id);
		alunoDao.removeAluno(aluno);
		result.redirectTo(AlunosController.class).lista();
	}

	/**
	 * Método associado ao .jsp com formulário para matricula do aluno.
	 */
	public void matricula() {
	    result.include("listaDeDisciplinas", disciplinaDao.listaTudo());
	}
	
	/**
	 * Inscreve o aluno na turma com o id fornecido.
	 * 
	 * @param idTurma
	 * @param idAluno
	 */
	public void inscreve(Long idAluno, Long idTurma) {
		Aluno aluno = alunoDao.carrega(idAluno);
		Turma turma = turmaDao.carrega(idTurma);
	    alunoDao.inscreve(aluno, turma);
		result.redirectTo(AlunosController.class).lista();
	}
	
	/**
	 * Remove a matricula do aluno na turma.
	 * @param idAluno id do aluno
	 * @param idTurma id da turma
	 */
    public void removeMatricula(Long idAluno, Long idTurma) {
        Aluno aluno = alunoDao.carrega(idAluno);
        Turma turma = turmaDao.carrega(idTurma);
        alunoDao.removeMatricula(aluno, turma);
        result.redirectTo(AlunosController.class).listaTurmas(idAluno);
    }
}
