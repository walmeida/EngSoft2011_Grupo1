package br.usp.ime.academicdevoir.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;

public class TestaBase {
	Session session;
    Transaction transaction;

	public TestaBase() {
		super();
        session = mock(Session.class);
        transaction = mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
	}

	/*@Before
	 public void criarSessao() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    SessionFactory factory = configuration.buildSessionFactory();
	    session = factory.openSession();
	 }*/

}
