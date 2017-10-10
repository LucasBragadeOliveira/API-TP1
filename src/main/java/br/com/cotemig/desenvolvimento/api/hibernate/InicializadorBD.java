package br.com.cotemig.desenvolvimento.api.hibernate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"br.com.cotemig.desenvolvimento.api.hibernate"})
public class InicializadorBD {
	
	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
		// Altera a propriedade para sempre criar a base
		System.setProperty("spring.jpa.hibernate.ddl-auto", "create");
		System.setProperty("spring.jpa.properties.hibernate.format_sql", "true");

		ConfigurableApplicationContext c = SpringApplication.run(InicializadorBD.class, args);
		PovoadorBD povoador = c.getBean(PovoadorBD.class);
		povoador.povoar();
		System.out.println("concluído!");
		c.close();
	}
}
