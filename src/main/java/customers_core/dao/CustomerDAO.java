package customers_core.dao;

import java.util.ArrayList;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import customers_core.db.CustomerDB;

public class CustomerDAO extends DAOService<CustomerDB> {

	public CustomerDAO(HibernateSessionProvider sessionProvider) {
		super(sessionProvider, CustomerDB.class);
	}
	
	public ArrayList<CustomerDB> getAllCustomers() {
		
		CriteriaQuery<CustomerDB> criteriaQuery = getCriteriaBuilder().createQuery(CustomerDB.class);
		Root<CustomerDB> root = criteriaQuery.from(CustomerDB.class);
		criteriaQuery.select(root);
		Query query = getCurrentSession().createQuery(criteriaQuery);
		@SuppressWarnings("unchecked")
		ArrayList<CustomerDB> customerStatuses = (ArrayList<CustomerDB>) query.getResultList();
		
		return customerStatuses; 
	}
	
	

}
