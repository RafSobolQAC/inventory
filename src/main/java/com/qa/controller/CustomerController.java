package com.qa.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Customer;
import com.qa.services.CrudServices;
import com.qa.utils.Utils;

public class CustomerController implements CrudController<Customer>{
	
	public static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	private CrudServices<Customer> customerService;
	
	public CustomerController(CrudServices<Customer> customerService) {
		this.customerService = customerService;
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
		String name = Utils.getInput();
		LOGGER.info("Customer being created. ");
		Customer customer = customerService.create(new Customer(name));
		LOGGER.info("Creation complete!");
		return customer;
	}

	public Customer update() {
		LOGGER.info("Which customer to update? (ID)");
		int id = Utils.getIntInput(LOGGER);
		LOGGER.info("What's their new name? ");
		String newName = Utils.getInput();
		Customer customer = customerService.update(id, new Customer(newName));
		LOGGER.info("Update complete!");
		return customer;
	}

	public boolean delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		int id = Utils.getIntInput(LOGGER);
		boolean wasDeleted = customerService.delete(id);
		return wasDeleted;

	}

	@Override
	public Customer readById() {
		LOGGER.info("Which customer ID to access? ");
		int id = Utils.getIntInput(LOGGER);
		Customer customer = customerService.readById(id);
		LOGGER.info(customer.toString());
		return customer;
	}
	
}
