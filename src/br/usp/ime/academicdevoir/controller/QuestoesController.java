package br.usp.ime.academicdevoir.controller;

import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeMultiplaEscolhaDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeSubmissaoDeArquivoDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeTextoDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeVouFDao;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.usp.ime.academicdevoir.util.TipoDeQuestao;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
/**
 * Controlador de questões.
 */
public class QuestoesController {

	private final QuestaoDao dao;
	private final QuestaoDeMultiplaEscolhaDao questaoDeMultiplaEscolhaDao;
	private final QuestaoDeSubmissaoDeArquivoDao questaoDeSubmissaoDeArquivoDao;
	private final QuestaoDeTextoDao questaoDeTextoDao;
	private final QuestaoDeVouFDao questaoDeVouFDao;
	private final Result result;
	
	/**
	 * @param result para interação com o jsp da questao.
	 * @param dao para interação com o banco de dados
	 */
	public QuestoesController(Result result,
			QuestaoDao dao,
			QuestaoDeMultiplaEscolhaDao questaoDeMultiplaEscolhaDao,
			QuestaoDeSubmissaoDeArquivoDao questaoDeSubmissaoDeArquivoDao,
			QuestaoDeTextoDao questaoDeTextoDao, QuestaoDeVouFDao questaoDeVouFDao) {
		this.dao = dao;
		this.questaoDeMultiplaEscolhaDao = questaoDeMultiplaEscolhaDao;
		this.questaoDeSubmissaoDeArquivoDao = questaoDeSubmissaoDeArquivoDao;
		this.questaoDeTextoDao = questaoDeTextoDao;
		this.questaoDeVouFDao = questaoDeVouFDao;
		this.result = result;
	}

	/**
	 * Recebe um id de questão e devolve o seu tipo.
	 * 
	 * @param id
	 * @return TipoDeQuestao
	 */
	public TipoDeQuestao getTipoDeQuestao(Long id) {
		Object questao;
		questao = questaoDeMultiplaEscolhaDao.buscaPorId(id);
		if (questao != null)
			return TipoDeQuestao.MULTIPLAESCOLHA;
		questao = questaoDeSubmissaoDeArquivoDao.buscaPorId(id);
		if (questao != null)
			return TipoDeQuestao.SUBMISSAODEARQUIVO;
		questao = questaoDeTextoDao.buscaPorId(id);
		if (questao != null)
			return TipoDeQuestao.TEXTO;
		questao = questaoDeVouFDao.buscaPorId(id);
		if (questao != null)
			return TipoDeQuestao.VOUF;
		return TipoDeQuestao.INVALIDA;
	}

	@Get
	@Path("/questoes/{id}")
	/** 
	 * Devolve uma questão de múltipla escolha com o id fornecido.
	 * @param id
	 * */
	public void alteracao(Long id) {
		switch (this.getTipoDeQuestao(id)) {
		case MULTIPLAESCOLHA:
			result.redirectTo(QuestoesDeMultiplaEscolhaController.class)
					.alteracao(id);
			break;
		case SUBMISSAODEARQUIVO:
			result.redirectTo(QuestoesDeSubmissaoDeArquivoController.class)
					.alteracao(id);
			break;
		case TEXTO:
			result.redirectTo(QuestoesDeTextoController.class).alteracao(id);
			break;
		case VOUF:
			result.redirectTo(QuestoesDeVouFController.class).alteracao(id);
			break;
		default:
			result.redirectTo(this).lista();
			break;
		}
	}

	@Delete
	@Path("/questoes/{id}")
	/**
	 * Remove uma questão do banco de dados com o id fornecido.
	 * @param id
	 */
	public void remove(Long id) {
		Object questao = dao.carrega(id);
		dao.remove((Questao) questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/cadastro")
	/**
	 * Redireciona para a página com formulário para cadastro de uma nova questão.
	 */
	public void cadastro() {
	}
	
	@Get
	@Path("/questoes")
	/**
	 * Devolve uma lista com todas as questões cadastradas no banco de dados.
	 */
	public void lista() {
		result.include("lista", dao.listaTudo());
	}
}
