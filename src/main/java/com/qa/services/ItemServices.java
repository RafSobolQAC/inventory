package com.qa.services;

import java.util.List;

import com.qa.persistence.dao.Dao;
import com.qa.persistence.domain.Item;

public class ItemServices implements CrudServices<Item>{
	
	Dao<Item> itemDao;
	
	public ItemServices(Dao<Item> itemDao) {
		this.itemDao = itemDao;
	}
	
	public List<Item> readAll() {
		return itemDao.readAll();
	}

	public Item create(Item item) {
		return itemDao.create(item);
	}

	public Item update(int id, Item t) {
		return itemDao.update(id, t);
	}

	public boolean delete(int id) {
		return itemDao.delete(id);
	}

	@Override
	public Item readById(int id) {
		return itemDao.readById(id);
	}

}
