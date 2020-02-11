package com.qa.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public String getInput() {
		return Utils.getInput();
	}
	
	public Integer getIntInput() {
		return Utils.getIntInput(LOGGER);
	}
	
	public HashMap<Item, Integer> createItemHashMap() {
		HashMap<Item,Integer> itemQuants = new HashMap<>(); 
		Item itemToAdd;

		boolean breaker = true;
		while(breaker) {
			LOGGER.info("Which item ID to add? ");
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
		return itemQuants;

	}
	
	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		return new ArrayList<Order>();
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter the customer's ID. ");
		int customerId = getIntInput();
		boolean breaker = false;
		LOGGER.info("At any point, type in (Q) to Quit.");
		while (!breaker) {
			LOGGER.info("What item ID would you like to add?");
		}
		
		return new Order();
		
	}

	@Override
	public Order update() {
		LOGGER.info("Which order would you like to update? (ID)");
		int orderId = Utils.getIntInput(LOGGER);
		LOGGER.info("Which customer made the order? (ID)");
		int customerId = Utils.getIntInput(LOGGER);
		LOGGER.info("At any point, type in (Q)uit to finish.");
		Map<Item,Integer> itemQuants = createItemHashMap();
		return new Order(orderId, customerId,itemQuants);
		
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
