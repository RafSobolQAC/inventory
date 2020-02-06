package com.qa;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.qa.persistence.dao.MysqlCustomerDao;
import com.qa.persistence.domain.Customer;

public class Runner {
	public static final Logger LOGGER = Logger.getLogger(Runner.class);

	public static void main(String[] args) throws SQLException {
		
		MysqlCustomerDao customerDao = new MysqlCustomerDao();
		Customer cust1 = new Customer("Chris Perrins");
		Customer cust2 = new Customer("Not Chris");
		Customer cust3 = new Customer("Wont B. Chris");
		Customer cust4 = new Customer("Will B. Chris");
		Customer cust4New = new Customer("Is Now Chris");
		
		customerDao.create(cust1);
		customerDao.create(cust2);
		customerDao.create(cust3);
		customerDao.create(cust4);
		customerDao.update(4, cust4New);
		ArrayList<Customer> all = customerDao.readAll();
		for (Customer cust : all) System.out.println(cust);
		customerDao.readById(2).toString();
	}
}
