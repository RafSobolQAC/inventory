package com.qa.imssobol.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.imssobol.persistence.domain.Item;
import com.qa.imssobol.services.CrudServices;
import com.qa.imssobol.utils.Utils;

public class ItemController implements CrudController<Item>{
	public static final Logger LOGGER = Logger.getLogger(ItemController.class);
	
	private CrudServices<Item> itemService;
	
	public ItemController(CrudServices<Item> itemService) {
		this.itemService = itemService;
	}
	public String getInput() {
		return Utils.getInput();
	}
	
	public int getIntInput() {
		return Utils.getIntInput(LOGGER);
	}
	
	public BigDecimal getBigDecimalInput() {
		return Utils.getBigDecimalInput(LOGGER);
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
		String name = getInput();
		LOGGER.info("What price should it have? ");
		BigDecimal price = getBigDecimalInput();
		Item item = itemService.create(new Item(name,price));
		LOGGER.info("Item created.");
		return item;
	}

	public Item update() {
		LOGGER.info("Which item to update? (ID)");
		int id = getIntInput();
		LOGGER.info("What's their new name? ");
		String newName = getInput();
		LOGGER.info("What's their new price? ");
		BigDecimal newPrice = getBigDecimalInput();
		
		Item item = itemService.update(id, new Item(newName,newPrice));
		LOGGER.info("Update complete!");
		return item;
	}

	public boolean delete() {
		LOGGER.info("Please enter the id of the item you would like to delete: ");
		int id = getIntInput();
		boolean wasDeleted = itemService.delete(id);
		return wasDeleted;

	}

	@Override
	public Item readById() {
		LOGGER.info("Which item ID to access? ");
		int id = getIntInput();
		Item item = itemService.readById(id);
		LOGGER.info(item.toString());
		return item;
	}
	
	
	
}
