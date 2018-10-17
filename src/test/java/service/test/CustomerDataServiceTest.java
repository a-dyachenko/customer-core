package service.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import customer_core.service.CustomerDataService;
import customers_core.dao.CustomerCoreSessionProvider;
import customers_core.db.CommentDB;
import customers_core.db.CustomerDB;
import customers_core.db.CustomerStatusDB;
import dao.test.BaseTest;
import service.test.TestObjectFactory.CustomerStatuses;

public class CustomerDataServiceTest extends BaseTest {

	/**
	 * service test here
	 */

	@Test
	public void saveCustomerTest() {
		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());
		saveAndLoadCustomerCurrent(customerDataService);

	}

	private CustomerDB saveAndLoadCustomerCurrent(CustomerDataService customerDataService) {

		CustomerDB newCustomer = TestObjectFactory.Customers.getTestCustomerObject(CustomerStatuses.getStatusCurrent());

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

		CustomerDB newCustomer1 = TestObjectFactory.Customers
				.getTestCustomerObject(CustomerStatuses.getStatusCurrent());
		String testCustomer1FirstName = "test customer 1 first name";
		String testCustomer1LastName = "test customer 1 last name";
		newCustomer1.setFirstName(testCustomer1FirstName);
		newCustomer1.setLastName(testCustomer1LastName);
		customerDataService.saveCustomer(newCustomer1);

		CustomerDB newCustomer2 = TestObjectFactory.Customers
				.getTestCustomerObject(CustomerStatuses.getStatusCurrent());
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
	public void getCustomerCommentsTest() {

		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());
		CustomerDB newCustomer = saveAndLoadCustomerCurrent(customerDataService);
		String commentText = "text \n text";
		CommentDB newComment = new CommentDB(newCustomer, commentText);
		customerDataService.saveComment(newComment);
		Assert.assertNotNull(newComment.getId());
		List<CommentDB> loadedComments = customerDataService.getCustomerComments(newCustomer.getId());
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

		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());
		CustomerDB savedCustomer = saveAndLoadCustomerCurrent(customerDataService);
		int customerId = savedCustomer.getId();

		CustomerDB getCustomerById = customerDataService.getCustomerById(customerId);
		Assert.assertTrue("customer match", getCustomerById.getId() == savedCustomer.getId());

	}

	@Test
	public void getCustomerStatuesTest() {

		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());
		List<CustomerStatusDB> customerStatuses = customerDataService.getCustomerStatuses();

		CustomerStatusDB statusCurrent = TestObjectFactory.CustomerStatuses.getStatusCurrent();
		CustomerStatusDB statusProspective = TestObjectFactory.CustomerStatuses.getStatusProspective();
		CustomerStatusDB statusNonActive = TestObjectFactory.CustomerStatuses.getStatusNonActive();

		this.logger.info("statuses " + customerStatuses);

		boolean containsStatusCurrent = false;
		boolean containsStatusProspective = false;
		boolean containsStatusNonActive = false;

		for (CustomerStatusDB status : customerStatuses) {

			int statusId = status.getId();
			String statusName = status.getStatusName();
			if (statusId == statusCurrent.getId() && statusCurrent.getStatusName().equals(statusName))
				containsStatusCurrent = true;
			if (statusId == statusProspective.getId() && statusProspective.getStatusName().equals(statusName))
				containsStatusProspective = true;
			if (statusId == statusNonActive.getId() && statusNonActive.getStatusName().equals(statusName))
				containsStatusNonActive = true;

		}

		Assert.assertTrue("statuses contain current", containsStatusCurrent);
		Assert.assertTrue("statuses contain prospective ", containsStatusProspective);
		Assert.assertTrue("statuses contain nonactive", containsStatusNonActive);

	}

	@Test
	public void saveCommentTest() {

		CustomerDataService customerDataService = CustomerDataService
				.getCustomerDataService(new CustomerCoreSessionProvider());
		CustomerDB newCustomer = saveAndLoadCustomerCurrent(customerDataService);
		String commentText = "text \n text";
		CommentDB newComment = new CommentDB(newCustomer, commentText);
		customerDataService.saveComment(newComment);
		Assert.assertNotNull(newComment.getId());

	}

}
