package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Get; 
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.ime.academicdevoir.dao.UsuarioDao;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.Privilegio;
import br.usp.ime.academicdevoir.infra.Public;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Public
@Resource
/**
 * Controlador de login.
 */
public class LoginController {
	
	/**
	 * @uml.property  name="result"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Result result;
	/**
	 * @uml.property  name="usuarioSession"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private UsuarioSession usuarioSession;
	/**
	 * @uml.property  name="usuarioDao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
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

	@Post("/autenticar")
	public void login(Usuario usuario) {
		Usuario user = usuarioDao.fazLogin(usuario.getLogin(),
				usuario.getSenha());

		if (user != null) {
			usuarioSession.setUsuario(user);
			Privilegio pr = user.getPrivilegio();
			if (pr.equals(Privilegio.PROFESSOR)
					|| pr.equals(Privilegio.ADMINISTRADOR)) {
				result.redirectTo(ProfessoresController.class).home();
			} else {
				result.redirectTo(AlunosController.class).home();
			}
		} else {
			result.include("error", "Login ou senha incorreta!")
					.redirectTo(this).login();
		}

	}

	@Get("/logout")
	public void logout() {
		usuarioSession.logout();
		result.redirectTo(this).login();
	}
	
	
	public void acessoNegado(){
		
	}

}
