package com.qa.imssobol.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	private Customer customer;
	private Customer other;

	@Before
	public void init() {
		
		customer = new Customer();
		other = new Customer();

	}

	@Test
	public void customerAddTest() {
		customer.setId(2);
		customer.setName("TestName");
		other = new Customer(2,"TestName");
		assertEquals(customer.getId(), other.getId());
		assertEquals(customer.getName(), other.getName());

	}

	@Test
	public void customerToStringTest() {
		other = new Customer("NoIDTest");
		other.setId(1);
		assertEquals("1 NoIDTest", other.toString());
	}
	@Test
	public void settersTest() {		
		customer.setId(-1);
		assertEquals(-1,customer.getId());
		customer.setName(null);
		assertNull(customer.getName());

		
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(customer.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(customer.equals(new Object()));
	}
	

	
	@Test
	public void checkEquality() {
		assertTrue(customer.equals(customer));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(customer.equals(other));
	}
	
	
	@Test
	public void customerNamesNotEqual() {
		other.setName("rhys");
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		customer.setName(null);
		assertTrue(customer.equals(other));
	}
	
	@Test
	public void someId() {
		customer.setId(100);
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void zeroIdOnBoth() {
		customer.setId(0);
		other.setId(0);
		assertTrue(customer.equals(other));
	}
		
	
	@Test
	public void constructorWithoutId() {
		Customer customer = new Customer("Chris");
		assertNotNull(customer.getName());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(customer.hashCode(), other.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		customer = new Customer(0, null);
		other = new Customer(0, null);
		assertEquals(customer.hashCode(), other.hashCode());
	}
	
	@Test
	public void equalsOneNull() {
		customer = new Customer(0,null);
		other = new Customer(0,"NotNull");
		assertFalse(customer.equals(other));
	}

	@Test
	public void equalsBothDifferent() {
		customer = new Customer(0,"A");
		other = new Customer(0,"B");
		assertFalse(customer.equals(other));
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
