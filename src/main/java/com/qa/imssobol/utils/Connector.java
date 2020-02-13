package com.qa.imssobol.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Connector {
	private Connection connection;
	private String password = "root";
	private String url = "jdbc:mysql://localhost:3306/";
	private Loginner loginner;
	public static final Logger LOGGER = Logger.getLogger(Connector.class);

	public Connector() {
	}

	public String getSystemPwd() {
		return System.getProperty("env.PWD");
	}

	public Connector(String url) {
		this.url = url;
	}
	public void setUpConnector() throws SQLException {
			password = getSystemPwd();
			while (true) {
				if (password == null) {
					loginner = new Loginner();
					password = loginner.logIn();
				}
				try {
					this.connection = setUpConnection(url, "root", password);
					password = null;
					break;
				} catch (SQLException e) {
					Utils.exceptionLogger(e, LOGGER);
					password = null;
				}
			}

	}
	protected Connection setUpConnection(String url, String username, String password) throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	public Connection getConnection() {
		return this.connection;
	}

}
