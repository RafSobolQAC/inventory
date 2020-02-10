package com.qa.controller;

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
	
	public void readAll() {
		for(Customer customer: customerService.readAll()) {
			LOGGER.info(customer.toString());
		}
	}

	public void create() {
		LOGGER.info("Please enter a first name");
		String name = Utils.getInput();
		customerService.create(new Customer(name));
		LOGGER.info("Customer created");
	}

	public void update() {
		
	}

	public void delete() {
		
	}
	
}
