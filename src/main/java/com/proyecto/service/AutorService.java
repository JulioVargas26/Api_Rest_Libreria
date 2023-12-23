package com.proyecto.service;

import java.util.List;

import com.proyecto.entity.Autor;

public interface AutorService {
	

	 public Autor registraActualizaAutor(Autor a);
	 public List<Autor> listaAutor();
	 public void eliminarAutor(int id);
	 
	 public Autor buscarPorId(int id);
	 
	 public List<Autor> listaAutorPorNombre(String nombre);
	 public List<Autor> listaDinamica(String nombre, String apellido, int estado);
	 
}