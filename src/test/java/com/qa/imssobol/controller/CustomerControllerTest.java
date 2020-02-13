package com.qa.imssobol.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.imssobol.persistence.domain.Customer;
import com.qa.imssobol.services.CustomerServices;

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
	public void getInputTest() {
		Mockito.doReturn("TestInput").when(customerController).getInput();
		assertEquals("TestInput",customerController.getInput());
	}
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
		Mockito.when(customerService.update(id, customer)).thenReturn(customer);
		assertEquals(customer, customerController.update());
	}
	
	@Test
	public void readByIdTest() {
		Customer customer = new Customer(1,"Bobby");
		Mockito.doReturn(1).when(customerController).getIntInput();
		Mockito.when(customerService.readById(Mockito.anyInt())).thenReturn(customer);
		assertEquals(customer,customerController.readById());
	}
	
	@Test
	public void deleteTest() {
		int id = 1;
		Mockito.doReturn(id).when(customerController).getIntInput();
		customerController.delete();
		Mockito.when(customerService.delete(id)).thenReturn(true);
		assertTrue(customerController.delete());
	}
	
}
