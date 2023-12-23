package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

	@Query("select x from Autor x where x.nombre like ?1")
	public List<Autor> listaPorNombreLike(String nombre);

	@Query("SELECT e from Autor e where (e.nombre like?1) and"
			+ "(?2 = '' or e.apellido =?2) and "
			+ "(e.estado =?3)" )
	List<Autor> listaConsultaDinamica(String nombre, String apellido,  int estado);
}