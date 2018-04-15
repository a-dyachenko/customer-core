package customers_core.dao;

import org.hibernate.Session;

public class HibernateSessionProvider {

	public Session getSession() {
		return HibernateUtil.getSession();
	}

}
