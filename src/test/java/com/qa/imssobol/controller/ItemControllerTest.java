package com.qa.imssobol.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.qa.imssobol.persistence.domain.Item;
import com.qa.imssobol.services.ItemServices;

public class ItemControllerTest {
	private BigDecimal price = BigDecimal.valueOf(5.0);
	
	/**
	 *  The thing I want to fake functionality for
	 */
	@Mock
	private ItemServices itemService;
	
	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer controller
	 */
	@Spy
	@InjectMocks
	private ItemController itemController;

	@Before
	public void init() {
        MockitoAnnotations.initMocks(this);
	}
	@Test
	public void getInputTest() {
		Mockito.doReturn("TestInput").when(itemController).getInput();
		assertEquals("TestInput",itemController.getInput());
	}
	@Test
	public void readAllTest() {
		ItemController itemController = new ItemController(itemService);
		List<Item> items = new ArrayList<>();
		items.add(new Item("A",price));
		Mockito.when(itemService.readAll()).thenReturn(items);
		assertEquals(items, itemController.readAll());
	}

	@Test
	public void createTest() {
		String name = "TestItem";
		Mockito.doReturn(name).when(itemController).getInput();
		Mockito.doReturn(price).when(itemController).getBigDecimalInput();
		Item item = new Item(name,price); 
		Mockito.when(itemService.create(item)).thenReturn(item);
		assertEquals(item, itemController.create());
	}

	@Test
	public void updateTest() {
		int id = 1;
		String name = "TestItem2";
		Mockito.doReturn(1).when(itemController).getIntInput();
		Mockito.doReturn("TestItem2").when(itemController).getInput();
		Mockito.doReturn(price).when(itemController).getBigDecimalInput();
		
		Item item = new Item(id,name,price);
		Mockito.when(itemService.update(Mockito.anyInt(), Mockito.any(Item.class))).thenReturn(item);
		assertEquals(item, itemController.update());
	}
	
	@Test
	public void readByIdTest() {
		Item item = new Item("Beer",price);
		Mockito.doReturn(1).when(itemController).getIntInput();
		Mockito.when(itemService.readById(Mockito.anyInt())).thenReturn(item);
		assertEquals(item,itemController.readById());
	}
	
	@Test
	public void deleteTest() {
		int id = 1;
		Mockito.doReturn(id).when(itemController).getIntInput();
		itemController.delete();
		Mockito.when(itemService.delete(id)).thenReturn(true);
		assertTrue(itemController.delete());
	}
	

}
