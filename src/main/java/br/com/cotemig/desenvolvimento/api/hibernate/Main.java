package br.com.cotemig.desenvolvimento.api.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"br.com.cotemig.desenvolvimento.api.hibernate"})
public class Main {
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		System.out.println("pronto!");
	}
}
