package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Libro;
import com.proyecto.repository.LibroRepository;

@Service
public class LibroServiceImpl implements LibroService{

	@Autowired
	private LibroRepository repo;
	
	@Override
	public Libro registraActualizaLibro(Libro obj) {
		return repo.save(obj);
	}

	@Override
	public List<Libro> listaLibro() {
		return repo.findAll();
	}

	@Override
	public void eliminaLibro(int idlibro) {
		Libro e = repo.findById(idlibro).orElse(null);
		if (e != null) {
			repo.delete(e);
		}
	}

	@Override
	public Libro buscarPorId(int idlibro) {
		return repo.findById(idlibro).orElse(null);
	}

}
