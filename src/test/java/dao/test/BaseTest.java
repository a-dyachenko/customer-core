package dao.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import customers_core.dao.CommentDAO;
import customers_core.dao.CustomerCoreSessionProvider;
import customers_core.dao.CustomerDAO;
import customers_core.dao.CustomerStatusDAO;

public class BaseTest {

	protected CustomerStatusDAO customerStatusDAO;
	protected CustomerDAO customerDAO;
	protected CommentDAO commentDAO;
	protected CustomerCoreSessionProvider sessionProvider;

	protected Logger logger = LogManager.getLogger(getClass());

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
