package com.qa.sobolinventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class MysqlCustomerDao implements Dao<Customer>{
	private Connection connection;
	private static final String INSERT = "INSERT INTO customers (name) VALUES (?)";
	private static final String UPDATE = "UPDATE customers SET name=? WHERE id=?";
	private static final String READBYID = "SELECT * FROM customers WHERE id=?";
	private static final String DELETE = "DELETE FROM customers WHERE id=?";
	private static final String READALL = "SELECT * FROM customers";
	
	public MysqlCustomerDao() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Password: ");
		String password = scanner.nextLine();
		
		this.connection = DriverManager.getConnection("jdbc:mysql://34.89.63.19:3306/inventory", "root",
				password);
		scanner.close();
}

	public void create(Customer t) {
		try {

			PreparedStatement ps = connection.prepareStatement(INSERT);

			ps.setString(1, t.getName());

			ps.executeUpdate();
			ps.close();

			System.out.println("Added book: " + t.toString());

		} catch (SQLException e) {

			e.printStackTrace();

		}

		
	}
	public Customer readById(int id) {
		Customer customer = null;
		try {
			PreparedStatement ps = connection.prepareStatement(READBYID);
			
			
			ps.setInt(1,id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				customer = new Customer();
				customer.setName(resultSet.getString("name"));
				customer.setId(resultSet.getInt("id"));
				
				
			}
			
			resultSet.close();
			ps.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	public ArrayList<Customer> readAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(READALL);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				customers.add(new Customer(id, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return customers;
	}

	public void update(int id, Customer t) {
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE);

			ps.setString(1, t.getName());
			ps.setInt(2, id);

			ps.executeUpdate();
			ps.close();

			System.out.println("Book with id " + id + " got updated: " + t.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void delete(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement(DELETE);

			ps.setInt(1, id);

			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
