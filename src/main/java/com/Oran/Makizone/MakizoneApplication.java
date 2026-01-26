package com.Oran.Makizone;

import org.hibernate.SessionBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MakizoneApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MakizoneApplication.class, args);
		
	}

}
