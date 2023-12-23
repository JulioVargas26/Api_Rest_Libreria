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

import com.proyecto.entity.Libro;
import com.proyecto.service.LibroService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/libro")
public class LibroController {

	@Autowired
	private LibroService service;
	
	@GetMapping("/lista")
	public List<Libro> listaLibro(){
		List<Libro> lstSalida = service.listaLibro();
		return lstSalida;
	}

	/*@GetMapping("/razonsocial/{razon}")
	public ResponseEntity<List<Libro>> listaLibroPorRazon(@PathVariable("razon") String razon){
		List<Libro> listaLibro = null;
		try {
			if(razon.equals("todos")) {
				listaLibro = service.listaLibroPorRazon("%");
			}else {
				listaLibro = service.listaLibroPorRazon("%" + razon + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(listaLibro);
		
	}
	
	@GetMapping("/parametros")
	public List<Libro> listaDinamica(@RequestParam(name = "razonsocial", required = false, defaultValue = "") String razonsocial,
										 @RequestParam(name = "direccion", required = false, defaultValue = "") String direccion,
										 @RequestParam(name = "ruc", required = false, defaultValue = "") String ruc,
										 @RequestParam(name = "estado", required = true, defaultValue = "1") int estado){
		List<Libro> lista = service.listaDinamica("%"+razonsocial.toLowerCase() + "%", direccion, ruc, estado);
		
		return lista;
	}
	*/
	@PostMapping("/registrar")
	public ResponseEntity<Map<String, Object>> registraLibro(@RequestBody Libro obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			//Seteando valores por defecto
			obj.setFecharegistro(new Date());
			obj.setFechaactualizacion(new Date());
			obj.setEstado(AppSettings.ACTIVO);
				
			Libro objSalida = service.registraActualizaLibro(obj);
			
			if(objSalida == null) {
				salida.put("mensaje", "Error en el registro");
			}else {
				salida.put("mensaje", "Se registro el Libro con ID=>" + objSalida.getIdlibro());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Map<String, Object>> actualizaLibro(@RequestBody Libro obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			//Seteando valores por defecto
			obj.setFechaactualizacion(new Date());
			Libro objSalida = service.registraActualizaLibro(obj);
			
			if(objSalida == null) {
				salida.put("mensaje", "Error en la actualización");
			}else {
				salida.put("mensaje", "Se actualizó el Libro con ID=>" + objSalida.getIdlibro());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Object>> eliminaLibro(@PathVariable("id")int idLibro){
		Map<String, Object> salida = new HashMap<>();
		try {
			service.eliminaLibro(idLibro);
			salida.put("mensaje","Libro eliminado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la eliminación");
		}
		return ResponseEntity.ok(salida);
	}
	

}
