package com.devsuperior.uri2611.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;

public interface MovieRepository extends JpaRepository<Movie, Long>{

	/*
	 * genreName - parâmetro da consulta
	 */
	@Query(nativeQuery = true, value = "SELECT movies.id, movies.name "
			+ "FROM movies "
			+ "INNER JOIN genres ON movies.id_genres = genres.id "
			+ "WHERE genres.description = :genreName")
	List<MovieMinProjection> search1(String genreName); 
	
	
	//Exemplo utilizando JPQL
	@Query("SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(obj.id, obj.name) " //Caminho do DTO e os parâmetros do construtor
			+ "FROM Movie obj "  // -> classe Movie
			+ "WHERE obj.genre.description = :genreName") 
			/*
			 * Acessa a classe Movie(obj) e acessa o atributo genre que tem o description
			 * Faz o inner join automático
			 */	
	List<MovieMinDTO> search2(String genreName); //Retorna os dados diretamente no DTO
}
