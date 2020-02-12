package com.qa.imssobol.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	public static final Logger LOGGER = Logger.getLogger(ItemTest.class);
	
	private Item item;
	private Item other;
	
	@Before
	public void init() {
		item = new Item();
		other = new Item();
	}
	
	@Test
	public void nameTest() {
		item.setName("Item");
		assertEquals("Item", item.getName());
	}
	
	@Test
	public void idTest() {
		item.setId(1);
		assertEquals(1,item.getId());
	}
	
	@Test
	public void priceTest() {
		item.setPrice(BigDecimal.valueOf(5));
		assertEquals(BigDecimal.valueOf(5),item.getPrice());
	}
	
	@Test
	public void constructorNamePriceTest() {
		BigDecimal price = BigDecimal.valueOf(5);
		item = new Item("Item",price);
		assertEquals("Item",item.getName());
		assertEquals(price,item.getPrice());
	}
	
	@Test
	public void constructorIdNamePriceTest() {
		BigDecimal price = BigDecimal.valueOf(5);
		item = new Item(1,"Item",price);
		assertEquals(1,item.getId());
		assertEquals("Item",item.getName());
		assertEquals(price,item.getPrice());
	}
	
	@Test
	public void toStringTest() {
		BigDecimal price = BigDecimal.valueOf(5);
		item = new Item(1,"Item",price);
		assertEquals("1 Item 5", item.toString());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(item.hashCode(),other.hashCode());
	}
	
	@Test
	public void equalTestSame() {
		assertTrue(item.equals(item));
	}
	@Test
	public void equalTestNotSame() {
		other.setId(100);
		assertFalse(item.equals(other));
		assertFalse(item.equals(5));
	}
	@Test
	public void equalTestOtherNull() {
		other = null;
		assertFalse(item.equals(other));
	}
	
	@Test
	public void equalTestNullName() {
		item.setName(null);
		other.setName("NotNull");
		assertFalse(item.equals(other));
		assertFalse(other.equals(item));
		other.setName(null);
		assertTrue(item.equals(other));
		
	}
	
	@Test
	public void equalTestNullPrice() {
		item.setPrice(null);
		other.setPrice(BigDecimal.valueOf(5));
		assertFalse(item.equals(other));
		assertFalse(other.equals(item));
	}
	
	@Test
	public void equalTestNotNullAll() {
		item = new Item("A",BigDecimal.valueOf(5));
		other = new Item("A",BigDecimal.valueOf(5));
		assertTrue(item.equals(other));
	}
	
	
}
