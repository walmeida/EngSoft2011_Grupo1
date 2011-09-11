package br.usp.ime.academicdevoir.controller;

import org.hsqldb.rights.User;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Path;

import br.usp.ime.academicdevoir.dao.UsuarioDao;
import br.usp.ime.academicdevoir.entidade.Aluno;
import br.usp.ime.academicdevoir.entidade.Professor;
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

    @Get
    @Path("/login")
    public void login() {
    }

    @Post
    @Path("/login")
    public void login(Usuario usuario) {
        try {
            Usuario user = usuarioDao.fazLogin(usuario.getLogin(),
                    usuario.getSenha());

            usuarioSession.setUsuario(user);

            result.redirectTo(LoginController.class).login();
        } catch (Exception e) {
            e.printStackTrace();
            result.forwardTo(this).login();
        }
    }

}
