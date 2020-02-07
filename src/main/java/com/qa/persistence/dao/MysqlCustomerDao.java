package com.qa.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Customer;
import com.qa.sobolinventory.Connector;
import com.qa.utils.Login;

public class MysqlCustomerDao implements Dao<Customer> {

	public static final Logger LOGGER = Logger.getLogger(MysqlCustomerDao.class);

	private Connection connection;
	private static final String INSERT = "INSERT INTO customers (name) VALUES (?)";
	private static final String UPDATE = "UPDATE customers SET name=? WHERE id=?";
	private static final String READBYID = "SELECT * FROM customers WHERE id=?";
	private static final String DELETE = "DELETE FROM customers WHERE id=?";
	private static final String READALL = "SELECT * FROM customers";

	public MysqlCustomerDao() throws SQLException {
		this.connection = DriverManager.getConnection("jdbc:mysql://34.89.63.19:3306/inventory", "root",
				Login.getPassword());
	}

	public boolean create(Customer t) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT)) {

			ps.setString(1, t.getName());

			ps.executeUpdate();

			LOGGER.info(("Added customer: " + t.toString()));
			return true;

		} catch (SQLException e) {

			LOGGER.warn(e.getMessage());
			return false;
		}

	}

	public Customer readById(int id) {
		Customer customer = null;
		try (PreparedStatement ps = connection.prepareStatement(READBYID)) {
//			PreparedStatement ps = connection.prepareStatement(READBYID);

			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				customer = new Customer();
				customer.setName(resultSet.getString("name"));
				customer.setId(resultSet.getInt("id"));
			} else {
				LOGGER.warn("Customer with ID does not exist!");
				return new Customer();
			}

		} catch (SQLException e) {

			LOGGER.warn(e.getMessage());
			e.printStackTrace();
		}
		return customer;
	}

	public ArrayList<Customer> readAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(READALL)) {
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(READALL);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				customers.add(new Customer(id, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return customers;
	}

	public boolean update(int id, Customer t) {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {

			ps.setString(1, t.getName());
			ps.setInt(2, id);

			ps.executeUpdate();
//			ps.close();

			System.out.println("Customer with id " + id + " got updated: " + t.toString());
			return true;

		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
			e.printStackTrace();
			return false;
		}

	}

	public boolean delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE)) {

			ps.setInt(1, id);

			ps.executeUpdate();
//			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
