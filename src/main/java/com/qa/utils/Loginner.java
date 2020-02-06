package com.qa.utils;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Loginner {
	public static final Logger LOGGER = Logger.getLogger(Loginner.class);
	public static void LogIn() {

		LOGGER.info("What is your password? ");
		Scanner scanner = new Scanner(System.in);
		String password = scanner.nextLine();

		Login.setPassword(password);
		scanner.close();
	}
}
