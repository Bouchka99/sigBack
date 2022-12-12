package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



//@EnableMongoRepositories(basePackages="com.example.demo.repository")

//@ComponentScan("com.example.demo.repository")

@SpringBootApplication

public class BackEndSigApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndSigApplication.class, args);
	}

}
