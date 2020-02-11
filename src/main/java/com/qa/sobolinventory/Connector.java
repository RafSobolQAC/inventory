package com.qa.sobolinventory;

import java.sql.Connection;
import java.io.File;
import java.net.URL;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Connector {
	private Connection connection;
	private String password;
	private static final String CONFIG_FILE = "/app.properties";
	public File readFileFromClasspath() {
        URL fileUrl = getClass().getResource(CONFIG_FILE);
        return new File(fileUrl.getFile());

	}
	
	public Connector(String url) throws SQLException {
		password = System.getenv("env.PWD");
		if (password == "") {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Password: ");
		password = scanner.nextLine();
		}
		try {
			this.connection = DriverManager.getConnection(url, "root",
					password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}

	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	
}
