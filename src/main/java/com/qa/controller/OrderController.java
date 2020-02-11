package com.qa.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.qa.persistence.dao.MysqlItemDao;
import com.qa.persistence.dao.MysqlOrderDao;
import com.qa.persistence.domain.Item;
import com.qa.persistence.domain.Order;
import com.qa.utils.Utils;

public class OrderController implements CrudController<Order>{
	public static final Logger LOGGER = Logger.getLogger(MysqlOrderDao.class);

	@Override
	public void readAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		Map<Item,Integer> itemQuants = new HashMap<>(); 
		LOGGER.info("Which order would you like to update? (ID)");
		try {
			int orderId = Integer.parseInt(Utils.getInput());
		} catch (IllegalArgumentException e) {
			LOGGER.warn("Please type in an integer.");
		}
		boolean breaker = true;
		while(breaker) {
			LOGGER.info("Type in an item's ID, or type in (Q)uit to finish.");
			String input = Utils.getInput();
			if (input.toLowerCase().startsWith("Q")) {
				breaker = false;
				break;
			}

			int itemId = Utils.getIntInput(LOGGER);
			
//			BigDecimal itemPrice = new ItemController(ItemServices(MysqlItemDao()))
			
			LOGGER.info("How many of this item? ");
			int itemQuant = Utils.getIntInput(LOGGER);
			itemQuants.put(new Item(itemId,"",BigDecimal.valueOf(0.0)),itemQuant);
		}
		

		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
