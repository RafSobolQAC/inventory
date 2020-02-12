package com.qa.imssobol.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Connector {
	private Connection connection;
	private String password = "";
	private Loginner loginner;
	private Login login;
	public static final Logger LOGGER = Logger.getLogger(Connector.class);
	
	public Connector() throws SQLException {
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		}
	}

	public Connector(String url) throws SQLException {
		password = System.getProperty("env.PWD");
		while (true) {
			if (password == null) {
				login = new Login();
				loginner = new Loginner(login);
				loginner.logIn();
				password = login.getPassword();
			}
			try {
				this.connection = DriverManager.getConnection(url, "root", password);
				password = null;
				break;
			} catch (SQLException e) {
				Utils.exceptionLogger(e, LOGGER);
				password = null;
			}
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

}
