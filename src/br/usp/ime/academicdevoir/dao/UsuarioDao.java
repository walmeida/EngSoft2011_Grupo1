package br.usp.ime.academicdevoir.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.usp.ime.academicdevoir.entidade.Usuario;
import br.usp.ime.academicdevoir.infra.Criptografia;

@Component
public class UsuarioDao {
	
	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="br.usp.ime.academicdevoir.entidade.Usuario"
	 */
	private final Session session;
	
	public UsuarioDao(Session session){
		this.session = session;
	}
 
    @SuppressWarnings("unchecked")
    public Usuario fazLogin(String login, String senha){
    	try{
	        List<Usuario> user = session.createCriteria(Usuario.class)
	                .add(Restrictions.like("login", login))
	                .list();
	        
	        if (user == null) return null;
	        
	        if(StringUtils.isBlank(login) || StringUtils.isBlank(senha))
	        	return null;
	        
	        // Gerando a senha criptografada para comparar
	        String senhaCripto = new Criptografia().geraMd5(senha);
	        
	        if(user.get(0).getSenha().equals(senhaCripto)) return user.get(0);
	        
	        return null;
    	} catch (Exception e) { 
    		return null;
    	}
    }
}
