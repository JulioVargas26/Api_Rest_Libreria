package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Editorial;
import com.proyecto.repository.EditorialRepository;

@Service
public class EditorialServiceImpl implements EditorialService {

	@Autowired
	private EditorialRepository repositorio;

	@Override
	public Editorial insertaActualizaEditorial(Editorial obj) {
		return repositorio.save(obj);
	}

	@Override
	public List<Editorial> listaEditorial() {
		return repositorio.findAll();
	}

	@Override
	public void eliminaEditorial(int ideditorial) {
		repositorio.deleteById(ideditorial);
	}

	@Override
	public List<Editorial> listaEditorialPorRazon(String razon) {
		return repositorio.listaPorRazSocLike(razon);
	}

	@Override
	public List<Editorial> listaDinamica(String razonsocial, String direccion, String ruc, int estado) {
		return repositorio.listaConsultaDinamica(razonsocial, direccion, ruc, estado);
	}

	@Override
	public List<Editorial> listaDinamicaMejorada(String razonsocial, String direccion, String ruc, int estado) {
		// TODO Auto-generated method stub
		return repositorio.listaConsultaDinamicaMejorada(razonsocial, direccion, ruc, estado);
	}
	
}
