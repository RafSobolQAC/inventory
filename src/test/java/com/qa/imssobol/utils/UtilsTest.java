package com.qa.imssobol.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;



@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
public class UtilsTest {
private static final Logger LOGGER = Logger.getLogger(UtilsTest.class);
   @Test
   public void shouldReturnTheCountOfEmployeesUsingTheDomainClass() throws Exception {
	   String input = "123";
	   InputStream in = new ByteArrayInputStream(input.getBytes());
	   System.setIn(in);
//	   PowerMockito.doReturn("123").when(utils).getInput();
      
      
      //      PowerMockito.when(utils.getInput()).thenReturn("123");
//      PowerMockito.verifyStatic(Utils.class,Mockito.times(1));
      Assert.assertEquals("123",Utils.getInput());
   }
}
