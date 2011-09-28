package br.usp.ime.academicdevoir.infra;

import java.util.Arrays;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.usp.ime.academicdevoir.controller.AlunosController;
import br.usp.ime.academicdevoir.controller.LoginController;

@Intercepts
@RequestScoped
public class LoginInterceptor implements Interceptor {
 
    private Result result;
    private UsuarioSession usuarioSession;
 
    public LoginInterceptor(Result result, UsuarioSession usuarioSession) {
        this.result = result;
        this.usuarioSession = usuarioSession;
    }

    @SuppressWarnings("unchecked")
    public boolean accepts(ResourceMethod method) {
        return !Arrays.asList(LoginController.class).contains(method.getMethod().getDeclaringClass());
    }
 
    public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) {
        if (usuarioSession.getUsuario() != null) {
            stack.next(method, resourceInstance);
        } else {
            result.redirectTo(LoginController.class).login();
        }
    }
 
}
