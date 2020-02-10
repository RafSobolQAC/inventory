package com.qa.utils;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Utils {
	public static void exceptionLogger(Exception e, Logger LOGGER) {
		LOGGER.warn(e.getMessage());
		LOGGER.warn(e.getCause());
		LOGGER.warn(e.getStackTrace());
	}
	public static String getInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

}
