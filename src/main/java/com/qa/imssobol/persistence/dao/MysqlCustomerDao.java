package com.qa.imssobol.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.qa.imssobol.persistence.domain.Customer;
import com.qa.imssobol.utils.Utils;

public class MysqlCustomerDao implements Dao<Customer> {

	public static final Logger LOGGER = Logger.getLogger(MysqlCustomerDao.class);

	private Connection connection;
	private static final String INSERT = "INSERT INTO customers (name) VALUES (?)";
	private static final String UPDATE = "UPDATE customers SET name=? WHERE id=?";
	private static final String READBYID = "SELECT * FROM customers WHERE id=?";
	private static final String DELETE = "DELETE FROM customers WHERE id=?";
	private static final String READALL = "SELECT * FROM customers";

	public MysqlCustomerDao(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Reads the latest Customer from the database (one with the highest ID).
	 * @return a Customer
	 */
	public Customer readLatest() {
		Customer customer = new Customer();
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM customers ORDER BY id DESC LIMIT 1");) {
			if (resultSet.next()) {
				customer.setName(resultSet.getString("name"));
				customer.setId(resultSet.getInt("id"));
				return customer;
			} else {
				LOGGER.warn("There is no customer yet!");
			}

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * Sends a Customer to the database.
	 * @param customer the customer to be created
	 * @return the Customer created
	 */
	public Customer create(Customer customer) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT)) {

			ps.setString(1, customer.getName());

			ps.executeUpdate();

			LOGGER.info(("Added customer: " + customer.toString()));
			return readLatest();

		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		}
		return null;

	}

	
	/**
	 * Reads a customer with the given ID from the database.
	 * @param id customer's ID (in the database)
	 * @return the Customer
	 */
	public Customer readById(int id) {
		Customer customer = null;
		ResultSet resultSet = null;
		try (PreparedStatement ps = connection.prepareStatement(READBYID)) {

			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				customer = new Customer();
				customer.setName(resultSet.getString("name"));
				customer.setId(resultSet.getInt("id"));
			} else {
				LOGGER.warn("Customer with ID does not exist!");
				return new Customer();
			}

		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				Utils.exceptionLogger(e, LOGGER);
			}
		}
		return customer;
	}

	
	/**
	 * Reads all customers from the database, and stores them in an arraylist.
	 * @return an arraylist of Customers 
	 */
	public ArrayList<Customer> readAll() {
		ArrayList<Customer> customers = new ArrayList<>();
		ResultSet resultSet = null;
		try (Statement statement = connection.createStatement()){
			resultSet = statement.executeQuery(READALL);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				customers.add(new Customer(id, name));
			}
		} catch (Exception e) {
			Utils.exceptionLogger(e, LOGGER);
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				Utils.exceptionLogger(e, LOGGER);
			}
		}

		return customers;
	}
	
	
	/**
	 * Updates a customer with the provided id, changing their details into those from the new customer.
	 * @param id customer's id in the database to be modified
	 * @param customer new customer's details (the ID of that customer doesn't affect anything)
	 * @return the new customer
	 */
	public Customer update(int id, Customer t) {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {

			ps.setString(1, t.getName());
			ps.setInt(2, id);

			ps.executeUpdate();

			return readById(id);
		} catch (Exception e) {
			Utils.exceptionLogger(e, LOGGER);
		}
		return null;
	}
	
	
	/**
	 * Deletes a customer with the specified ID.
	 * @param id the customer's id
	 * @return true if no exceptions (whether deleted or not), false otherwise
	 */
	public boolean delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE)) {

			ps.setInt(1, id);

			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			Utils.exceptionLogger(e, LOGGER);
			return false;
		}

	}

}
