package com.qa.imssobol.persistence.domain;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.imssobol.persistence.dao.MysqlCustomerDao;

public class CustomerTest {
	public static final Logger LOGGER = Logger.getLogger(CustomerTest.class);

	private Customer customer = new Customer();
	private MysqlCustomerDao custDao;
	private static Connection conn;
	@BeforeClass
	public static void login() throws SQLException {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims", "root", "root");
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
		
	}

	@Before
	public void init() throws SQLException {
		
		customer = new Customer();
		custDao = new MysqlCustomerDao(conn);


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
		assertEquals("1 NoIDTest", customer2.toString());
	}

//	@Test
//	public void customerDaoTest() {
//		try {
////			final String READLAST = "SELECT * FROM customers ORDER BY id DESC LIMIT 1";
//			MysqlCustomerDao custDao = new MysqlCustomerDao();
//			customer.setName("Chris Testins");
//			custDao.create(customer);
//			int id = 0;
//			Customer testins = null;
//			ArrayList<Customer> customers = custDao.readAll();
//			for (Customer cust : customers) {
//				if (cust.getName() == "Chris Testins") {
//					id = cust.getId();
//					testins = cust;
//				}
//			}
//			Customer readById = custDao.readById(id);
//			assertEquals(readById,testins);
//			
//			
//		} catch (SQLException e) {
//
//		}
//	}

	
	
	
	
	
	
}
