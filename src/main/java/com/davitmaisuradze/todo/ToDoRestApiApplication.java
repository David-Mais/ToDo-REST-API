package com.davitmaisuradze.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.davitmaisuradze.todo.entity")
public class ToDoRestApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ToDoRestApiApplication.class, args);
	}
}
