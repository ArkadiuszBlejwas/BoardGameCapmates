package com.capgemini.jstk.springAplication.Aplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.capgemini.jstk.springAplication")
public class SpringAplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAplication.class, args);
	}
}
