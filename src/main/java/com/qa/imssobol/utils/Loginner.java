package com.qa.imssobol.utils;

import org.apache.log4j.Logger;

public class Loginner {
	public static final Logger LOGGER = Logger.getLogger(Loginner.class);

	private String password; 
	
	public String setPassword() {
		return Utils.getPassword();
	}

	public String logIn() {

		if (this.password == null) {
			LOGGER.info("What is your password? ");

			this.password = setPassword();
			return this.password;
		}
		return this.password;
	}
}
