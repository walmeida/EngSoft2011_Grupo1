package br.usp.ime.academicdevoir.teste;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;

public class TestaBase {
	Session session;

	public TestaBase() {
		super();
	}

	@Before
	 public void criarSessao() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    SessionFactory factory = configuration.buildSessionFactory();
	    session = factory.openSession();
	 }

}
