package com.devsuperior.uri2602.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	/*
	 * Query -Utilizado para customizar a consulta
	 * nativeQuery - Para realizar a consulta raiz SQL
	 */
	@Query(nativeQuery = true, value = "SELECT name "
			+ "FROM customers "
			+ "WHERE UPPER(state)= UPPER(:state)") //Garantir para desconsiderar maisc e minsc
	List<CustomerMinProjection> search1(String state); //Retorna e cria uma strutura de acordo com a interface CustomerMinProjection
	
	
	/*
	 * Consulta JPQL pode ser retornado o DTO direto
	 */
	@Query("SELECT new com.devsuperior.uri2602.dto.CustomerMinDTO(obj.name) " //caminho do DTO
			+ "FROM Customer obj "
			+ "WHERE UPPER(obj.state)= UPPER(:state)") 
	List<CustomerMinDTO> search2(String state);
}
