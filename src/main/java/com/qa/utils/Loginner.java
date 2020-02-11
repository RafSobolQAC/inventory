package com.qa.utils;

import org.apache.log4j.Logger;

public class Loginner {
	public static final Logger LOGGER = Logger.getLogger(Loginner.class);
	private Login login;
	public Loginner(Login login) {
		this.login = login;
	}
	public void LogIn() {

		LOGGER.info("What is your password? ");

		String password = Utils.getInput();
		login.setPassword(password);
	}
}
