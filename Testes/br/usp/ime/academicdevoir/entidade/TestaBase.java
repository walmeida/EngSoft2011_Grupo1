package br.usp.ime.academicdevoir.entidade;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestaBase {
	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	Session session;
    /**
	 * @uml.property  name="transaction"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
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
