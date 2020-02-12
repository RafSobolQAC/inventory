package com.qa.imssobol.utils;

import org.apache.log4j.Logger;

public class Loginner {
	public static final Logger LOGGER = Logger.getLogger(Loginner.class);
	private Login login;
	public Loginner(Login login) {
		this.login = login;
	}
	public void logIn() {

		LOGGER.info("What is your password? ");

		String password = Utils.getPassword();
		login.setPassword(password);
	}
}
