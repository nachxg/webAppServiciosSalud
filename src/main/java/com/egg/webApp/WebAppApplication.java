package com.egg.webApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebAppApplication {

	public static void main(String[] args) {
		System.out.println("Versión de Java: " + System.getProperty("java.version"));

		SpringApplication.run(WebAppApplication.class, args);
	}


}
