package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Path;

import br.usp.ime.academicdevoir.dao.UsuarioDao;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Resource
public class LoginController {
	private final Result result;
	private UsuarioSession usuarioSession;
	private UsuarioDao usuarioDao;

	public LoginController(Result result, UsuarioDao usuarioDao,
			UsuarioSession alunodao) {
		this.result = result;
		this.usuarioDao = usuarioDao;
		this.usuarioSession = alunodao;
	}

	@Path("/")
	public void index() {
		result.redirectTo(LoginController.class).login();
	}

	@Get
	@Path("/login")
	public void login() {
	}

	@Post
	@Path("/autenticar")
	public void login(Usuario usuario) {
		Usuario user = usuarioDao.fazLogin(usuario.getLogin(),
				usuario.getSenha());
		
		if (user != null) {
			usuarioSession.setUsuario(user);
			result.redirectTo(AlunosController.class).home();
		} else {
			result.include("error", "E-mail ou senha incorreta!")
					.redirectTo(this).login();
		}

	}

	@Get("/logout")
	public void logout() {
		usuarioSession.logout();
		result.redirectTo(this).login();
	}

}
