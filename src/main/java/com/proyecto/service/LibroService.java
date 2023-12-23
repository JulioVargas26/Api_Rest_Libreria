package com.proyecto.service;

import java.util.List;

import com.proyecto.entity.Libro;

public interface LibroService {
	
	public abstract Libro registraActualizaLibro(Libro obj);
	public abstract List<Libro> listaLibro();
	public abstract void eliminaLibro(int idlibro);

	public Libro buscarPorId(int idlibro);
	
}
