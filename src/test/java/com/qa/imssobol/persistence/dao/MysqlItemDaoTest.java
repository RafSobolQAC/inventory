package com.qa.imssobol.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.qa.imssobol.persistence.domain.Item;

public class MysqlItemDaoTest {
	public static final Logger LOGGER = Logger.getLogger(MysqlItemDaoTest.class);

	private Item item;
	private Item other;
	private static Connection conn;
	private final BigDecimal price = BigDecimal.valueOf(5);
	
	@Spy
	@InjectMocks
	private MysqlItemDao itemDao;

	
	@BeforeClass
	public static void login() throws SQLException {

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims", "root", "root");
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
	}
	
	@Before
	public void setUpItem() {
		item = new Item();
		other = new Item();
		itemDao = new MysqlItemDao(conn);
	}

	
	@Test
	public void itemDaoAddTest() {
		item.setName("One!");
		item.setPrice(price);
		other = new Item("One!",price);
		itemDao.create(item);
		assertEquals(other.getName(), itemDao.readLatest().getName());

	}
	
	@Test
	public void itemDaoReadByIdTest() {
		itemDao.create(item);
		item = itemDao.readLatest();
		assertEquals(item,itemDao.readById(item.getId()));
	}

	@Test
	public void itemDaoDeleteTest() {
		itemDao.create(item);
		assertTrue(itemDao.delete(itemDao.readLatest().getId()));
	}
	
	@Test
	public void itemDaoUpdateTest() {
		itemDao.create(item);
		item.setName("TestName");
		item.setPrice(price);
		itemDao.update(itemDao.readLatest().getId(), item);
		assertEquals(item.getName(), itemDao.readLatest().getName());
		assertEquals(0,item.getPrice().compareTo(itemDao.readLatest().getPrice()));
	}
	
	@Test
	public void itemDaoReadAllTest() {
		item.setName("TestName");
		item.setPrice(price);
		itemDao.create(item);
		List<Item> items = itemDao.readAll();
		assertEquals(item.getName(),items.get(items.size()-1).getName());
		assertEquals(0,item.getPrice().compareTo(items.get(items.size()-1).getPrice()));
	}
	
}
