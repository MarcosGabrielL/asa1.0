package com.softsaj.AsaSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.softsaj.AsaSpring.security"})
@ComponentScan({"com.softsaj.AsaSpring.exception"})
@ComponentScan({"com.softsaj.AsaSpring.models"})
@ComponentScan({"com.softsaj.AsaSpring.repositories"})
@ComponentScan({"com.softsaj.AsaSpring.resource"})
@ComponentScan({"com.softsaj.AsaSpring.services"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AsaSpringApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(AsaSpringApplication.class, args);
	}

}
