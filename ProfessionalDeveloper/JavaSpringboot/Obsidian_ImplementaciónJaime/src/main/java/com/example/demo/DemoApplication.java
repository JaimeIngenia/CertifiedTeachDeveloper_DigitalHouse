package com.example.demo;

import com.example.demo.dao.DB;
import com.example.demo.model.Dentist;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		DB.createTables();
		SpringApplication.run(DemoApplication.class, args);
	}

}
