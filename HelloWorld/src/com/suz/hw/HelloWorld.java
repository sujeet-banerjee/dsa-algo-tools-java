package com.suz.hw;

import java.util.logging.Logger;

public class HelloWorld {

	Logger logger = Logger.getLogger(this.getClass().getName());
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		new HelloWorld().logger.info("Logging This: {}"+ "Hello World!");
	}
}
