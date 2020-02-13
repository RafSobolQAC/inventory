package com.qa.imssobol.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.imssobol.persistence.dao.MysqlItemDao;
import com.qa.imssobol.persistence.domain.Item;

@RunWith(MockitoJUnitRunner.class)
public class ItemServicesTest {
	private Item item; 
	private BigDecimal price = BigDecimal.valueOf(5.0);
	
	@Mock
	private MysqlItemDao itemDaoMock;
	
	@InjectMocks
	private ItemServices itemService;
	
	@Before
	public void init() {
		item = new Item("A House",price);
	}
	@Test
	public void readAllTest() {
		ArrayList<Item> items = new ArrayList<>();
		items.add(item);
		Mockito.when(itemDaoMock.readAll()).thenReturn(items);
		assertEquals(items,itemService.readAll());
	}
	
	@Test
	public void createTest() {
		Mockito.when(itemDaoMock.create(Mockito.any(Item.class))).thenReturn(item);
		assertEquals(item,itemService.create(item));
	}
	
	@Test
	public void updateTest() {
		Mockito.when(itemDaoMock.update(Mockito.anyInt(), Mockito.any(Item.class))).thenReturn(item);
		assertEquals(item,itemService.update(1, item));
	}
	
	@Test
	public void deleteTest() {
		Mockito.when(itemDaoMock.delete(Mockito.anyInt())).thenReturn(true);
		assertEquals(true,itemService.delete(1));
	}
	
	@Test
	public void readByIdTest() {
		Mockito.when(itemDaoMock.readById(Mockito.anyInt())).thenReturn(item);
		assertEquals(item,itemService.readById(1));
	}

}
