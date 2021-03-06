package com.qa.imssobol.persistence.domain;

import org.apache.log4j.Logger;

import com.qa.imssobol.utils.Utils;

public enum Domain {

	CUSTOMER("Information about customers"),
	ITEM("Individual Items"),
	ORDER("Purchases of items"),
	STOP("To close the application");
	
	public static final Logger LOGGER = Logger.getLogger(Domain.class);

	private String description;
	
	private Domain(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.name() + ": " +this.description;
	}
	/**
	 * Output all domains from Domain.
	 */
	public static void printDomains() {
		for (Domain domain : Domain.values()) {
			LOGGER.info(domain.getDescription());
		}
	}
	/**
	 * Get a specific domain from input.
	 * @return one of the domains
	 */
	public static Domain getDomain() {
		Domain domain;
		while (true) {
			try {
				domain = Domain.valueOf(Utils.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return domain;
	}
	
}
