//package com.qa.imssobol.utils;
//
//import static org.junit.Assert.assertEquals;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//
//
//@RunWith(MockitoJUnitRunner.class)
//@PrepareForTest({Utils.class})
//public class UtilsTest {
//    // stream to record the output (System.out)
//    private ByteArrayOutputStream testOutput;
//
//    @Before
//    public void setUpOutputStream() {
//        testOutput = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(testOutput));
//    }
//
//    // input feed to Scanner (System.in)
//    private void setInput(String input) {
//        System.setIn(new ByteArrayInputStream(input.getBytes()));
//    }
//    
//    
//    @Test
//    public void test1() {
//        // set System.in
//        setInput("foo");
//
//        // run the program (empty arguments array)
//        Utils.getPassword();
//        
//        final String expected = "foo";
//        final String actual = testOutput.toString();
//
//        assertEquals(expected, actual);
//    }
//
//
//}
