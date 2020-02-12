package com.qa.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.persistence.domain.Customer;
import com.qa.services.CustomerServices;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
	
	/**
	 *  The thing I want to fake functionality for
	 */
	@Mock
	private CustomerServices customerService;
	
	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer controller
	 */
	@Spy
	@InjectMocks
	private CustomerController customerController;

	@Test
	public void readAllTest() {
		CustomerController customerController = new CustomerController(customerService);
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("Chris"));
		customers.add(new Customer("Rhys"));
		customers.add(new Customer("Nic"));
		Mockito.when(customerService.readAll()).thenReturn(customers);
		assertEquals(customers, customerController.readAll());
	}

	@Test
	public void createTest() {
		String name = "Chris";
		Mockito.doReturn(name).when(customerController).getInput();
		Customer customer = new Customer(name);
		Customer savedCustomer = new Customer(1, "Chris");
		Mockito.when(customerService.create(customer)).thenReturn(savedCustomer);
		assertEquals(savedCustomer, customerController.create());
	}

	@Test
	public void updateTest() {
		int id = 1;
		String name = "Rhys";
		Mockito.doReturn(id).when(customerController).getIntInput();
		Mockito.doReturn(name).when(customerController).getInput();
		Customer customer = new Customer(name);
//		Mockito.doReturn(customer).when(customerService).update(id,customer);
//		customerService.update(id, new Customer(newName));
		Mockito.when(customerService.update(id, customer)).thenReturn(customer);
		assertEquals(customer, customerController.update());
	}
	

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the delete method
	 */
//	@Test
//	public void deleteTest() {
//		String id = "1";
//		Mockito.doReturn(id).when(customerController).getInput();
//		customerController.delete();
//		Mockito.verify(customerServices, Mockito.times(1)).delete(1);
//	}
	
	@Test
	public void delete2Test() {
		int id = 1;
		Mockito.doReturn(id).when(customerController).getIntInput();
		customerController.delete();
		Mockito.when(customerService.delete(id)).thenReturn(true);
		assertEquals(true,customerController.delete());
	}
	
}
