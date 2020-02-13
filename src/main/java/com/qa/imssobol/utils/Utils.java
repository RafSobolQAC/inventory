package com.qa.imssobol.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Utils {

	public static final Logger LOGGER = Logger.getLogger(Utils.class);

	private Utils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Returns standard messages in case of an exception.
	 * 
	 * @param e      exception
	 * @param LOGGER logger of class in which this method is ran
	 */
	public static void exceptionLogger(Exception e, Logger LOGGER) {
		LOGGER.error("Error in Utils.exceptionLogger: ");
		LOGGER.error(e.getMessage());
		LOGGER.error(e.getCause());
		LOGGER.debug(e.getStackTrace());
	}

	public static String getPassword() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	/**
	 * Takes in user input of a string and returns it. Only accepts alphanumerics
	 * and .
	 * 
	 * @return string, alphanumerics and .
	 */
	public static String getInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String string = scanner.nextLine();
			if (string.matches("[A-Za-z0-9. ]+")) {
				return string;
			} else {
				LOGGER.info("Please only use alphanumerics, spaces, and full stops.");
			}
		}
	}

	/**
	 * Takes in user input of an integer and returns it.
	 * 
	 * @param LOGGER logger of class in which this method is ran
	 * @return an integer
	 */
	public static int getIntInput(Logger LOGGER) {
		LOGGER.debug("Your number: ");
		boolean done = false;
		int intRet = 0;
		do {
			try {
				intRet = Integer.parseInt(getInput());
				if (intRet <= 0)
					throw new NumberFormatException();
				done = true;
			} catch (NumberFormatException e) {
				LOGGER.warn("Please type in an integer greater than 0!");
			}
		} while (!done);
		return intRet;
	}

	/**
	 * Takes in user input of a decimal number and returns it, scaled up/down to 2
	 * decimal points.
	 * 
	 * @param LOGGER logger of class in which this method is ran
	 * @return a decimal BigDecimal
	 */
	public static BigDecimal getBigDecimalInput(Logger LOGGER) {
		boolean done = false;
		BigDecimal price = BigDecimal.valueOf(0.0);
		do {
			try {
				price = BigDecimal.valueOf(Double.valueOf(getInput()));
				if (price.compareTo(BigDecimal.valueOf(0)) <= 0)
					throw new NumberFormatException();
				done = true;
			} catch (NumberFormatException e) {
				LOGGER.warn("Please type in a decimal number greater than 0!");
			}
		} while (!done);
		return price.setScale(2, RoundingMode.HALF_UP);

	}
}
