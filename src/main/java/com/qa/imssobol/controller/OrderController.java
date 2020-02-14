package com.qa.imssobol.controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.qa.imssobol.persistence.dao.MysqlItemDao;
import com.qa.imssobol.persistence.domain.Item;
import com.qa.imssobol.persistence.domain.Order;
import com.qa.imssobol.services.CrudServices;
import com.qa.imssobol.services.ItemServices;
import com.qa.imssobol.services.OrderServices;
import com.qa.imssobol.utils.Utils;

public class OrderController implements CrudController<Order> {
	public static final Logger LOGGER = Logger.getLogger(OrderController.class);
	private Connection connection;
	private CrudServices<Order> orderService;
	private ItemServices itemService;
	public OrderController(OrderServices orderService) {
		this.orderService = orderService;
		this.connection = orderService.getConnection();
	}

	public String getInput() {
		return Utils.getInput();
	}

	public Integer getIntInput() {
		return Utils.getIntInput(LOGGER);
	}

	public Map<Item, Integer> createItemHashMap() {
		HashMap<Item, Integer> itemQuants = new HashMap<>();
		if (itemService == null) itemService = new ItemServices(new MysqlItemDao(connection));
		Item itemToAdd;
		boolean breaker = true;
		while (breaker) {
			LOGGER.info("To continue, press any key. To quit, (Q)uit.");
			String input = getInput();
			if (input.toLowerCase().startsWith("q")) {
				breaker = false;
			}
			if (breaker) {
				LOGGER.info("Item ID: ");
				int itemId = getIntInput();
				itemToAdd = itemService.readById(itemId);

				LOGGER.info("How many of this item? ");
				int itemQuant = getIntInput();
				itemQuants.put(itemToAdd, itemQuant);
			}
		}
		return itemQuants;

	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter the customer's ID. ");
		int customerId = getIntInput();
		Map<Item, Integer> itemQuants = createItemHashMap();
		Order order = orderService.create(new Order(customerId, itemQuants));
		LOGGER.info("Order was created.");
		return order;

	}

	@Override
	public Order update() {
		LOGGER.info("Which order would you like to update? (ID)");
		int orderId = getIntInput();
		LOGGER.info("Which customer made the order? (ID)");
		int customerId = getIntInput();
		Map<Item, Integer> itemQuants = createItemHashMap();
		Order order = orderService.update(orderId, new Order(customerId, itemQuants));
		LOGGER.info("Order was updated.");
		return order;

	}

	@Override
	public boolean delete() {
		LOGGER.info("Which order would you like to delete? (ID)");
		int orderId = getIntInput();
		boolean wasRemoved = orderService.delete(orderId);
		LOGGER.info("Order " + orderId + " was removed.");
		return wasRemoved;
	}

	@Override
	public Order readById() {
		LOGGER.info("Which order ID to access? ");
		int id = getIntInput();
		Order order = orderService.readById(id);
		LOGGER.info(order.toString());
		return order;
	}

}
