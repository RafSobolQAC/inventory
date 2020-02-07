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
			this.connection = DriverManager.getConnection(url, "root",
					password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}
		scanner.close();

	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	
}
