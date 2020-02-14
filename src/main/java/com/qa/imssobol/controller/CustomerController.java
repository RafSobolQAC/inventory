package com.qa.imssobol.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.imssobol.persistence.domain.Customer;
import com.qa.imssobol.services.CrudServices;
import com.qa.imssobol.utils.Utils;

public class CustomerController implements CrudController<Customer>{
	
	public static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	private CrudServices<Customer> customerService;
	/**
	 * Controller - takes user input, sends it over to the customerService.
	 * @param customerService
	 */
	public CustomerController(CrudServices<Customer> customerService) {
		this.customerService = customerService;
	}
	
	/**
	 * Local implementation of the Utils.getInput method, which provides user-input sanitised strings.
	 * @return a sanitised String
	 */
	public String getInput() {
		return Utils.getInput();
	}
	
	/**
	 * Local implementation of the Utils.getIntInput method, which provides user-input integers
	 * @return an integer
	 */
	public int getIntInput() {
		return Utils.getIntInput(LOGGER);
	}
	
	
	/**
	 * Reads all customers from the database, via the customerService class.
	 * @return a list of Customers
	 */
	public List<Customer> readAll() {
		List<Customer> customers = customerService.readAll();
		for(Customer customer: customers) {
			LOGGER.info(customer.toString());
		}
		return customers;
	}
	/**
	 * Creates a new customer with user-input data.
	 * @return the Customer created
	 */
	public Customer create() {
		LOGGER.info("Please enter a first name");
		String name = getInput();
		LOGGER.info("Customer being created. ");
		Customer customer = customerService.create(new Customer(name));
		LOGGER.info("Creation complete!");
		return customer;
	}

	/**
	 * Updates a customer based on user-input data.
	 * @return the newly-updated customer
	 */
	public Customer update() {
		LOGGER.info("Which customer to update? (ID)");
		Integer id = getIntInput();
		LOGGER.info("What's their new name? ");
		String newName = getInput();
		Customer customer = customerService.update(id, new Customer(newName));
		LOGGER.info("Update complete!");
		return customer;
	}

	/**
	 * Deletes a customer with the provided id.
	 * @return true if no exceptions were caught, false otherwise
	 */
	public boolean delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		int id = getIntInput();
		boolean wasDeleted = customerService.delete(id);
		LOGGER.info("Customer was deleted!");
		return wasDeleted;

	}
/**
 * Reads a customer with the user-input ID from the database.
 * @return the Customer
 */
	@Override
	public Customer readById() {
		LOGGER.info("Which customer ID to access? ");
		int id = getIntInput();
		Customer customer = customerService.readById(id);
		LOGGER.info(customer.toString());
		return customer;
	}
	
}
