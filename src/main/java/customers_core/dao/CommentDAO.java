package customers_core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import customers_core.db.CommentDB;
import customers_core.db.CustomerDB;

public class CommentDAO extends DAOService<CommentDB> {

	private static final String FIELD_CUSTOMER = "customer";
	private static final String FIELD_CREATED = "created";

	public CommentDAO(HibernateSessionProvider sessionProvider) {
		super(sessionProvider, CommentDB.class);
	}

	public List<CommentDB> getCommentsForCustomer(CustomerDB customer) {

		CriteriaQuery<CommentDB> criteriaQuery = getCriteriaBuilder().createQuery(CommentDB.class);
		Root<CommentDB> root = criteriaQuery.from(CommentDB.class);
		 
		List<Order> orderList = new ArrayList<>(); 

		orderList.add(getCriteriaBuilder().desc(root.get(FIELD_CREATED))); 
		
		criteriaQuery.select(root).where(getCriteriaBuilder().equal(root.get(FIELD_CUSTOMER), customer))
				.orderBy(orderList);
		 
		Query query = getCurrentSession().createQuery(criteriaQuery);
		@SuppressWarnings("unchecked")
		List<CommentDB> customerComments = (List<CommentDB>) query.getResultList();

		return customerComments;
	}

}
