package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Autor;
import com.proyecto.repository.AutorRepository;

@Service 
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorRepository repo;

	@Override
	public Autor registraActualizaAutor(Autor a) {
		return repo.save(a);
	}
	
	@Override
	public List<Autor> listaAutor() {
		return repo.findAll();
	}

	@Override
	public void eliminarAutor(int id) {
		Autor a = repo.findById(id).orElse(null);
		if (a != null) {
			repo.delete(a);
		}
	}

	@Override
	public Autor buscarPorId(int id) {
		return repo.findById(id).orElse(null);
	}
	
	@Override
	public List<Autor> listaAutorPorNombre(String nombre) {
		return repo.listaPorNombreLike(nombre);
	}

	@Override
	public List<Autor> listaDinamica(String nombre, String apellido, int estado) {
		return repo.listaConsultaDinamica(nombre, apellido, estado);
	}

}