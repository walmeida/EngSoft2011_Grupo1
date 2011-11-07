package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.usp.ime.academicdevoir.arquivos.Arquivos;
import br.usp.ime.academicdevoir.dao.AlunoDao;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.dao.ListaDeExerciciosDao;
import br.usp.ime.academicdevoir.dao.ListaDeSolucoesDao;
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.entidade.ListaDeSolucoes;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.usp.ime.academicdevoir.entidade.Resposta;
import br.usp.ime.academicdevoir.infra.TipoDeQuestao;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Resource
public class SolucoesController {

	private final ListaDeSolucoesDao dao;
	//private final ListaDeExerciciosDao listaDeExerciciosDao;
	//private final AlunoDao alunoDao;
	//private final DisciplinaDao disciplinaDao;
	//private final UsuarioSession usuario;
	private final Arquivos arquivos;
	private final Result result;
	//private final Validator validator;
	private final QuestaoDao questaoDao;

	public SolucoesController(ListaDeSolucoesDao dao,
			ListaDeExerciciosDao listaDeExerciciosDao, AlunoDao alunoDao, DisciplinaDao disciplinaDao,
			UsuarioSession usuario, Arquivos arquivos, Result result, Validator validator, QuestaoDao questaoDao) {
		this.dao = dao;
		//this.listaDeExerciciosDao = listaDeExerciciosDao;
		//this.alunoDao = alunoDao;
		//this.disciplinaDao = disciplinaDao;
		this.questaoDao = questaoDao;
		//this.usuario = usuario;
		this.arquivos = arquivos;
		this.result = result;
		//this.validator = validator;
	}
	
	/**
	 * Salva uma resposta referente na lista de respostas fornecida.
	 * @param listaDeSolucoes
	 * @param listaDeExercicios
	 */
	@Post
	@Path("/solucoes/{listaDeSolucoes.id}/cadastra")
	public void salvaResposta(ListaDeSolucoes listaDeSolucoes, Resposta resposta, Long idDaQuestao, UploadedFile arquivo) {
		if (resposta == null) resposta = new Resposta();
		
		dao.recarrega(listaDeSolucoes);
		
		Questao questao = questaoDao.carrega(idDaQuestao);		
				
		if (questao.getTipo() == TipoDeQuestao.SUBMISSAODEARQUIVO && arquivo != null) {
			arquivos.salva(arquivo, idDaQuestao);
			resposta.setValor(arquivo.getFileName());
		}
		
		resposta.setQuestao(questao);
		listaDeSolucoes.adiciona(resposta);
				
		dao.atualiza(listaDeSolucoes);
		result.redirectTo(ListasDeExerciciosController.class).lista();
	}
	
	/**
	 * Remove a lista de solucoes fornecida do banco de dados.
	 * @param id
	 */
	@Delete
	@Path("/solucoes/{id}")
	public void removeSolucoes(Long id) {
		ListaDeSolucoes listaDeSolucoes = dao.carrega(id);
		dao.remove(listaDeSolucoes);		
		/*result.redirectTo;*/
	}
}
