package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.AlunoDao;
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

	/**
	 * @param result para interação com o jsp do aluno.
	 * @param alunoDao para interação com o banco de dados
	 */
	public AlunosController(Result result, AlunoDao alunoDao) {
		this.result = result;
		this.alunoDao = alunoDao;
	}

	/**
	 * Método associado à home page do aluno.
	 */
	public void home() {
	}

	public void listaAlunosNaTurma(Turma turma) {
        result.include("listaDeAlunos", turma.getAlunos());
        result.redirectTo(TurmasController.class).lista();
    }

    public void listaTodas() {
        result.include("listaDeAlunos", alunoDao.getLista());
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
	 * @param novo aluno a ser cadastrado no sitema
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
	
	public void altera(Long id, String novoNome, String novoEmail,
			String novaSenha) {
		Aluno a = alunoDao.carregaPelaId(id);
		if (!novoNome.equals("")) a.setNome(novoNome);
		if (!novoEmail.equals("")) a.setEmail(novoEmail);
		if (!novaSenha.equals("")) a.setSenha(novaSenha);
		alunoDao.alteraAluno(a);
		result.redirectTo(AlunosController.class).lista();
	}

	public void remocao() {
	}

	public void remove(final Long id) {
		Aluno aluno = alunoDao.carregaPelaId(id);
		alunoDao.removeAluno(aluno);
		result.redirectTo(AlunosController.class).lista();
	}

	public void matricula() {
		result.include("alunoDao", alunoDao);
	}

	public void inscreve(Long idTurma) {
		alunoDao.inscreve(idTurma);
		result.redirectTo(AlunosController.class).lista();
	}
}
