package br.usp.ime.academicdevoir.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import br.usp.ime.academicdevoir.dao.ListaDeExerciciosDao;
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.TagDao;
import br.usp.ime.academicdevoir.entidade.PropriedadesDaListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeCodigo;
import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;
import br.usp.ime.academicdevoir.entidade.QuestaoDeSubmissaoDeArquivo;
import br.usp.ime.academicdevoir.entidade.QuestaoDeTexto;
import br.usp.ime.academicdevoir.entidade.QuestaoDeVouF;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;
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

	/**
	 * @uml.property name="dao"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final QuestaoDao dao;
	/**
	 * @uml.property name="result"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final Result result;
	/**
	 * @uml.property name="usuarioSession"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final UsuarioSession usuarioSession;
	private TagDao tagDao;
	private ListaDeExerciciosDao listaDeExerciciosDao;

	/**
	 * @param turmaDao
	 *            para interação com o banco de dados
	 * @param result
	 *            para interação com o jsp da questao.
	 * @param usuarioSession
	 *            para controle de permissões
	 */
	public QuestoesController(QuestaoDao dao, TagDao tagDao,
			ListaDeExerciciosDao listaDeExerciciosDao, Result result,
			UsuarioSession usuarioSession) {
		this.dao = dao;
		this.tagDao = tagDao;
		this.listaDeExerciciosDao = listaDeExerciciosDao;
		this.result = result;
		this.usuarioSession = usuarioSession;
	}

	@Get
	@Path("/questoes/{id}")
	/** 
	 * Devolve uma questão de múltipla escolha com o id fornecido.
	 * @param id
	 * */
	public void alteracao(Long id) {
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u
				.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		switch (dao.carrega(id).getTipo()) {
		case CODIGO:
			result.redirectTo(QuestoesDeCodigoController.class).alteracao(id);
			break;
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
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u
				.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}

		Questao questao = dao.carrega(id);
		dao.remove(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/cadastro")
	/**
	 * Permite acesso à página com formulário para cadastro de uma nova questão.
	 */
	public void cadastro() {
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u
				.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
	}

	@Get
	@Path("/questoes")
	/**
	 * Devolve uma lista com todas as questões cadastradas no banco de dados.
	 */
	public void lista() {
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u
				.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}

		result.include("lista", dao.listaTudo());
	}

	@Get
	@Path("/questoes/tags/autocompletar.json")
	public void autoCompletar(String term) {
		result.use(json()).withoutRoot().from(tagDao.autoCompletar(term))
				.serialize();
	}

	@Get
	@Path("/questoes/buscaListas/{idDaQuestao}")
	public void buscaListas(Long idDaQuestao) {
		List<BigInteger> idsDasListas = listaDeExerciciosDao
				.buscaListasQueContemQuestao(idDaQuestao);
		List<PropriedadesDaListaDeExercicios> listas = new ArrayList<PropriedadesDaListaDeExercicios>();
		for (BigInteger id : idsDasListas) {
			listas.add(listaDeExerciciosDao.carrega(id.longValue())
					.getPropriedades());
		}
		result.use(json())
				.withoutRoot()
				.from(listas)
				.exclude("enunciado", "peso", "prazoDeEntrega", "autoCorrecao",
						"visivel", "numeroMaximoDeEnvios").serialize();
	}

	@Get
	@Path("/questoes/copia/{id}")
	public void copia(Long id) {
		Usuario u = usuarioSession.getUsuario();
		if (!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u
				.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		Questao questao = dao.carrega(id).copia();

		switch (questao.getTipo()) {
		case CODIGO:
			result.redirectTo(QuestoesDeCodigoController.class).copia(
					(QuestaoDeCodigo) questao);
			break;
		case MULTIPLAESCOLHA:
			result.redirectTo(QuestoesDeMultiplaEscolhaController.class)
					.copia((QuestaoDeMultiplaEscolha) questao);
			break;
		case SUBMISSAODEARQUIVO:
			result.redirectTo(QuestoesDeSubmissaoDeArquivoController.class)
					.copia((QuestaoDeSubmissaoDeArquivo) questao);
			break;
		case TEXTO:
			result.redirectTo(QuestoesDeTextoController.class).copia(
					(QuestaoDeTexto) questao);
			break;
		case VOUF:
			result.redirectTo(QuestoesDeVouFController.class).copia(
					(QuestaoDeVouF) questao);
			break;
		default:
			result.redirectTo(this).lista();
			break;
		}
	}
}
