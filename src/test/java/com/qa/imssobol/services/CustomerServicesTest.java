package com.qa.imssobol.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.imssobol.persistence.dao.MysqlCustomerDao;
import com.qa.imssobol.persistence.domain.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServicesTest {

	private Customer customer; 
	
	@Mock
	private MysqlCustomerDao custDaoMock;
	
	@InjectMocks
	private CustomerServices customerService;
	
	@Before
	public void init() {
		customer = new Customer("Bobby");
	}
	@Test
	public void readAllTest() {
		ArrayList<Customer> customers = new ArrayList<>();
		customers.add(customer);
		Mockito.when(custDaoMock.readAll()).thenReturn(customers);
		assertEquals(customers,customerService.readAll());
	}
	
	@Test
	public void createTest() {
		Mockito.when(custDaoMock.create(Mockito.any(Customer.class))).thenReturn(customer);
		assertEquals(customer,customerService.create(customer));
	}
	
	@Test
	public void updateTest() {
		Mockito.when(custDaoMock.update(Mockito.anyInt(), Mockito.any(Customer.class))).thenReturn(customer);
		assertEquals(customer,customerService.update(1, customer));
	}
	
	@Test
	public void deleteTest() {
		Mockito.when(custDaoMock.delete(Mockito.anyInt())).thenReturn(true);
		assertEquals(true,customerService.delete(1));
	}
	
	@Test
	public void readByIdTest() {
		Mockito.when(custDaoMock.readById(Mockito.anyInt())).thenReturn(customer);
		assertEquals(customer,customerService.readById(1));
	}
}
