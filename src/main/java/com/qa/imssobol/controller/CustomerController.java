package com.qa.imssobol.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.imssobol.persistence.domain.Customer;
import com.qa.imssobol.services.CrudServices;
import com.qa.imssobol.utils.Utils;

public class CustomerController implements CrudController<Customer>{
	
	public static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	private CrudServices<Customer> customerService;
	
	public CustomerController(CrudServices<Customer> customerService) {
		this.customerService = customerService;
	}
	
	public String getInput() {
		return Utils.getInput();
	}
	
	public int getIntInput() {
		return Utils.getIntInput(LOGGER);
	}
	
	
	public List<Customer> readAll() {
		List<Customer> customers = customerService.readAll();
		for(Customer customer: customers) {
			LOGGER.info(customer.toString());
		}
		return customers;
	}

	public Customer create() {
		LOGGER.info("Please enter a first name");
		String name = getInput();
		LOGGER.info("Customer being created. ");
		Customer customer = customerService.create(new Customer(name));
		LOGGER.info("Creation complete!");
		return customer;
	}

	public Customer update() {
		LOGGER.info("Which customer to update? (ID)");
		Integer id = getIntInput();
		LOGGER.info("What's their new name? ");
		String newName = getInput();
		Customer customer = customerService.update(id, new Customer(newName));
		LOGGER.info("Update complete!");
		return customer;
	}

	public boolean delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		int id = getIntInput();
		boolean wasDeleted = customerService.delete(id);
		return wasDeleted;

	}

	@Override
	public Customer readById() {
		LOGGER.info("Which customer ID to access? ");
		int id = getIntInput();
		Customer customer = customerService.readById(id);
		LOGGER.info(customer.toString());
		return customer;
	}
	
}
