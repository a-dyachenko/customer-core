package customer_core.service;

import java.util.List;

import customers_core.dao.CommentDAO;
import customers_core.dao.CustomerCoreSessionProvider;
import customers_core.dao.CustomerDAO;
import customers_core.dao.CustomerStatusDAO;
import customers_core.dao.HibernateSessionProvider;
import customers_core.db.CommentDB;
import customers_core.db.CustomerDB;
import customers_core.db.CustomerStatusDB;

/**
 * wrapper / service "API" class for DAO services
 * 
 * @author pwner
 *
 */
public class CustomerDataService {

	private CustomerDAO customerDAO;
	private CustomerStatusDAO customerStatusDAO;
	private CommentDAO commentDAO;

	/**
	 * default constructor
	 */
	public CustomerDataService() {

		CustomerCoreSessionProvider sessionProvider = new CustomerCoreSessionProvider();
		commentDAO = new CommentDAO(sessionProvider);
		customerStatusDAO = new CustomerStatusDAO(sessionProvider);
		customerDAO = new CustomerDAO(sessionProvider);
	}

	/**
	 * constructor for custom session provider
	 * 
	 * @param sessionProvider
	 */
	public CustomerDataService(HibernateSessionProvider sessionProvider) {

		commentDAO = new CommentDAO(sessionProvider);
		customerStatusDAO = new CustomerStatusDAO(sessionProvider);
		customerDAO = new CustomerDAO(sessionProvider);
	}

	public void saveCustomer(CustomerDB customer) {
		customerDAO.save(customer);
	}

	public CustomerDB getCustomerById(int customerId) {
		return customerDAO.get(customerId);
	}

	public List<CustomerStatusDB> getCustomerStatuses() {
		return this.customerStatusDAO.getCustomerStatuses();
	}

	public List<CustomerDB> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}

	public void saveComment(CommentDB comment) {
		this.commentDAO.save(comment);
	}

	public List<CommentDB> getCommentsForCustomer(CustomerDB customer) {
		return this.commentDAO.getCommentsForCustomer(customer);
	}
}
