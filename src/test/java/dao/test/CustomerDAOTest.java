package dao.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import customers_core.db.CommentDB;
import customers_core.db.CustomerDB;
import customers_core.db.CustomerStatusDB;

public class CustomerDAOTest extends BaseTest {

	private static final String STATUS_PROSPECTIVE = "prospective";
	private static final String STATUS_CURRENT = "current";
	private static final String STATUS_NONACTIVE = "non-active";

	@Test
	public void getCustomerStatusTest() {

		CustomerStatusDB customerStatus = this.customerStatusDAO.get(1);
		Assert.assertNotNull("status loaded", customerStatus);
		Assert.assertEquals("status match", STATUS_PROSPECTIVE, customerStatus.getStatusName());

		customerStatus = this.customerStatusDAO.get(2);
		Assert.assertNotNull("status loaded", customerStatus);
		Assert.assertEquals("status match", STATUS_CURRENT, customerStatus.getStatusName());

		customerStatus = this.customerStatusDAO.get(3);
		Assert.assertNotNull("status loaded", customerStatus);
		Assert.assertEquals("status match", STATUS_NONACTIVE, customerStatus.getStatusName());

		customerStatus = this.customerStatusDAO.getCustomerStatusByName(STATUS_CURRENT);
		Assert.assertNotNull("status loaded", customerStatus);
		Assert.assertEquals("status match", STATUS_CURRENT, customerStatus.getStatusName());
	}
	
	@Test
	public void getCustomerStatusesTest() {
		ArrayList<CustomerStatusDB> customerStatuses = this.customerStatusDAO.getCustomerStatuses();
		Assert.assertNotNull("customer statuses loaded", customerStatuses);
		Assert.assertEquals("3 customer statuses found", 3, customerStatuses.size());
	}

	@Test
	public void createCustomerTest() {

		CustomerDB customer = createNewCustomer();

		System.out.println(customer.getCreated());
		Assert.assertNotNull("customer id generated", customer.getId());
		System.out.println("Customer created with id " + customer.getId());
	}

	private CustomerDB createNewCustomer() {

		CustomerDB customer = new CustomerDB();
		customer.setCustomerStatus(customerStatusDAO.getStatusProspective());
		String customerPhone = "012345678";

		customer.setCustomerPhone(customerPhone);
		String firstName = "Alexander";
		String lastName = "Dyachenko";
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		String customerAddress = "Main street 1, Supertown, Super Country";
		customer.setCustomerAddress(customerAddress);

		customerDAO.save(customer);
		return customer;
	}

	@Test
	public void createCommentTest() {

		CustomerDB customer = createNewCustomer();

		String comment1Text = "this is a really nice person";
		CommentDB comment1 = new CommentDB(customer, comment1Text);
		String comment2Text = "I completely agree";
		CommentDB comment2 = new CommentDB(customer, comment2Text);

		commentDAO.save(comment1);
		commentDAO.save(comment2);

		Assert.assertNotNull("comment saved with new id", comment1.getId());
		Assert.assertNotNull("comment saved with new id", comment2.getId());

		ArrayList<CommentDB> customerComments = commentDAO.getCommentsForCustomer(customer);
		Assert.assertNotNull("comments found for customer", customerComments);
		Assert.assertEquals("proper amount of comments found for customer", 2, customerComments.size());

		System.out.println(customerComments.get(0).getCommentText());
		System.out.println(customerComments.get(1).getCommentText());

		Assert.assertEquals("comment1 text match", comment1Text, customerComments.get(0).getCommentText());
		Assert.assertEquals("comment1 text match", comment2Text, customerComments.get(1).getCommentText());
	}

}
