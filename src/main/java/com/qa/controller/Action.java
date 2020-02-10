package com.qa.controller;

import org.apache.log4j.Logger;

import com.qa.utils.Utils;

public enum Action {

	CREATE("To save a new item into the database"), READ("To read an item from the database"),
	UPDATE("To change an item already in the database"), DELETE("To remove an item from the database"),
	RETURN("To return to domain selection");

	public static final Logger LOGGER = Logger.getLogger(Action.class);

	private String description;

	Action(String description) {
		this.description = description;
	}

	public String description() {
		return this.name() + ": " + this.description;
	}

	public static void printActions() {
		for (Action action : Action.values()) {
			LOGGER.info(action.description());
		}
	}

	public static Action getAction() {
		Action action;
		while (true) {
			try {
				action = Action.valueOf(Utils.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return action;
	}

}
