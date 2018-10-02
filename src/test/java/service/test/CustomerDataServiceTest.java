package service.test;

import org.junit.Assert;
import org.junit.Test;

import customer_core.service.CustomerDataService;
import customers_core.dao.CustomerCoreSessionProvider;
import customers_core.db.CustomerDB;
import dao.test.BaseTest;
import service.test.TestObjectFactory.CustomerStatusDBFactory;

public class CustomerDataServiceTest extends BaseTest {

	/**
	 * service test here
	 */

	@Test
	public void saveCustomerTest() {

		CustomerDB newCustomer = TestObjectFactory.CustomerDBFactory
				.getTestCustomerObject(CustomerStatusDBFactory.getStatusCurrent());

		CustomerDataService customerDataService = new CustomerDataService(new CustomerCoreSessionProvider());
		customerDataService.saveCustomer(newCustomer);

		int newCustomerId = newCustomer.getId();
		Assert.assertNotNull("new customer id created", newCustomerId);
		logger.info("New customer saved with id " + newCustomerId);

		CustomerDB loadedNewCustomer = customerDataService.getCustomerById(newCustomerId);

		Assert.assertNotNull("new customer load", loadedNewCustomer);

		Assert.assertEquals("id match", newCustomer.getId(), loadedNewCustomer.getId());
		Assert.assertEquals("address match", newCustomer.getCustomerAddress(), loadedNewCustomer.getCustomerAddress());
		Assert.assertEquals("address match", newCustomer.getCustomerPhone(), loadedNewCustomer.getCustomerPhone());
		Assert.assertEquals("address match", newCustomer.getCustomerStatus(), loadedNewCustomer.getCustomerStatus());
		Assert.assertEquals("address match", newCustomer.getCreated(), loadedNewCustomer.getCreated());
		Assert.assertEquals("address match", newCustomer.getFirstName(), loadedNewCustomer.getFirstName());
		Assert.assertEquals("address match", newCustomer.getLastName(), loadedNewCustomer.getLastName());

	}

	@Test
	public void getAllCustomersTest() {

	}

	@Test
	public void getCommentsForCustomerTest() {

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
