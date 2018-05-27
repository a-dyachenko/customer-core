package customers_core.dao;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;

import customers_core.db.BaseObjectDB;

public class DAOService<DAOClass extends BaseObjectDB> {

	Class<DAOClass> cl;
	private CustomerCoreSessionProvider sessionProvider;
	private CriteriaBuilder criteriaBuilder;

	public DAOService(CustomerCoreSessionProvider sessionProvider, Class<DAOClass> cl) {
		this.sessionProvider = sessionProvider;
		this.cl = cl;
	}

	public void save(final DAOClass object) {
		sessionProvider.getSession().saveOrUpdate(object);
	}

	public DAOClass get(final int id) {

		DAOClass daoClass = (DAOClass) sessionProvider.getSession().get(cl, id);
		return daoClass;
	}
	
	public void delete(final DAOClass object) {
		sessionProvider.getSession().delete(object);
	}

	public Session getCurrentSession() {
		return sessionProvider.getSession();
	}

	protected CriteriaBuilder getCriteriaBuilder() {
		if (criteriaBuilder == null)
			criteriaBuilder = sessionProvider.getSession().getCriteriaBuilder();
		return criteriaBuilder;
	}

}
