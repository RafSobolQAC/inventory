package com.qa.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Customer;
import com.qa.utils.Utils;

public class MysqlCustomerDao implements Dao<Customer> {

	public static final Logger LOGGER = Logger.getLogger(MysqlCustomerDao.class);

	private Connection connection;
	private static final String INSERT = "INSERT INTO customers (name) VALUES (?)";
	private static final String UPDATE = "UPDATE customers SET name=? WHERE id=?";
	private static final String READBYID = "SELECT * FROM customers WHERE id=?";
	private static final String DELETE = "DELETE FROM customers WHERE id=?";
	private static final String READALL = "SELECT * FROM customers";

	public MysqlCustomerDao(Connection connection) throws SQLException {
		this.connection = connection;
	}
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

	public Customer create(Customer t) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT)) {

			ps.setString(1, t.getName());

			ps.executeUpdate();

			LOGGER.info(("Added customer: " + t.toString()));
			return readLatest();

		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		}
		return null;

	}

	public Customer readById(int id) {
		Customer customer = null;
		ResultSet resultSet = null;
		try (PreparedStatement ps = connection.prepareStatement(READBYID)) {
//			PreparedStatement ps = connection.prepareStatement(READBYID);

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
			}
			;
		}
		return customer;
	}

	public ArrayList<Customer> readAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		ResultSet resultSet = null;
		try (Statement statement = connection.createStatement()){
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(READALL);
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
			}
			;
		}

		return customers;
	}

	public Customer update(int id, Customer t) {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {

			ps.setString(1, t.getName());
			ps.setInt(2, id);

			ps.executeUpdate();
//			ps.close();

			System.out.println("Customer with id " + id + " got updated: " + t.toString());
			return readById(id);
		} catch (Exception e) {
			Utils.exceptionLogger(e, LOGGER);
		}
		return null;
	}

	public boolean delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE)) {

			ps.setInt(1, id);

			ps.executeUpdate();
//			ps.close();
			return true;
		} catch (Exception e) {
			Utils.exceptionLogger(e, LOGGER);
			return false;
		}

	}

}
