package com.qa.imssobol;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qa.imssobol.controller.Action;
import com.qa.imssobol.controller.CrudController;
import com.qa.imssobol.controller.CustomerController;
import com.qa.imssobol.controller.ItemController;
import com.qa.imssobol.controller.OrderController;
import com.qa.imssobol.persistence.dao.MysqlCustomerDao;
import com.qa.imssobol.persistence.dao.MysqlItemDao;
import com.qa.imssobol.persistence.dao.MysqlOrderDao;
import com.qa.imssobol.persistence.domain.Domain;
import com.qa.imssobol.services.CustomerServices;
import com.qa.imssobol.services.ItemServices;
import com.qa.imssobol.services.OrderServices;
import com.qa.imssobol.utils.Connector;
import com.qa.imssobol.utils.Utils;

public class Ims {
	protected Connector connector;
	public static final Logger LOGGER = Logger.getLogger(Ims.class);
	protected Connection connection;

	public Ims(Connector connector) {
		if (this.connector == null)	this.connector = connector;
	}
//	public Ims(String url) {
//		try {
//			connector = makeConnector(url);
//			connection = connector.getConnection();
//		} catch (SQLException e) {
//			Utils.exceptionLogger(e, LOGGER);
//		}
//	}
	
	public Connector makeConnector(String url) throws SQLException {
		return new Connector(url);
	}

	public void imsRunner() throws SQLException {
		connector.setUpConnector();
		this.connection = connector.getConnection();
		boolean breaker = true;
		while (breaker) {
			LOGGER.info("Which entity would you like to use? ");
			Domain.printDomains();

			Domain domain = Domain.getDomain();
			if (domain.name().equals("STOP")) System.exit(0);
			LOGGER.info("What would you like to do with " + domain.name().toLowerCase() + ":");
			Action.printActions();
			Action action = Action.getAction();
			
			switch (domain) {
			case CUSTOMER:
				CustomerController customerController = new CustomerController(
						new CustomerServices(new MysqlCustomerDao(connection)));
				doAction(customerController, action);
				break;
			case ITEM:
				ItemController itemController = new ItemController(new ItemServices(new MysqlItemDao(connection)));
				doAction(itemController, action);
				break;
			case ORDER:
				OrderController orderController = new OrderController(new OrderServices(new MysqlOrderDao(connection)));
				doAction(orderController, action);
				break;
			case STOP:
				breaker = false;
				break;
			default:
				break;
			}
		}
	}

	public void doAction(CrudController crudController, Action action) {
		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case READALL:
			crudController.readAll();
			break;
		case READ:
			crudController.readById();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case RETURN:
			break;
		default:
			break;
		}
	}

}
