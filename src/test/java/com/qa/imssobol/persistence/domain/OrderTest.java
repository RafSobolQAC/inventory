package com.qa.imssobol.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
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


@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

	@Mock
	private Map<Item,Integer> itemsQuants;
	
	@Spy
	@InjectMocks
	private Order orderMock;
	
	
	private Order order;
	private Order other;
	
	
	@Before
	public void init() {
		order = new Order();
		other = new Order();
	}
	
	
	@Test
	public void idTest() {
		order.setId(100);
		assertEquals(100,order.getId());
		other.setId(100);
		assertTrue(order.equals(other));
		other.setId(10);
		assertFalse(order.equals(other));
		
	}
	
	@Test
	public void priceTest() {
		BigDecimal price = BigDecimal.valueOf(5);
		order.setPrice(price);
		assertEquals(price,order.getPrice());
		other.setPrice(price);
		assertTrue(order.equals(other));
		other.setPrice(null);
		assertFalse(order.equals(other));
		assertFalse(other.equals(order));
	}

	@Test
	public void customerTest() {
		order.setCustomerId(100);
		assertEquals(100,order.getCustomerId());
		other.setCustomerId(100);
		assertTrue(order.equals(other));
		other.setCustomerId(10);
		assertFalse(order.equals(other));
	}

	@Test
	public void equalsTest() {
		assertTrue(order.equals(order));
		assertFalse(order.equals(null));
		assertFalse(order.equals(new Integer(5)));
		
	}

	@Test
	public void itemsTest() {
		Item item = new Item("Item",BigDecimal.valueOf(5));
		Map<Item,Integer> itemQuants = new HashMap<>();
		itemQuants.put(item, 2);
		Mockito.doReturn(itemQuants).when(orderMock).getItems();
		assertTrue(itemQuants.equals(orderMock.getItems()));
	}
	
	@Test
	public void itemsSetGetTest() {
		Item item = new Item();
		Map<Item,Integer> itemQuants = new HashMap<>();
		itemQuants.put(item,2);
		order.setItems(itemQuants);
		assertEquals(itemQuants,order.getItems());
	}
	
	@Test
	public void calcPriceTest() {
		Item item = new Item("A",BigDecimal.valueOf(10000));
		Map<Item,Integer> itemQuants = new HashMap<>();
		itemQuants.put(item, 2);
		order.setItems(itemQuants);
		assertEquals(0,BigDecimal.valueOf(18000).compareTo(order.calcPrice()));
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(order.hashCode(),order.hashCode());
		other.setId(5000);
		assertNotEquals(order.hashCode(), other.hashCode());
		
	}
	
	@Test
	public void itemsEqualityTest() {
		order.setItems(null);
		other.setItems(null);
		assertTrue(order.equals(other));
		other.setItems(new HashMap<Item,Integer>());
		assertFalse(order.equals(other));
		assertFalse(other.equals(order));
	}
	
	@Test
	public void constructorsTest() {
		order = new Order(1,2,new HashMap<Item,Integer>());
		other = new Order(2,new HashMap<Item,Integer>());
		other.setId(1);
		assertEquals(order,other);
	}
	
	@Test
	public void toStringTest() {
		order = new Order(1,1,new HashMap<Item,Integer>());
		assertEquals("Order [id=1, price=0, customerId=1, items={}]",order.toString());
	}
}
