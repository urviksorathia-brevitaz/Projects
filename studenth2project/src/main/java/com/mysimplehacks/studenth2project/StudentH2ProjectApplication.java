package com.mysimplehacks.studenth2project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
SpringBootApplication
Student CRUD REST API using Service layer DAO implementation using EntityManager DI (Jpa) for CRUD operations,
    using application.yml for configuration
Dependency : web, jpa, h2
 */

@SpringBootApplication
public class StudentH2ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentH2ProjectApplication.class, args);
    }

}
