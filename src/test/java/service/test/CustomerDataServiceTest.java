package service.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import customer_core.service.CustomerDataService;
import customers_core.dao.CustomerCoreSessionProvider;
import customers_core.db.CommentDB;
import customers_core.db.CustomerDB;
import dao.test.BaseTest;
import service.test.TestObjectFactory.CustomerStatusDBFactory;

public class CustomerDataServiceTest extends BaseTest {

	/**
	 * service test here
	 */

	@Test
	public void saveCustomerTest() {
		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());
		saveAndLoadCustomer(customerDataService);

	}

	private CustomerDB saveAndLoadCustomer(CustomerDataService customerDataService) {

		CustomerDB newCustomer = TestObjectFactory.CustomerDBFactory
				.getTestCustomerObject(CustomerStatusDBFactory.getStatusCurrent());

		customerDataService.saveCustomer(newCustomer);

		int newCustomerId = newCustomer.getId();
		Assert.assertNotNull("new customer id created", newCustomerId);
		logger.info("New customer saved with id " + newCustomerId);

		CustomerDB loadedNewCustomer = customerDataService.getCustomerById(newCustomerId);

		Assert.assertNotNull("new customer load", loadedNewCustomer);

		Assert.assertEquals("id match", newCustomer.getId(), loadedNewCustomer.getId());
		Assert.assertEquals("address match", newCustomer.getCustomerAddress(), loadedNewCustomer.getCustomerAddress());
		Assert.assertEquals("phone match", newCustomer.getCustomerPhone(), loadedNewCustomer.getCustomerPhone());
		Assert.assertEquals("status match", newCustomer.getCustomerStatus(), loadedNewCustomer.getCustomerStatus());
		Assert.assertEquals("created match", newCustomer.getCreated(), loadedNewCustomer.getCreated());
		Assert.assertEquals("first name match", newCustomer.getFirstName(), loadedNewCustomer.getFirstName());
		Assert.assertEquals("last name match", newCustomer.getLastName(), loadedNewCustomer.getLastName());

		return loadedNewCustomer;
	}

	@Test
	public void getAllCustomersTest() {

		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());

		CustomerDB newCustomer1 = TestObjectFactory.CustomerDBFactory
				.getTestCustomerObject(CustomerStatusDBFactory.getStatusCurrent());
		String testCustomer1FirstName = "test customer 1 first name";
		String testCustomer1LastName = "test customer 1 last name";
		newCustomer1.setFirstName(testCustomer1FirstName);
		newCustomer1.setLastName(testCustomer1LastName);
		customerDataService.saveCustomer(newCustomer1);

		CustomerDB newCustomer2 = TestObjectFactory.CustomerDBFactory
				.getTestCustomerObject(CustomerStatusDBFactory.getStatusCurrent());
		String testCustomer2FirstName = "test customer 2 first name";
		String testCustomer2LastName = "test customer 2 last name";
		newCustomer2.setFirstName(testCustomer2FirstName);
		newCustomer2.setLastName(testCustomer2LastName);
		customerDataService.saveCustomer(newCustomer2);

		List<CustomerDB> allCustomers = customerDataService.getAllCustomers();
		Assert.assertNotNull("customers loaded", allCustomers);
		logger.info("customers entries found " + allCustomers.size());
		Assert.assertTrue("customer entries quantity", allCustomers.size() >= 2);

		Assert.assertTrue("customer entries contains customer 1", allCustomers.contains(newCustomer1));
		Assert.assertTrue("customer entries contains customer 2", allCustomers.contains(newCustomer2));

	}

	@Test
	public void getCommentsForCustomerTest() {

		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());
		CustomerDB newCustomer = saveAndLoadCustomer(customerDataService);
		String commentText = "text \n text";
		CommentDB newComment = new CommentDB(newCustomer, commentText);
		customerDataService.saveComment(newComment);
		Assert.assertNotNull(newComment.getId());
		List<CommentDB> loadedComments = customerDataService.getCommentsForCustomer(newCustomer);
		Assert.assertTrue("contains comment for customer", loadedComments.contains(newComment));

		CommentDB loadedComment = null;

		for (CommentDB comment : loadedComments) {
			if (comment.getId() == newComment.getId()) {
				loadedComment = comment;
			}
		}
		Assert.assertNotNull("loaded comment found", loadedComment);
		Assert.assertTrue(commentText.equals(loadedComment.getCommentText()));

	}

	@Test
	public void getCustomerByIdTest() {

	}

	@Test
	public void getCustomerStatuesTest() {

	}

	@Test
	public void saveCommentTest() {

	}

}
