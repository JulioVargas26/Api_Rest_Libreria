package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	public abstract List<Categoria> findByOrderByDescripcionAsc();
	
}