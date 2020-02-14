package com.qa.imssobol;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qa.imssobol.utils.Connector;

public class Runner {
	public static final Logger LOGGER = Logger.getLogger(Runner.class);

	public static void main(String[] args) throws SQLException {
		Connector connector = new Connector("jdbc:mysql://34.89.63.19:3306/inventory");
		Ims ims = new Ims(connector);
		ims.imsRunner();
	}
}