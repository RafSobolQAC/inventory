package com.qa.imssobol.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Utils {
	public static void exceptionLogger(Exception e, Logger LOGGER) {
		LOGGER.error(e.getMessage());
		LOGGER.error(e.getCause());
		LOGGER.debug(e.getStackTrace());
	}
	
	public static String getInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	
	public static int getIntInput(Logger LOGGER) {
		boolean done = false;
		int intRet = 0;
		do {
			try {
				intRet = Integer.parseInt(getInput());
				if (intRet <= 0) throw new NumberFormatException();
				done = true;
			} catch (NumberFormatException e) {
				LOGGER.warn("Please type in an integer greater than 0!");
			}
		} while (!done);
		return intRet;	
	}
	
	
	public static BigDecimal getBigDecimalInput(Logger LOGGER) {
		boolean done = false;
		BigDecimal price = BigDecimal.valueOf(0.0);
		do {
			try {
				price = BigDecimal.valueOf(Double.valueOf(getInput()));
				if (price.compareTo(BigDecimal.valueOf(0))<=0) throw new NumberFormatException();
				done = true;
			} catch (NumberFormatException e) {
				LOGGER.warn("Please type in a decimal number greater than 0!");
			}
		} while (!done);
		return price.setScale(2,RoundingMode.HALF_UP);	

	}
}
