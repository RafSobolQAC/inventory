package com.qa.utils;

public class Login {
	private static String password;
	
	public static void setPassword(String password) {
		Login.password = password;
	}
	
	public static String getPassword() {
		return password;
	}
}
