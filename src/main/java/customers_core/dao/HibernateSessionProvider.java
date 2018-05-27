package customers_core.dao;

import org.hibernate.Session;

public interface HibernateSessionProvider {

	public Session getSession();
}
