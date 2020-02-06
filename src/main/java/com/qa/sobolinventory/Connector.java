package com.qa.sobolinventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Connector {
	private Connection connection;
	public Connector(String url) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Password: ");
		String password = scanner.nextLine();
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://34.89.63.19:3306/inventory", "root",
					password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}
		scanner.close();

	}
	
	
	
//	
//	public Connector(String url) {
//		Connection connect = null;
////		Statement statement = null;
////		PreparedStatement preparedStatement = null;
//		try {
////            Class.forName("com.mysql.jdbc.Driver");
//			connect = DriverManager.getConnection(url,"root","root");
////            statement = connect.createStatement();
//			Customer billy = new Customer("Billy");
//			System.out.println(billy.getName());
//			// Do something with the Connection
//		} catch (SQLException ex) {
//			// handle any errors
//			System.out.println("SQLException: " + ex.getMessage());
//			System.out.println("SQLState: " + ex.getSQLState());
//			System.out.println("VendorError: " + ex.getErrorCode());
//		}
//	}
//	
	
}
