package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Usuario;

@Component
public class UsuarioDao {
	
	private final Session session;
	
	public UsuarioDao(Session session){
		this.session = session;
	}
 
    @SuppressWarnings("unchecked")
    public Usuario fazLogin(String login, String senha){
    	  
        List<Usuario> user = session.createCriteria(Usuario.class)
                .add(Restrictions.like("login", login))
                .list();
        
        if (user == null) return null;
        
        if(user.get(0).getSenha().equals(senha)) return user.get(0);
        
        return null;
        
    }
}
