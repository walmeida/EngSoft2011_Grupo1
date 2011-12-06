package br.usp.ime.academicdevoir.controller;

import static br.com.caelum.vraptor.view.Results.json;

import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.TagDao;
import br.usp.ime.academicdevoir.entidade.Questao;
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
	 * @uml.property  name="dao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final QuestaoDao dao;
	/**
	 * @uml.property  name="result"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Result result;
	/**
	 * @uml.property  name="usuarioSession"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final UsuarioSession usuarioSession;
	private TagDao tagDao;
	
	/**
	 * @param turmaDao para interação com o banco de dados
	 * @param result para interação com o jsp da questao.
	 * @param usuarioSession para controle de permissões
	 */
	public QuestoesController(QuestaoDao dao, TagDao tagDao, Result result, UsuarioSession usuarioSession) {
		this.dao = dao;
		this.tagDao = tagDao;
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
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		switch (dao.carrega(id).getTipo()) {
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
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR)) {
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
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR))
			result.redirectTo(LoginController.class).acessoNegado();
	}
	
	@Get
	@Path("/questoes")
	/**
	 * Devolve uma lista com todas as questões cadastradas no banco de dados.
	 */
	public void lista() {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
		result.include("lista", dao.listaTudo());
	}
	
	@Get
	@Path("/questoes/tags/autocompletar.json")
	public void autoCompletar(String q) {
	}
	
}
