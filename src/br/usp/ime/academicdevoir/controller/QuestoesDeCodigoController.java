package br.usp.ime.academicdevoir.controller;

import br.usp.ime.academicdevoir.dao.QuestaoDeCodigoDao;
import br.usp.ime.academicdevoir.dao.TagDao;
import br.usp.ime.academicdevoir.entidade.QuestaoDeCodigo;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.Permission;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.UsuarioSession;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
@Resource
/**
 * Controlador de questões de texto.
 */
public class QuestoesDeCodigoController {

    /**
     * @uml.property name="dao"
     * @uml.associationEnd multiplicity="(1 1)"
     */
    private QuestaoDeCodigoDao dao;
    /**
     * @uml.property name="result"
     * @uml.associationEnd multiplicity="(1 1)"
     */
    private final Result result;
    /**
     * @uml.property name="validator"
     * @uml.associationEnd multiplicity="(1 1)"
     */
    private Validator validator;
    /**
     * @uml.property name="usuarioSession"
     * @uml.associationEnd multiplicity="(1 1)"
     */
    private final UsuarioSession usuarioSession;
    private TagDao tagDao;
    
    
    /**
     * @param result
     *            para interação com o jsp da questão.
     * @param validator
     * @param usuarioSession
     *            para controle de permissões
     * @param turmaDao
     *            para interação com o banco de dados
     */
    public QuestoesDeCodigoController(QuestaoDeCodigoDao dao, TagDao tagDao,
            Result result, Validator validator, UsuarioSession usuarioSession) {
        this.dao = dao;
        this.tagDao = tagDao;
        this.result = result;
        this.validator = validator;
        this.usuarioSession = usuarioSession;
    }

    @Post
    @Path("/questoes/codigo")
    /**
     * Verifica se a questão de texto fornecida é válida e adiciona no banco de dados.
     * @param questao
     */
    public void cadastra(final QuestaoDeCodigo questao, String tags) {

        questao.setTags(tags, tagDao);

        validator.validate(questao);
        validator.onErrorUsePageOf(QuestoesController.class).cadastro();

        dao.salva(questao);
        result.redirectTo(this).lista();
    }

    @Get
    @Path("/questoes/codigo/{id}")
    /** 
     * Devolve uma questão de texto com o id fornecido.
     * @param id
     */
    public void alteracao(Long id) {
        QuestaoDeCodigo questao = dao.carrega(id);
        result.include("questao", questao);
        result.include("tags", questao.getTagsEmString());
    }

    @Put
    @Path("/questoes/codigo/{questao.id}")
    /**
     * Verifica se a questão de texto fornecida é válida e atualiza no banco de dados.
     * @param id
     */
    public void altera(QuestaoDeCodigo questao, String tags) {

        questao.setTags(tags, tagDao);

        validator.validate(questao);
        validator.onErrorUsePageOf(QuestoesDeCodigoController.class).alteracao(
                questao.getId());

        dao.atualiza(questao);
        result.redirectTo(this).lista();
    }

    @Delete
    @Path("/questoes/codigo/{id}")
    /**
     * Remove uma questão de texto do banco de dados com o id fornecido.
     * @param id
     */
    public void remove(Long id) {

        QuestaoDeCodigo questao = dao.carrega(id);
        dao.remove(questao);
        result.redirectTo(this).lista();
    }

    @Get
    @Path("/questoes/codigo")
    @Permission(Privilegio.ADMINISTRADOR)
    /**
     * Devolve uma lista com todas as questões de texto cadastradas no banco de dados.
     */
    public void lista() {

        result.include("lista", dao.listaTudo());
    }

	public void copia(QuestaoDeCodigo questao) {
		dao.salva(questao);
		result.redirectTo(this).alteracao(questao.getId());
	}
}
