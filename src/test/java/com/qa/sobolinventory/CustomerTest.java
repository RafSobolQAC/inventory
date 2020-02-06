package com.qa.sobolinventory;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.MysqlCustomerDao;
import com.qa.persistence.domain.Customer;

public class CustomerTest {

	Customer customer = new Customer();

	@Before
	public void init() {
		customer = new Customer();
	}

	@Test
	public void customerAddTest() {
		customer.setId(2);
		customer.setName("TestName");
		Customer customer2 = new Customer(2, "TestName");
		assertEquals(customer.getId(), customer2.getId());
		assertEquals(customer.getName(), customer2.getName());

	}

	@Test
	public void customerToStringTest() {
		Customer customer2 = new Customer("NoIDTest");
		customer2.setId(1);
		assertEquals(customer2.toString(), "1 NoIDTest");
	}

	@Test
	public void customerDaoTest() {
		try {
			final String READLAST = "SELECT * FROM customers ORDER BY id DESC LIMIT 1";
			MysqlCustomerDao custDao = new MysqlCustomerDao();
			customer.setName("Chris Testins");
			custDao.create(customer);
			int id = 0;
			Customer testins = null;
			ArrayList<Customer> customers = custDao.readAll();
			for (Customer cust : customers) {
				if (cust.getName() == "Chris Testins") {
					id = cust.getId();
					testins = cust;
				}
			}
			Customer readById = custDao.readById(id);
			assertEquals(readById,testins);
			
			
		} catch (SQLException e) {

		}
	}
}
