package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.AlunoDao;
import br.usp.ime.academicdevoir.dao.TurmaDao;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.Turma;
import br.usp.ime.academicdevoir.infra.Public;

@Resource
/**
 * Controlador de alunos.
 * @author Vinicius Rezende
 */
public class AlunosController {
	
	private final Result result;
	private AlunoDao alunoDao;
	private TurmaDao turmaDao;

	/**
	 * @param result para interação com o jsp do aluno.
	 * @param alunoDao para interação com o banco de dados
	 */
	public AlunosController(Result result, AlunoDao alunoDao, TurmaDao turmaDao) {
		this.result = result;
		this.alunoDao = alunoDao;
		this.turmaDao = turmaDao;
	}

	/**
	 * Método associado à home page do aluno.
	 */
	public void home() {
	}

	/**
	 * Lista todos os alunos da turma fornecida.
	 * 
	 * @param turma
	 */
	public void listaAlunosNaTurma(Long idDaTurma) {
	    Turma turma = turmaDao.carrega(idDaTurma);
        result.include("listaDeAlunos", turma.getAlunos().toArray());
        result.redirectTo(AlunosController.class).lista();
    }


	/**
	 * Lista todos os alunos do banco de dados.
	 */
    public void listaTodosAlunos() {
        result.include("listaDeAlunos", alunoDao.listaTudo());
        result.redirectTo(AlunosController.class).lista();
    }
	
	/**
	 * Método associado ao .jsp que lista os alunos.
	 */
	public void lista() {
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
	 */
	public void alteracao() {
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
		if (!novoNome.equals("")) a.setNome(novoNome);
		if (!novoEmail.equals("")) a.setEmail(novoEmail);
		if (!novaSenha.equals("")) a.setSenha(novaSenha);
		alunoDao.atualizaAluno(a);
		result.redirectTo(AlunosController.class).lista();
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
		result.include("alunoDao", alunoDao);
	}

	/**
	 * Inscreve o aluno na turma com o id fornecido.
	 * 
	 * @param idTurma
	 */
	public void inscreve(Long idTurma) {
		alunoDao.inscreve(idTurma);
		result.redirectTo(AlunosController.class).lista();
	}
}
