package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

	
}
