package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

	@Query("SELECT e FROM Libro e WHERE " +
	        "(UPPER(e.titulo) LIKE UPPER(COALESCE(?1, '')))")
	public List<Libro> listaPorTituloLike(String titulo);
	
	
}
