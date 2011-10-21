package br.usp.ime.academicdevoir.sessao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class CriadorDeSessao implements ComponentFactory<Session> {

	/**
	 * @uml.property  name="factory"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final SessionFactory factory;
	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  
	 */
	private Session session;

	public CriadorDeSessao(SessionFactory factory) {
		this.factory = factory;
	}

	@PostConstruct
	public void open() {
		this.session = factory.openSession();
	}

	public Session getInstance() {
		return this.session;
	}

	@PreDestroy
	public void close() {
		this.session.close();
	}
}
