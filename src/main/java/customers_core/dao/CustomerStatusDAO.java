package customers_core.dao;

import java.util.ArrayList;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import customers_core.db.CustomerStatusDB;

public class CustomerStatusDAO extends DAOService<CustomerStatusDB> {

	private static final int STATUS_PROSPECTIVE = 1;
	private static final int STATUS_CURRENT = 2;
	private static final int STATUS_NONACTIVE = 3;

	public CustomerStatusDAO(HibernateSessionProvider sessionProvider) {
		super(sessionProvider, CustomerStatusDB.class);
	}

	public CustomerStatusDB getCustomerStatusByName(String statusName) {

		CriteriaQuery<CustomerStatusDB> criteriaQuery = getCriteriaBuilder().createQuery(CustomerStatusDB.class);
		Root<CustomerStatusDB> root = criteriaQuery.from(CustomerStatusDB.class);
		criteriaQuery.select(root).where(getCriteriaBuilder().equal(root.get("statusName"), statusName));
		Query query = getCurrentSession().createQuery(criteriaQuery);
		CustomerStatusDB customerStatus = (CustomerStatusDB) query.getSingleResult();

		return customerStatus;
	}

	public CustomerStatusDB getStatusProspective() {
		return this.get(STATUS_PROSPECTIVE);
	}

	public CustomerStatusDB getStatusCurrent() {
		return this.get(STATUS_CURRENT);
	}

	public CustomerStatusDB getStatusNonActive() {
		return this.get(STATUS_NONACTIVE);
	}
	
	public ArrayList<CustomerStatusDB> getCustomerStatuses() {
		
		CriteriaQuery<CustomerStatusDB> criteriaQuery = getCriteriaBuilder().createQuery(CustomerStatusDB.class);
		Root<CustomerStatusDB> root = criteriaQuery.from(CustomerStatusDB.class);
		criteriaQuery.select(root);
		Query query = getCurrentSession().createQuery(criteriaQuery);
		
		@SuppressWarnings("unchecked")
		ArrayList<CustomerStatusDB> customerStatuses = (ArrayList<CustomerStatusDB>) query.getResultList(); 
		
		return customerStatuses;
	}
}
