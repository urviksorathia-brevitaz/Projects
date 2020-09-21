package com.mysimplehacks.studentdataapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Student CRUD REST API using JpaRepository for CRUD operations, using application.yml for configuration
Dependency : web, jpa, apache derby
 */

@SpringBootApplication
public class StudentDataApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentDataApiApplication.class, args);
	}

}
