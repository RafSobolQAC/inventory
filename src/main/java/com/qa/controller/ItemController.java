package com.qa.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Customer;
import com.qa.persistence.domain.Item;
import com.qa.services.CrudServices;
import com.qa.utils.Utils;

public class ItemController implements CrudController<Item>{
	public static final Logger LOGGER = Logger.getLogger(ItemController.class);
	
	private CrudServices<Item> itemService;
	
	public ItemController(CrudServices<Item> itemService) {
		this.itemService = itemService;
	}
	
	public List<Item> readAll() {
		List<Item> items = itemService.readAll();
		for(Item item: items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	public Item create() {
		LOGGER.info("Please enter the name of the item.");
		String name = Utils.getInput();
		LOGGER.info("What price should it have? ");
		BigDecimal price = Utils.getBigDecimalInput(LOGGER);
		Item item = itemService.create(new Item(name,price));
		LOGGER.info("Item created.");
		return item;
	}

	public Item update() {
		LOGGER.info("Which item to update? (ID)");
		int id = Utils.getIntInput(LOGGER);
		LOGGER.info("What's their new name? ");
		String newName = Utils.getInput();
		LOGGER.info("What's their new price? ");
		BigDecimal newPrice = Utils.getBigDecimalInput(LOGGER);
		
		Item item = itemService.update(id, new Item(newName,newPrice));
		LOGGER.info("Update complete!");
		return item;
	}

	public boolean delete() {
		LOGGER.info("Please enter the id of the item you would like to delete: ");
		int id = Utils.getIntInput(LOGGER);
		boolean wasDeleted = itemService.delete(id);
		return wasDeleted;

	}

	@Override
	public Item readById() {
		LOGGER.info("Which item ID to access? ");
		int id = Utils.getIntInput(LOGGER);
		Item item = itemService.readById(id);
		LOGGER.info(item.toString());
		return item;
	}
	
	
	
}
