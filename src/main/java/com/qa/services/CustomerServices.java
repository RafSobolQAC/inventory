package com.qa.services;

import java.util.List;

import com.qa.persistence.dao.Dao;
import com.qa.persistence.domain.Customer;

public class CustomerServices implements CrudServices<Customer> {

	Dao<Customer> customerDao;
	
	public CustomerServices(Dao<Customer> customerDao) {
		this.customerDao = customerDao;
	}
	
	public List<Customer> readAll() {
		return customerDao.readAll();
	}

	public Customer create(Customer customer) {
		return customerDao.create(customer);
	}

	public Customer update(int id, Customer t) {
		return customerDao.update(id, t);
	}

	public boolean delete(int id) {
		return customerDao.delete(id);
	}

	@Override
	public Customer readById(int id) {
		return customerDao.readById(id);
	}

}
