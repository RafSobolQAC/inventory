package com.qa.imssobol.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Connector {
	private Connection connection;
	private String url = "jdbc:mysql://localhost:3306/";
	public static final Logger LOGGER = Logger.getLogger(Connector.class);

	public Connector() {
	}

	public String getSystemPwd() {
		return System.getProperty("env.PWD");
	}

	public Connector(String url) {
		this.url = url;
	}
/**
 * Sets up the connector. If no password is passed to the system at runtime, it asks for user input.
 * @throws SQLException
 */
	public void setUpConnector() throws SQLException {
		Loginner loginner;
		String pd = "root";
		pd = getSystemPwd();
		while (true) {
			if (pd == null) {
				loginner = new Loginner();
				pd = loginner.logIn();
			}
			try {
				this.connection = setUpConnection(url, "root", pd);
				pd = null;
				break;
			} catch (SQLException e) {
				Utils.exceptionLogger(e, LOGGER);
				pd = null;
			}
		}

	}
/**
 * Creates a connection based on the url and password provided.
 * @param url
 * @param username
 * @param password
 * @return the Connection
 * @throws SQLException
 */
	protected Connection setUpConnection(String url, String username, String password) throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
/**
 *
 * @return a connection to the database
 */
	public Connection getConnection() {
		return this.connection;
	}

}
