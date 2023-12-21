package com.proyecto.service;

import java.util.List;

import com.proyecto.entity.Autor;

public interface AutorService {
	
	 public List<Autor> listAll();
	 public Autor registrar(Autor a);
	 public Autor actualizar(Autor a);
	 public List<Autor> buscarNombreLike(String nombre);
	 public void eliminar(int id);
	 public Autor buscarPorId(int id);
	 public List<Autor> buscarAutores(String nombre, String apellido, String pais, String estado);
	 
}