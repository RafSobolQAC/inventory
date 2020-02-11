package com.qa.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.persistence.dao.MysqlItemDao;
import com.qa.persistence.dao.MysqlOrderDao;
import com.qa.persistence.domain.Item;
import com.qa.persistence.domain.Order;
import com.qa.services.CrudServices;
import com.qa.services.ItemServices;
import com.qa.utils.Utils;

public class OrderController implements CrudController<Order>{
	public static final Logger LOGGER = Logger.getLogger(MysqlOrderDao.class);
	private Connection connection;
	private CrudServices<Order> orderService;
	
	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}

	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		return new ArrayList<Order>();
	}

	@Override
	public Order create() {
		// TODO Auto-generated method stub
		
		return new Order();
		
	}

	@Override
	public Order update() {
		HashMap<Item,Integer> itemQuants = new HashMap<>(); 
		Item itemToAdd;
		LOGGER.info("Which order would you like to update? (ID)");
		int orderId = Utils.getIntInput(LOGGER);
		LOGGER.info("Which customer made the order? (ID)");
		int customerId = Utils.getIntInput(LOGGER);
		boolean breaker = true;
		while(breaker) {
			LOGGER.info("Type in an item's ID, or type in (Q)uit to finish.");
			String input = Utils.getInput();
			if (input.toLowerCase().startsWith("Q")) {
				breaker = false;
				break;
			}

			int itemId = Utils.getIntInput(LOGGER);
			try {
				ItemServices itemService = new ItemServices(new MysqlItemDao(connection));
				itemToAdd = itemService.readById(itemId);
			} catch (SQLException e) {
				Utils.exceptionLogger(e, LOGGER);
				break;
			}
			
			
			LOGGER.info("How many of this item? ");
			int itemQuant = Utils.getIntInput(LOGGER);
			itemQuants.put(itemToAdd,itemQuant);
		}
		
		return new Order(orderId,customerId,itemQuants);
		
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Order readById() {
		LOGGER.info("Which order ID to access? ");
		int id = Utils.getIntInput(LOGGER);
		Order order = orderService.readById(id);
		LOGGER.info(order.toString());
		return order;
	}

}
