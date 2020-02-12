package com.qa.imssobol;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qa.imssobol.controller.CustomerController;
import com.qa.imssobol.persistence.dao.MysqlCustomerDao;
import com.qa.imssobol.services.CustomerServices;
import com.qa.imssobol.utils.Connector;
import com.qa.imssobol.utils.Utils;

public class Ims {
	protected Connector connector;
	public static final Logger LOGGER = Logger.getLogger(Ims.class);
	protected Connection connection;
	
	public Ims(String url) {
		try {
			connector = new Connector(url);
			connection = connector.getConnection();
		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		}
		
	}
	
	
	
	public void imsRunner() {
		try {
			CustomerController customerController = new CustomerController(new CustomerServices(new MysqlCustomerDao(connection)));
		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		}

	}
	
	
}
