package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Editorial;
import com.proyecto.repository.EditorialRepository;

@Service
public class EditorialServiceImpl implements EditorialService {

	@Autowired
	private EditorialRepository repo;

	@Override
	public Editorial registraActualizaEditorial(Editorial obj) {
		return repo.save(obj);
	}

	@Override
	public List<Editorial> listaEditorial() {
		return repo.findAll();
	}

	@Override
	public void eliminaEditorial(int ideditorial) {
		Editorial e = repo.findById(ideditorial).orElse(null);
		if (e != null) {
			repo.delete(e);
		}
	}

	@Override
	public Editorial buscarPorId(int ideditorial) {
		return repo.findById(ideditorial).orElse(null);
	}
	
	@Override
	public List<Editorial> listaEditorialPorRazon(String razon) {
		return repo.listaPorRazSocLike(razon);
	}

	@Override
	public List<Editorial> listaDinamica(String razonsocial, String direccion, String ruc, int estado) {
		return repo.listaConsultaDinamica(razonsocial, direccion, ruc, estado);
	}

	@Override
	public List<Editorial> listaDinamicaMejorada(String razonsocial, String direccion, String ruc, int estado) {
		// TODO Auto-generated method stub
		return repo.listaConsultaDinamicaMejorada(razonsocial, direccion, ruc, estado);
	}
	
}
