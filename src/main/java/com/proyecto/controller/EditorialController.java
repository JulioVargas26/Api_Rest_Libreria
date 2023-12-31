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

import com.proyecto.entity.Editorial;
import com.proyecto.service.EditorialService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/editorial")
public class EditorialController {

	@Autowired
	private EditorialService service;
	
	@GetMapping
	public List<Editorial> listaEditorial(){
		List<Editorial> lstSalida = service.listaEditorial();
		return lstSalida;
	}

	@GetMapping("/{razon}")
	public ResponseEntity<List<Editorial>> listaEditorialPorRazon(@PathVariable("razon") String razon){
		List<Editorial> listaEditorial = null;
		try {
			if(razon.equals("todos")) {
				listaEditorial = service.listaEditorialPorRazon("%");
			}else {
				listaEditorial = service.listaEditorialPorRazon("%" + razon + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(listaEditorial);
		
	}
	
	@GetMapping("/param")
	public List<Editorial> listaDinamica(@RequestParam(name = "razonsocial", required = false, defaultValue = "") String razonsocial,
										 @RequestParam(name = "direccion", required = false, defaultValue = "") String direccion,
										 @RequestParam(name = "ruc", required = false, defaultValue = "") String ruc,
										 @RequestParam(name = "estado", required = true, defaultValue = "1") int estado){
		List<Editorial> lista = service.listaDinamica("%"+razonsocial.toLowerCase() + "%", direccion, ruc, estado);
		
		return lista;
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> registraEditorial(@RequestBody Editorial obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			//Seteando valores por defecto
			obj.setFecharegistro(new Date());
			obj.setFechaactualizacion(new Date());
			obj.setEstado(AppSettings.ACTIVO);
				
			Editorial objSalida = service.registraActualizaEditorial(obj);
			
			if(objSalida == null) {
				salida.put("mensaje", "Error en el registro");
			}else {
				salida.put("mensaje", "Se registro el editorial con ID=>" + objSalida.getIdeditorial());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping
	public ResponseEntity<Map<String, Object>> actualizaEditorial(@RequestBody Editorial obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			//Seteando valores por defecto
			obj.setFechaactualizacion(new Date());
			Editorial objSalida = service.registraActualizaEditorial(obj);
			
			if(objSalida == null) {
				salida.put("mensaje", "Error en la actualización");
			}else {
				salida.put("mensaje", "Se actualizó el editorial con ID=>" + objSalida.getIdeditorial());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminaEditorial(@PathVariable("id")int ideditorial){
		Map<String, Object> salida = new HashMap<>();
		try {
			service.eliminaEditorial(ideditorial);
			salida.put("mensaje","Editorial eliminado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la eliminación");
		}
		return ResponseEntity.ok(salida);
	}
	

}
