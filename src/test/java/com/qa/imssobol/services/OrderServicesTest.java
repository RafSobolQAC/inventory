package com.qa.imssobol.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.imssobol.persistence.dao.MysqlOrderDao;
import com.qa.imssobol.persistence.domain.Item;
import com.qa.imssobol.persistence.domain.Order;

@RunWith(MockitoJUnitRunner.class)
public class OrderServicesTest {
	private Order order; 
	
	@Mock
	private MysqlOrderDao orderDaoMock;
	
	@Mock
	private Connection mockConn;
	
	@Spy
	@InjectMocks
	private OrderServices orderService;
	
	@Before
	public void init() {
		Map<Item,Integer> itemQuants = new HashMap<>();
		
		order = new Order(1,itemQuants);
	}
	@Test
	public void readAllTest() {
		ArrayList<Order> orders = new ArrayList<>();
		orders.add(order);
		Mockito.when(orderDaoMock.readAll()).thenReturn(orders);
		assertEquals(orders,orderService.readAll());
	}
	
	@Test
	public void getConnectionTest() {
		Mockito.when(orderDaoMock.getConnection()).thenReturn(mockConn);
		OrderServices testOS = new OrderServices(orderDaoMock);
		assertEquals(mockConn,testOS.getConnection());
	}
	
	@Test
	public void createTest() {
		Mockito.when(orderDaoMock.create(Mockito.any(Order.class))).thenReturn(order);
		assertEquals(order,orderService.create(order));
	}
	
	@Test
	public void updateTest() {
		Mockito.when(orderDaoMock.update(Mockito.anyInt(), Mockito.any(Order.class))).thenReturn(order);
		assertEquals(order,orderService.update(1, order));
	}
	
	@Test
	public void deleteTest() {
		Mockito.when(orderDaoMock.delete(Mockito.anyInt())).thenReturn(true);
		assertEquals(true,orderService.delete(1));
	}
	
	@Test
	public void readByIdTest() {
		Mockito.when(orderDaoMock.readById(Mockito.anyInt())).thenReturn(order);
		assertEquals(order,orderService.readById(1));
	}

}
