package com.qa.sobolinventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	public Connector(String url) {
		Connection connect = null;
//		Statement statement = null;
//		PreparedStatement preparedStatement = null;
		try {
//            Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url,"root","root");
//            statement = connect.createStatement();
			Customer billy = new Customer("Billy");
			System.out.println(billy.getName());
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	
}
