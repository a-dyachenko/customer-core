package customers_core.dao;

import org.hibernate.Session;

public class CustomerCoreSessionProvider implements HibernateSessionProvider {

	@Override
	public Session getSession() {
		return HibernateUtil.getSession();
	}

}
