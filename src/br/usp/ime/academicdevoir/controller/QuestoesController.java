package br.usp.ime.academicdevoir.controller;

import java.util.List;

import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeMultiplaEscolhaDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeSubmissaoDeArquivoDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeTextoDao;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class QuestoesController {

	private final QuestaoDao dao;
	private final QuestaoDeMultiplaEscolhaDao questaoDeMultiplaEscolhaDao;
	private final QuestaoDeSubmissaoDeArquivoDao questaoDeSubmissaoDeArquivoDao;
	private final QuestaoDeTextoDao questaoDeTextoDao;
	private final Result result;
	
	public QuestoesController(QuestaoDao dao,
			QuestaoDeMultiplaEscolhaDao questaoDeMultiplaEscolhaDao,
			QuestaoDeSubmissaoDeArquivoDao questaoDeSubmissaoDeArquivoDao,
			QuestaoDeTextoDao questaoDeTextoDao, Result result) {
		this.dao = dao;
		this.questaoDeMultiplaEscolhaDao = questaoDeMultiplaEscolhaDao;
		this.questaoDeSubmissaoDeArquivoDao = questaoDeSubmissaoDeArquivoDao;
		this.questaoDeTextoDao = questaoDeTextoDao;
		this.result = result;
	}
	
	/**
	 * Recebe um id de questão e retorna um inteiro que representa o seu tipo.
	 * @param id
	 * @return int
	 */
	public int tipoDeQuestao (Long id){
		Object questao;
		questao = questaoDeMultiplaEscolhaDao.buscaPorId(id);
		if (questao != null) return 1;
		questao = questaoDeSubmissaoDeArquivoDao.buscaPorId(id); 
		if (questao != null) return 2;
		questao = questaoDeTextoDao.buscaPorId(id);
		if (questao != null) return 3;
		return 0;
	}
	
	@Get
	@Path("/questoes/{id}")
	/** 
	 * Retorna uma questão de múltipla escolha com o id fornecido.
	 * @param id
	 * @return QuestaoDeMultiplaEscolha	 * 
	 * */
	public void altera(Long id) {
		switch (this.tipoDeQuestao(id)){		
		case 1:
			result.redirectTo(QuestoesDeMultiplaEscolhaController.class).alteracao(id);
			break;
		case 2:
			result.redirectTo(QuestoesDeSubmissaoDeArquivoController.class).alteracao(id);
			break;
		case 3:
			result.redirectTo(QuestoesDeTextoController.class).alteracao(id);
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
	@Path("/questoes")
	/**
	 * Retorna uma lista com todas as questões cadastradas no banco de dados.
	 * @return List<Questao>
	 */
	public List<Questao> lista() {
		return dao.listaTudo();
	}

}
