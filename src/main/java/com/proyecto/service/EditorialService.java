package com.proyecto.service;

import java.util.List;

import com.proyecto.entity.Editorial;

public interface EditorialService {
	
	public abstract Editorial registraActualizaEditorial(Editorial obj);
	public abstract List<Editorial> listaEditorial();
	public abstract void eliminaEditorial(int ideditorial);

	public Editorial buscarPorId(int ideditorial);
	
	public abstract List<Editorial> listaEditorialPorRazon(String razon);
	public abstract List<Editorial> listaDinamica(String razonsocial, String direccion, String ruc, int estado);
	public abstract List<Editorial> listaDinamicaMejorada(String razonsocial, String direccion, String ruc, int estado);

}
