package com.qa.imssobol.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	
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
		
	}

	@Test
	public void Test() {
		
	}

	
}
