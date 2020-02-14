package com.qa.imssobol.services;

import static org.junit.Assert.assertEquals;

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
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.imssobol.persistence.dao.MysqlCustomerDao;
import com.qa.imssobol.persistence.dao.MysqlCustomerDaoIT;
import com.qa.imssobol.persistence.domain.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServicesIT {
	public static final Logger LOGGER = Logger.getLogger(CustomerServicesIT.class);
	
	private Customer customer; 

	private static Connection conn;
	
	private CustomerServices customerService;
	
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
	public void init() {
		customer = new Customer("Bobby");
		custDao = new MysqlCustomerDao(conn);
		customerService = new CustomerServices(custDao);
		
	}
	
	@Test
	public void createCustomerTest() {
		customer.setName("One!");
		Customer otherCustomer = new Customer("One!");
		customerService.create(customer);
		ArrayList<Customer> customers = (ArrayList<Customer>) customerService.readAll();
		assertEquals(otherCustomer.getName(), customers.get(customers.size()-1).getName());
	}

	@Test
	public void updateTest() {
		customer.setName("NewName!");
		ArrayList<Customer> customers = (ArrayList<Customer>) customerService.readAll();
		int lastId = customers.get(customers.size()-1).getId();
		customerService.update(lastId, customer);
		customers = (ArrayList<Customer>) customerService.readAll();
		assertEquals(customer.getName(),customerService.update(lastId, customer).getName());
	}
	
	@Test
	public void deleteTest() {
		ArrayList<Customer> customers = (ArrayList<Customer>) customerService.readAll();
		int lastId = customers.get(customers.size()-1).getId();
		assertEquals(true,customerService.delete(lastId));
	}
	
	@Test
	public void readByIdTest() {
		ArrayList<Customer> customers = (ArrayList<Customer>) customerService.readAll();
		Customer lastCustomer = customers.get(customers.size()-1);
		
		assertEquals(lastCustomer,customerService.readById(lastCustomer.getId()));
	}

}
