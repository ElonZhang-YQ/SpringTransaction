package com.ez.springtransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MandatoryTransactionalApplication {

	public static void main(String[] args) {

		SpringApplication.run(MandatoryTransactionalApplication.class, args);
	}

}
