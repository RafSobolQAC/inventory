package com.qa;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qa.sobolinventory.Connector;

public class Ims {
	protected Connector connector;
	public static final Logger LOGGER = Logger.getLogger(Ims.class);
	protected Connection connection;
	
	public Ims() {
		try {
			connector = new Connector("jdbc:mysql://34.89.63.19:3306/inventory");
			connection = connector.getConnection();
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
			LOGGER.warn(e.getCause());
			LOGGER.warn(e.getStackTrace());
		}
		
	}
	
	
	
	public void imsRunner() {
		
	}
	
	
}
