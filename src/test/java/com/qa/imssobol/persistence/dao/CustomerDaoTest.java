package com.qa.imssobol.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.imssobol.persistence.domain.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDaoTest {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerDaoTest.class);

	private Customer customer;
	private static Connection conn;
	
	@Spy
	@InjectMocks
	private MysqlCustomerDao custDao;

	
	@BeforeClass
	public static void login() throws SQLException {

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims", "root", "root");
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
	}
	
	@Before
	public void setUpCustomer() {
		customer = new Customer();
		custDao = new MysqlCustomerDao(conn);
	}

	@Test
	public void customerDaoAddTest() throws SQLException {
		customer.setName("One!");
		Customer otherCustomer = new Customer("One!");
		custDao.create(customer);
		assertEquals(otherCustomer.getName(), custDao.readLatest().getName());

	}

	@Test
	public void customerDaoReadByIntTest() throws SQLException {
		assertEquals(null, custDao.readById(1000000).getName());
	}
	
	@Test
	public void customerUpdateTest() {
		custDao.create(new Customer("Bobby Tables"));
		ArrayList<Customer> customers = custDao.readAll();
		int idLast = customers.get(customers.size()-1).getId();
		custDao.update(idLast, new Customer("Billy Tables"));
		assertEquals("Billy Tables",custDao.readById(idLast).getName());
	}
	
	
	@Test
	public void customerDeleteTest() {
		custDao.create(new Customer("Bobby T."));
		ArrayList<Customer> customers = custDao.readAll();
		int idLast = customers.get(customers.size()-1).getId();
		assertTrue(custDao.delete(idLast));
	}

}
