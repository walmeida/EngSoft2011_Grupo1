package br.usp.ime.academicdevoir.controller;

import java.util.List;

import br.usp.ime.academicdevoir.dao.QuestaoDeMultiplaEscolhaDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.TipoDeQuestao;
import br.usp.ime.academicdevoir.infra.UsuarioSession;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
/**
 * Controlador de questões de múltipla escolha.
 */
public class QuestoesDeMultiplaEscolhaController {

	/**
	 * @uml.property  name="dao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private QuestaoDeMultiplaEscolhaDao dao;
	/**
	 * @uml.property  name="result"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Result result;
	/**
	 * @uml.property  name="validator"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Validator validator;
	/**
	 * @uml.property  name="usuarioSession"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final UsuarioSession usuarioSession;
	
	/**
	 * @param result para interação com o jsp da questão.
	 * @param validator 
	 * @param usuarioSession para controle de permissões
	 * @param turmaDao para interação com o banco de dados
	 */
	public QuestoesDeMultiplaEscolhaController(QuestaoDeMultiplaEscolhaDao dao,
			Result result, Validator validator, UsuarioSession usuarioSession) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.usuarioSession = usuarioSession;
	}

	@Post
	@Path("/questoes/mult")
	/**
	 * Verifica se a questão de múltipla escolha fornecida é válida e adiciona 
	 * no banco de dados.
	 * @param questao
	 */
	public void cadastra(final QuestaoDeMultiplaEscolha questao, List<Integer> resposta) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
		if (!questao.getRespostaUnica()) questao.setResposta(resposta);
		
		validator.validate(questao);
		validator.onErrorUsePageOf(QuestoesController.class).cadastro();

		dao.salva(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/mult/{id}")
	/** 
	 * Devolve uma questão de múltipla escolha com o id fornecido.
	 * 
	 * @param id
	 */
	public void alteracao(Long id) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
		QuestaoDeMultiplaEscolha questao = dao.carrega(id);
		result.include("questao", questao);
		result.include("numeroDeAlternativas", questao.getAlternativas().size());
	}

	@Put
	@Path("/questoes/mult/{questao.id}")
	/**
	 * Verifica se a questão de múltipla escolha fornecida é válida e atualiza no banco de dados.
	 * 
	 * @param questao
	 */
	public void altera(QuestaoDeMultiplaEscolha questao, List<Integer> resposta) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
		if (!questao.getRespostaUnica()) questao.setResposta(resposta);
		
		validator.validate(questao);
		validator.onErrorUsePageOf(this).alteracao(questao.getId());

		dao.atualiza(questao);

		result.redirectTo(this).lista();
	}

	@Delete
	@Path("/questoes/mult/{id}")
	/**
	 * Remove uma questão de múltipla escolha do banco de dados com o id fornecido.
	 * 
	 * @param id
	 */
	public void remove(Long id) {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
		QuestaoDeMultiplaEscolha questao = dao.carrega(id);
		dao.remove(questao);
		result.redirectTo(this).lista();
	}

	@Get
	@Path("/questoes/mult")
	/**
	 * Devolve uma lista com todas as questões de múltipla escolha cadastradas 
	 * no banco de dados.
	 */
	public void lista() {
		Usuario u = usuarioSession.getUsuario();
		if(!(u.getPrivilegio() == Privilegio.ADMINISTRADOR || u.getPrivilegio() == Privilegio.PROFESSOR)) {
			result.redirectTo(LoginController.class).acessoNegado();
			return;
		}
		
		result.include("tipoDaQuestao", TipoDeQuestao.MULTIPLAESCOLHA);
		result.include("lista", dao.listaTudo());
	}
}
