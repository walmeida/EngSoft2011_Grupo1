package br.usp.ime.academicdevoir.infra;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.usp.ime.academicdevoir.entidades.Usuario;

@Component
@SessionScoped
public class UsuarioSession {
	
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
