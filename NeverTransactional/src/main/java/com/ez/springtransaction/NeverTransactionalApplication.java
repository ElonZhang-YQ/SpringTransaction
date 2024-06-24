package com.ez.springtransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NeverTransactionalApplication {

	public static void main(String[] args) {

		SpringApplication.run(NeverTransactionalApplication.class, args);
	}

}
