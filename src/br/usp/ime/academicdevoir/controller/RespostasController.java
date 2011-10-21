package br.usp.ime.academicdevoir.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.usp.ime.academicdevoir.dao.AlunoDao;
import br.usp.ime.academicdevoir.dao.DisciplinaDao;
import br.usp.ime.academicdevoir.dao.ListaDeExerciciosDao;
import br.usp.ime.academicdevoir.dao.ListaDeRespostasDao;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.ListaDeExercicios;
import br.usp.ime.academicdevoir.entidade.ListaDeRespostas;
import br.usp.ime.academicdevoir.entidade.Resposta;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Resource
public class RespostasController {

	private final ListaDeRespostasDao dao;
	private final ListaDeExerciciosDao listaDeExerciciosDao;
	private final AlunoDao alunoDao;
	private final DisciplinaDao disciplinaDao;
	private final UsuarioSession usuario;
	private final Result result;

	public RespostasController(ListaDeRespostasDao dao,
			ListaDeExerciciosDao listaDeExerciciosDao, AlunoDao alunoDao, DisciplinaDao disciplinaDao,
			UsuarioSession usuario, Result result, Validator validator) {
		this.dao = dao;
		this.listaDeExerciciosDao = listaDeExerciciosDao;
		this.alunoDao = alunoDao;
		this.disciplinaDao = disciplinaDao;
		this.usuario = usuario;
		this.result = result;
	}

	@Post
	@Path("/respostas/cadastra")
	public void salvaRespostas(ListaDeRespostas listaDeRespostas, ListaDeExercicios listaDeExercicios) {
		listaDeExerciciosDao.recarrega(listaDeExercicios);
		listaDeRespostas.setAluno((Aluno) usuario.getUsuario());
		listaDeRespostas.setListaDeExercicios(listaDeExercicios);
		
		dao.salva(listaDeRespostas);		
		/*result.redirectTo;*/
	}
	
	@Put
	@Path("/respostas/altera/{id}")
	public void alteraRespostas(Long id, ListaDeExercicios listaDeExercicios) {
		ListaDeRespostas listaDeRespostas = dao.carrega(id);
		listaDeExerciciosDao.recarrega(listaDeExercicios);

		listaDeRespostas.setAluno((Aluno) usuario.getUsuario());
		listaDeRespostas.setListaDeExercicios(listaDeExercicios);
		
		dao.atualiza(listaDeRespostas);
		/*result.redirectTo;*/
	}
	
	@Delete
	@Path("/respostas/altera/{listaDeRespostas.id}")
	public void removeRespostas(Long id) {
		ListaDeRespostas listaDeRespostas = dao.carrega(id);
		dao.remove(listaDeRespostas);		
		/*result.redirectTo;*/
	}
}
