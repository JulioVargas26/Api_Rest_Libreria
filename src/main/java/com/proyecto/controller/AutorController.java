package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entity.Autor;
import com.proyecto.service.AutorService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	private AutorService service;
	
	@GetMapping
	public List<Autor> listaAutor(){
		List<Autor> lstSalida = service.listaAutor();
		return lstSalida;
	}

	@GetMapping("/{nombre}")
	public ResponseEntity<List<Autor>> listaAutorPorNombre(@PathVariable("nombre") String nombre){
		List<Autor> listaAutor = null;
		try {
			if(nombre.equals("todos")) {
				listaAutor = service.listaAutorPorNombre("%");
			}else {
				listaAutor = service.listaAutorPorNombre("%" + nombre + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(listaAutor);
		
	}
	
	@GetMapping("/param")
	public List<Autor> listaDinamica(@RequestParam(name = "nom", required = false, defaultValue = "") String nombre,
										 @RequestParam(name = "ape", required = false, defaultValue = "") String apellido,
										 @RequestParam(name = "estado", required = true, defaultValue = "1") int estado){
		List<Autor> lista = service.listaDinamica("%"+nombre.toLowerCase() + "%", apellido, estado);
		
		return lista;
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> registraAutor(@RequestBody Autor obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			//Seteando valores por defecto
			obj.setFecharegistro(new Date());
			obj.setFechaactualizacion(new Date());
			obj.setEstado(AppSettings.ACTIVO);
				
			Autor objSalida = service.registraActualizaAutor(obj);
			
			if(objSalida == null) {
				salida.put("mensaje", "Error en el registro");
			}else {
				salida.put("mensaje", "Se registro el Autor con ID=>" + objSalida.getIdautor());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping
	public ResponseEntity<Map<String, Object>> actualizaAutor(@RequestBody Autor obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			//Seteando valores por defecto
			obj.setFechaactualizacion(new Date());
			Autor objSalida = service.registraActualizaAutor(obj);
			
			if(objSalida == null) {
				salida.put("mensaje", "Error en la actualización");
			}else {
				salida.put("mensaje", "Se actualizó el Autor con ID=>" + objSalida.getIdautor());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminaAutor(@PathVariable("id")int idAutor){
		Map<String, Object> salida = new HashMap<>();
		try {
			service.eliminarAutor(idAutor);
			salida.put("mensaje","Autor eliminado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la eliminación");
		}
		return ResponseEntity.ok(salida);
	}
	

}
