package com.qa.imssobol.services;

import java.util.List;

import com.qa.imssobol.persistence.dao.Dao;
import com.qa.imssobol.persistence.domain.Customer;

public class CustomerServices implements CrudServices<Customer> {

	Dao<Customer> customerDao;
	
	public CustomerServices(Dao<Customer> customerDao) {
		this.customerDao = customerDao;
	}
	
	public List<Customer> readAll() {
		return customerDao.readAll();
	}
/**
 * Create a customer.
 * @param customer
 * @return the customer
 */
	public Customer create(Customer customer) {
		return customerDao.create(customer);
	}

	/**
	 * Update a customer.
	 * @param id the id to update
	 * @param customer new customer's details
	 * @return the new customer
	 */
	public Customer update(int id, Customer customer) {
		return customerDao.update(id, customer);
	}

	public boolean delete(int id) {
		return customerDao.delete(id);
	}

	@Override
	public Customer readById(int id) {
		return customerDao.readById(id);
	}

}
