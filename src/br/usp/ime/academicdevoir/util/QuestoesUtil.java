package br.usp.ime.academicdevoir.util;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.usp.ime.academicdevoir.dao.QuestaoDeMultiplaEscolhaDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeSubmissaoDeArquivoDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeTextoDao;
import br.usp.ime.academicdevoir.dao.QuestaoDeVouFDao;
import br.usp.ime.academicdevoir.infra.TipoDeQuestao;

@Component
@RequestScoped
public class QuestoesUtil {

	/**
	 * @uml.property  name="questaoDeMultiplaEscolhaDao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final QuestaoDeMultiplaEscolhaDao questaoDeMultiplaEscolhaDao;
	/**
	 * @uml.property  name="questaoDeSubmissaoDeArquivoDao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final QuestaoDeSubmissaoDeArquivoDao questaoDeSubmissaoDeArquivoDao;
	/**
	 * @uml.property  name="questaoDeTextoDao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final QuestaoDeTextoDao questaoDeTextoDao;
	/**
	 * @uml.property  name="questaoDeVouFDao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final QuestaoDeVouFDao questaoDeVouFDao;

	public QuestoesUtil(
			QuestaoDeMultiplaEscolhaDao questaoDeMultiplaEscolhaDao,
			QuestaoDeSubmissaoDeArquivoDao questaoDeSubmissaoDeArquivoDao,
			QuestaoDeTextoDao questaoDeTextoDao,
			QuestaoDeVouFDao questaoDeVouFDao) {

		this.questaoDeMultiplaEscolhaDao = questaoDeMultiplaEscolhaDao;
		this.questaoDeSubmissaoDeArquivoDao = questaoDeSubmissaoDeArquivoDao;
		this.questaoDeTextoDao = questaoDeTextoDao;
		this.questaoDeVouFDao = questaoDeVouFDao;
	}

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
}
