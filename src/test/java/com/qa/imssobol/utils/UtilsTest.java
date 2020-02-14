package com.qa.imssobol.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {
private static final Logger LOGGER = Logger.getLogger(UtilsTest.class);
   @Test
   public void utilsInputTest() throws Exception {
	   String input = "123";
	   InputStream in = new ByteArrayInputStream(input.getBytes());
	   System.setIn(in);
//	   PowerMockito.doReturn("123").when(utils).getInput();
      
      
      //      PowerMockito.when(utils.getInput()).thenReturn("123");
//      PowerMockito.verifyStatic(Utils.class,Mockito.times(1));
      Assert.assertEquals("123",Utils.getInput());
	   input = "***\n123";
	   in = new ByteArrayInputStream(input.getBytes());
	   System.setIn(in);
	   Assert.assertEquals("123", Utils.getInput());
   }
}
