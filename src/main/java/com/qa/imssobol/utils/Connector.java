package com.qa.imssobol.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Connector {
	private Connection connection;
	private String password;
	private Loginner loginner;
	private Login login;
	public static final Logger LOGGER = Logger.getLogger(Connector.class);

	
	public Connector(String url) throws SQLException {
		password = System.getProperty("env.PWD");
		if (password == null) {
			login = new Login();
			loginner = new Loginner(login);
			loginner.LogIn();
			password = login.getPassword();
		}
		try {
			this.connection = DriverManager.getConnection(url, "root",
					password);
		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
			
		}

	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	
}
