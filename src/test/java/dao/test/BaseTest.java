package dao.test;

import org.junit.After;
import org.junit.Before;

import customers_core.dao.CommentDAO;
import customers_core.dao.CustomerDAO;
import customers_core.dao.CustomerStatusDAO;
import customers_core.dao.HibernateSessionProvider;

public class BaseTest {

	CustomerStatusDAO customerStatusDAO;
	CustomerDAO customerDAO;
	CommentDAO commentDAO;
	HibernateSessionProvider sessionProvider;

	@Before
	public void init() {
		sessionProvider = new HibernateSessionProvider();
		customerStatusDAO = new CustomerStatusDAO(sessionProvider);
		customerDAO = new CustomerDAO(sessionProvider);
		commentDAO = new CommentDAO(sessionProvider); 
	}

	@After
	public void rollback() {
		sessionProvider.getSession().getTransaction().rollback();
		sessionProvider.getSession().close();
	}

}
