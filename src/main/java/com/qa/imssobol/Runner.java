package com.qa.imssobol;

import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Runner {
	public static final Logger LOGGER = Logger.getLogger(Runner.class);

	public static void main(String[] args) throws SQLException {
		Ims ims = new Ims("jdbc:mysql://34.89.63.19:3306/inventory");
		ims.imsRunner();
	}
}