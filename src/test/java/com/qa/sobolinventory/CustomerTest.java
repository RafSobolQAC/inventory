package com.qa.sobolinventory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.persistence.dao.MysqlCustomerDao;
import com.qa.persistence.domain.Customer;

public class CustomerTest {
	public static final Logger LOGGER = Logger.getLogger(CustomerTest.class);

	private Customer customer = new Customer();
	private MysqlCustomerDao custDao;
	private static Connection conn;
	private static Connector connector;
	@BeforeClass
	public static void login() throws SQLException {
		connector = new Connector("jdbc:mysql://34.89.63.19:3306/inventory");
		conn = connector.getConnection();
		
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
		assertEquals(customer2.toString(), "1 NoIDTest");
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

	@Test
	public void customerDaoAddTest() throws SQLException {
		customer.setName("One!");
		assertTrue(custDao.create(customer));

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
