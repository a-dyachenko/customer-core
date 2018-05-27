package dao.test;

import org.junit.After;
import org.junit.Before;

import customers_core.dao.CommentDAO;
import customers_core.dao.CustomerDAO;
import customers_core.dao.CustomerStatusDAO;
import customers_core.dao.CustomerCoreSessionProvider;

public class BaseTest {

	protected CustomerStatusDAO customerStatusDAO;
	protected CustomerDAO customerDAO;
	protected CommentDAO commentDAO;
	protected CustomerCoreSessionProvider sessionProvider;

	@Before
	public void init() {
		sessionProvider = new CustomerCoreSessionProvider();
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
