package com.qa.imssobol.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.imssobol.persistence.dao.MysqlItemDao;
import com.qa.imssobol.persistence.domain.Item;
import com.qa.imssobol.persistence.domain.Order;
import com.qa.imssobol.services.ItemServices;
import com.qa.imssobol.services.OrderServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	private Map<Item,Integer> itemQuants;
	private Order order;
	
	@Mock
	private ItemServices itemService;
	
	@Mock
	private MysqlItemDao mockItemDao;
	
	@Mock
	private Connection connection;
	/**
	 *  The thing I want to fake functionality for
	 */
	@Mock
	private OrderServices orderService;
	
	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer controller
	 */
	@Spy
	@InjectMocks
	private OrderController orderController;

	@Before
	public void init() {
        MockitoAnnotations.initMocks(this);
        Item item = new Item("TestItem",BigDecimal.valueOf(5.0));
        itemQuants = new HashMap<>();
        itemQuants.put(item, 5);
        order = new Order(1,2,itemQuants);
	}
	@Test
	public void readAllTest() {
		OrderController orderController = new OrderController(orderService);
		List<Order> orders = new ArrayList<>();
		orders.add(order);
		Mockito.when(orderService.readAll()).thenReturn(orders);
		assertEquals(orders, orderController.readAll());
	}

	@Test
	public void createTest() {
		Mockito.doReturn(1).when(orderController).getIntInput();
		Mockito.doReturn(itemQuants).when(orderController).createItemHashMap();
		Mockito.when(orderService.create(Mockito.any(Order.class))).thenReturn(order);
		assertEquals(order, orderController.create());
	}

	@Test
	public void updateTest() {
		Mockito.doReturn(1).when(orderController).getIntInput();
		Mockito.doReturn(itemQuants).when(orderController).createItemHashMap();
		Mockito.doReturn(order).when(orderService).update(Mockito.anyInt(), Mockito.any(Order.class));
//		Mockito.when(orderService.update(Mockito.anyInt(),Mockito.any(Order.class))).thenReturn(order);
		assertEquals(order, orderController.update());
	}
	
	@Test
	public void readByIdTest() {
		Mockito.doReturn(1).when(orderController).getIntInput();
		Mockito.when(orderService.readById(Mockito.anyInt())).thenReturn(order);
		assertEquals(order,orderController.readById());
	}
	
	@Test
	public void deleteTest() {
		int id = 1;
		Mockito.doReturn(id).when(orderController).getIntInput();
		orderController.delete();
		Mockito.when(orderService.delete(id)).thenReturn(true);
		assertTrue(orderController.delete());
	}
	
	@Test
	public void createItemHashMapTest() {
		Item item = new Item(1,"TestItem",BigDecimal.valueOf(5.0));
		Mockito.doReturn(" ").doReturn("q").when(orderController).getInput();
		Mockito.doReturn(1).doReturn(2).when(orderController).getIntInput();
		assertNotNull(orderController.createItemHashMap());
		
	}

}
