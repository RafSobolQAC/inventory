package com.qa.imssobol.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ActionTest {
	@Test
	public void createTest() {
		Action action = Action.CREATE;
		assertTrue(action.getDescription().toLowerCase().contains("create"));
	}
	

	@Test
	public void readTest() {
		Action action = Action.READALL;
		assertTrue(action.getDescription().toLowerCase().contains("readall"));
	}
	@Test
	public void updateTest() {
		Action action = Action.UPDATE;
		assertTrue(action.getDescription().toLowerCase().contains("update"));
	}
	@Test
	public void deleteTest() {
		Action action = Action.DELETE;
		assertTrue(action.getDescription().toLowerCase().contains("delete"));
	}
	@Test
	public void returnTest() {
		Action action = Action.RETURN;
		assertTrue(action.getDescription().toLowerCase().contains("return"));
	}
}
