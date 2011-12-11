package br.usp.ime.academicdevoir.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import br.usp.ime.academicdevoir.dao.ListaDeExerciciosDao;
import br.usp.ime.academicdevoir.dao.ListaDeRespostasDao;
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.dao.TagDao;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.ListaDeRespostas;
import br.usp.ime.academicdevoir.entidade.PropriedadesDaListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.usp.ime.academicdevoir.entidade.QuestaoDaLista;
import br.usp.ime.academicdevoir.entidade.QuestaoDeCodigo;
import br.usp.ime.academicdevoir.entidade.QuestaoDeMultiplaEscolha;
import br.usp.ime.academicdevoir.entidade.QuestaoDeSubmissaoDeArquivo;
import br.usp.ime.academicdevoir.entidade.QuestaoDeTexto;
import br.usp.ime.academicdevoir.entidade.QuestaoDeVouF;
import br.usp.ime.academicdevoir.entidade.Resposta;
import br.usp.ime.academicdevoir.infra.Permission;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
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
	private ListaDeRespostasDao listaDeRespostasDao;;

	/**
	 * @param turmaDao
	 *            para interação com o banco de dados
	 * @param result
	 *            para interação com o jsp da questao.
	 * @param usuarioSession
	 *            para controle de permissões
	 */
	public QuestoesController(QuestaoDao dao, TagDao tagDao, ListaDeExerciciosDao listaDeExerciciosDao, ListaDeRespostasDao listaDeRespostasDao, Result result,
			UsuarioSession usuarioSession) {
		this.dao = dao;
		this.tagDao = tagDao;
		this.listaDeExerciciosDao = listaDeExerciciosDao;
		this.listaDeRespostasDao = listaDeRespostasDao;
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
	@Permission(Privilegio.ADMINISTRADOR)
	/**
	 * Remove uma questão do banco de dados com o id fornecido.
	 * @param id
	 */
	public void remove(Long id) {
		List<BigInteger> idsDasListas = listaDeExerciciosDao.buscaListasQueContemQuestao(id);
		ListaDeExercicios lista;
		List<QuestaoDaLista> questoes;
		List<ListaDeRespostas> listaDeListaDeRespostas;
		List<Resposta> respostas;
		
		
		// Remove a questão na lista de exercícios e nas listas de respostas
		for (BigInteger idDaLista : idsDasListas) {
			lista = listaDeExerciciosDao.carrega(idDaLista.longValue());
			questoes = lista.getQuestoes();
			
			for (QuestaoDaLista questao : questoes) {
				if (questao.getQuestao().getId() == id) {
					questoes.remove(questao);
					break;
				}
			}
			
			lista.setQuestoes(questoes);
			listaDeExerciciosDao.atualiza(lista);
			
			listaDeListaDeRespostas = lista.getRespostas();
			
			for (ListaDeRespostas listaDeRespostas : listaDeListaDeRespostas) {
				respostas = listaDeRespostas.getRespostas();
				
				for(Resposta resposta : respostas) {
					if (resposta.getQuestao().getId() == id) {
						respostas.remove(resposta);
						break;
					}
				}
				
				listaDeRespostas.setRespostas(respostas);
				listaDeRespostasDao.atualiza(listaDeRespostas);
			}			
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
	}

	@Get
	@Path("/questoes")
	/**
	 * Devolve uma lista com todas as questões cadastradas no banco de dados.
	 */
	public void lista() {
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
		
		PropriedadesDaListaDeExercicios propriedades;
		ListaDeExercicios lista;
		
		for (BigInteger id : idsDasListas) {
			lista = listaDeExerciciosDao.carrega(id.longValue());
			propriedades = lista.getPropriedades();
			propriedades.setNome( new String( lista.getTurma().getNome() + " - " + propriedades.getNome()) );
			listas.add(propriedades);
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
		Questao questao = dao.carrega(id).copia(tagDao);		

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
