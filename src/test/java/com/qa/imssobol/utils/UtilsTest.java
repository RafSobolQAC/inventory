//package com.qa.imssobol.utils;
//
//import org.apache.log4j.Logger;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(Utils.class)
//public class UtilsTest {
//
//   @Test
//   public void shouldReturnTheCountOfEmployeesUsingTheDomainClass() {
//      PowerMockito.mockStatic(Utils.class);
//      PowerMockito.when(Utils.getInput()).thenReturn("123");
//
//      PowerMockito.verifyStatic(Utils.class,Mockito.times(1));
//      Assert.assertEquals(123,Utils.getIntInput(Mockito.any(Logger.class)));
//   }
//}
