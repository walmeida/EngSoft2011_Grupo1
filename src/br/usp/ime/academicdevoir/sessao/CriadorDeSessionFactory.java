package br.usp.ime.academicdevoir.sessao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class CriadorDeSessionFactory implements ComponentFactory<SessionFactory> {

	/**
	 * @uml.property  name="factory"
	 * @uml.associationEnd  
	 */
	private SessionFactory factory;

	@PostConstruct
	public void open() {
		Configuration configuration = new Configuration();
		configuration.configure();

		this.factory = configuration.buildSessionFactory();
	}

	public SessionFactory getInstance() {
		return factory;
	}

	@PreDestroy
	public void close() {
		this.factory.close();
	}
}
