package customers_core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import customers_core.db.CommentDB;
import customers_core.db.CustomerDB;
import customers_core.db.CustomerStatusDB;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try { 
				registry = new StandardServiceRegistryBuilder().configure().build();
 
				MetadataSources sources = new MetadataSources(registry);
				sources.addAnnotatedClass(CustomerStatusDB.class);
				sources.addAnnotatedClass(CommentDB.class);
				sources.addAnnotatedClass(CustomerDB.class);
 
				Metadata metadata = sources.getMetadataBuilder().build();
 
				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch (Exception e) {
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

	public static Session getSession() {

		Session currentSession = getSessionFactory().getCurrentSession();
		if (currentSession.getTransaction() == null || !currentSession.getTransaction().isActive())
			currentSession.beginTransaction();
		return currentSession;
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}