package com.qa.services;

import java.util.List;

import com.qa.persistence.dao.Dao;
import com.qa.persistence.domain.Item;

public class ItemServices {
	
	Dao<Item> itemDao;
	
	public ItemServices(Dao<Item> itemDao) {
		this.itemDao = itemDao;
	}
	
	public List<Item> readAll() {
		return itemDao.readAll();
	}

	public void create(Item item) {
		itemDao.create(item);
	}

	public void update(int id, Item t) {
		
	}

	public void delete(Item t) {
		
	}

}
