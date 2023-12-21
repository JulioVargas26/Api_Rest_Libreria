package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
	
	@Query("select x from Autor x where x.nombre like ?1")
	public List<Autor> listaPorNombreLike(String nombre);

	@Query("SELECT a FROM Autor a " + "WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nom, '%')) "
			+ "AND LOWER(a.apellido) LIKE LOWER(CONCAT('%', :ape, '%')) "
			+ "AND CAST(a.pais.idpais AS string) LIKE :pais " + "AND CAST(a.estado AS string) LIKE :estado")
	List<Autor> buscarAutor(@Param("nom") String nombre, @Param("ape") String apellido,
			@Param("pais") String pais, @Param("estado") String estado);
}