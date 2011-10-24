package br.usp.ime.academicdevoir.infra;

import java.util.Arrays;
import java.util.Collection;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.usp.ime.academicdevoir.controller.LoginController;

@Intercepts
@RequestScoped
public class LoginInterceptor implements Interceptor {

	/**
	 * @uml.property  name="result"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Result result;
	/**
	 * @uml.property  name="usuarioSession"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private UsuarioSession usuarioSession;

	public LoginInterceptor(Result result, UsuarioSession usuarioSession) {
		this.result = result;
		this.usuarioSession = usuarioSession;
	}
	
	private boolean hasAccess(Permission privilegio) {
	    if (privilegio == null) {
	        return true;
	    }

	    Collection<Privilegio> privilegioList = Arrays.asList(privilegio.value());

	    return privilegioList.contains(usuarioSession.getUsuario().getPrivilegio());
	}


	public boolean accepts(ResourceMethod method) {
		return !(method.getMethod().isAnnotationPresent(Public.class) || method
				.getResource().getType().isAnnotationPresent(Public.class));
		//return false;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) {
    	if (usuarioSession.getUsuario() != null) {
    		Permission methodPermission = method.getMethod().getAnnotation(
    				Permission.class);
    		Permission controllerPermission = method.getResource().getType()
    				.getAnnotation(Permission.class);
    		if (this.hasAccess(methodPermission)
    				&& this.hasAccess(controllerPermission)) {
    			stack.next(method, resourceInstance);
    		} else {
    			result.redirectTo(LoginController.class).acessoNegado();
    		}
    
    	} else {
    		result.redirectTo(LoginController.class).login();
    	}
	}

}
