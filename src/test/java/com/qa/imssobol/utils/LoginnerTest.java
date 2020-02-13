package com.qa.imssobol.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginnerTest {

	@Spy
	private Loginner mockLoginner;
	
	@Test
	public void loginnerTest() {
		Mockito.doReturn("Password").when(mockLoginner).setPassword();
		
		assertEquals("Password",mockLoginner.logIn());
	}
	
	
}
