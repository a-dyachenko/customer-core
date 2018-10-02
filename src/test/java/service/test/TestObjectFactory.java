package service.test;
import customers_core.db.CustomerDB;
import customers_core.db.CustomerStatusDB;

public class TestObjectFactory {

	public TestObjectFactory() {
	}

	public static class CustomerDBFactory {

		public static CustomerDB getTestCustomerObject(CustomerStatusDB status) {

			CustomerDB customer = new CustomerDB();

			customer.setCustomerAddress("Test address 1");
			customer.setCustomerPhone("0123456");
			customer.setCustomerStatus(status);
			customer.setFirstName("Test");
			customer.setLastName("Testersson");

			return customer;
		}
	}

	public static class CustomerStatusDBFactory {

		private static final String CUSTOMER_STATUS_CURRENT = "current";
		private static final String CUSTOMER_STATUS_NONACTIVE = "non-active";
		private static final String CUSTOMER_STATUS_PROSPECTIVE = "prospective";

		public static CustomerStatusDB getStatusCurrent() {

			CustomerStatusDB statusCurrent = new CustomerStatusDB();
			statusCurrent.setId(2);
			statusCurrent.setStatusName(CUSTOMER_STATUS_CURRENT);
			return statusCurrent;
		}

		public static CustomerStatusDB getStatusNonActive() {

			CustomerStatusDB statusCurrent = new CustomerStatusDB();
			statusCurrent.setId(3);
			statusCurrent.setStatusName(CUSTOMER_STATUS_NONACTIVE);
			return statusCurrent;
		}

		public static CustomerStatusDB getStatusProspective() {

			CustomerStatusDB statusCurrent = new CustomerStatusDB();
			statusCurrent.setId(1);
			statusCurrent.setStatusName(CUSTOMER_STATUS_PROSPECTIVE);
			return statusCurrent;
		}
	}
}
