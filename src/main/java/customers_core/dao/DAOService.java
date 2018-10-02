package customers_core.dao;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;

import customers_core.db.BaseDBObject;

public class DAOService<DBObject extends BaseDBObject> {

	Class<DBObject> cl;
	private HibernateSessionProvider sessionProvider;
	private CriteriaBuilder criteriaBuilder;

	public DAOService(HibernateSessionProvider sessionProvider, Class<DBObject> cl) {
		this.sessionProvider = sessionProvider;
		this.cl = cl;
	}

	public void save(final DBObject object) {
		sessionProvider.getSession().saveOrUpdate(object);
	}

	public DBObject get(final int id) {

		DBObject daoClass = (DBObject) sessionProvider.getSession().get(cl, id);
		return daoClass;
	}
	
	public void delete(final DBObject object) {
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
