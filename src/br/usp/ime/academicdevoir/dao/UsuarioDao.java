package br.usp.ime.academicdevoir.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidades.Usuario;

@Component
public class UsuarioDao {
	
	private final Session session;
	
	public UsuarioDao(Session session){
		this.session = session;
	}
 
    public Usuario fazLogin(String login, String senha){
    	Usuario user;
        
        user = (Usuario) session.load(Usuario.class, login);
        
        if (user == null) return null;
        
        if(user.getSenha().equals(senha)) return user;
        
        return null;
        
    }
}
