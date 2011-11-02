package br.usp.ime.academicdevoir.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.usp.ime.academicdevoir.arquivos.Arquivos;
import br.usp.ime.academicdevoir.dao.AlunoDao;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.dao.ListaDeExerciciosDao;
import br.usp.ime.academicdevoir.dao.ListaDeRespostasDao;
import br.usp.ime.academicdevoir.dao.QuestaoDao;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.ListaDeRespostas;
import br.usp.ime.academicdevoir.entidade.Questao;
import br.usp.ime.academicdevoir.entidade.QuestaoDaLista;
import br.usp.ime.academicdevoir.entidade.Resposta;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Resource
public class RespostasController {

	private final ListaDeRespostasDao dao;
	private final ListaDeExerciciosDao listaDeExerciciosDao;
	private final AlunoDao alunoDao;
	private final DisciplinaDao disciplinaDao;
	private final UsuarioSession usuario;
	private final Arquivos arquivos;
	private final Result result;
	private final Validator validator;
	private final QuestaoDao questaoDao;

	public RespostasController(ListaDeRespostasDao dao,
			ListaDeExerciciosDao listaDeExerciciosDao, AlunoDao alunoDao, DisciplinaDao disciplinaDao,
			UsuarioSession usuario, Arquivos arquivos, Result result, Validator validator, QuestaoDao questaoDao) {
		this.dao = dao;
		this.listaDeExerciciosDao = listaDeExerciciosDao;
		this.alunoDao = alunoDao;
		this.disciplinaDao = disciplinaDao;
		this.questaoDao = questaoDao;
		this.usuario = usuario;
		this.arquivos = arquivos;
		this.result = result;
		this.validator = validator;
	}

	/**
	 * Salva uma lista de respostas (enviada pelo aluno) referente a lista de exercícios fornecida no banco de dados.
	 * @param listaDeRespostas
	 * @param listaDeExercicios
	 */
	@Post
	@Path("/respostas/{listaDeExercicios.id}/cadastra")
	public void salvaRespostas(ListaDeRespostas listaDeRespostas, ListaDeExercicios listaDeExercicios, List<Long> listaDeIdsQuestoes) {
		
		listaDeExerciciosDao.recarrega(listaDeExercicios);
		Iterator<Long> iListaDeIds = listaDeIdsQuestoes.iterator();
		Resposta resposta;
		Iterator<Resposta> iRespostas = listaDeRespostas.getRespostas().iterator();
		List<Resposta> respostas = new ArrayList<Resposta>();
		
		while(iRespostas.hasNext() && iListaDeIds.hasNext()) {
			resposta = iRespostas.next();
			resposta.setQuestao(questaoDao.carrega(iListaDeIds.next()));
			respostas.add(resposta);
		}
		listaDeRespostas.setRespostas(respostas);
		
		listaDeExerciciosDao.recarrega(listaDeExercicios);
		listaDeRespostas.setAluno((Aluno) usuario.getUsuario());
		listaDeRespostas.setListaDeExercicios(listaDeExercicios);
				
		dao.salva(listaDeRespostas);
		result.redirectTo(ListasDeExerciciosController.class).lista();
	}
	
	/**
	 * Altera respostas da lista fornecida.
	 * @param id
	 * @param listaDeExercicios
	 */
	@Put
	@Path("/respostas/{id}")
	public void alteraRespostas(Long id, ListaDeExercicios listaDeExercicios, List<Resposta> respostas) {
		ListaDeRespostas listaDeRespostas = dao.carrega(id);
		listaDeExerciciosDao.recarrega(listaDeExercicios);

		listaDeRespostas.setRespostas(respostas);
		listaDeRespostas.setAluno((Aluno) usuario.getUsuario());
		listaDeRespostas.setListaDeExercicios(listaDeExercicios);
		
		dao.atualiza(listaDeRespostas);
		/*result.redirectTo;*/
	}
	
	/**
	 * Remove a lista de respostas fornecida do banco de dados.
	 * @param id
	 */
	@Delete
	@Path("/respostas/{id}")
	public void removeRespostas(Long id) {
		ListaDeRespostas listaDeRespostas = dao.carrega(id);
		dao.remove(listaDeRespostas);		
		/*result.redirectTo;*/
	}
}
