package com.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.apachecommons.CommonsLog;

@SpringBootApplication
@CommonsLog
public class ApiRestLibreriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestLibreriaApplication.class, args);
		log.info("¡Terminó de cargar spring ... Fuerza!");
	}

}
