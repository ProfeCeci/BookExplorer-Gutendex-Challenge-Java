package com.cecilialopez.bookexplorer;

import com.cecilialopez.bookexplorer.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookexplorerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookexplorerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.ejecutaAplicacion();
	}
}
