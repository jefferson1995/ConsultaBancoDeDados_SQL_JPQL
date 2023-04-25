package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

	@Query(nativeQuery = true, value = "SELECT empregados.cpf, empregados.enome, departamentos.dnome "
			+ "FROM empregados "
			+ "INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero "
			+ "WHERE empregados.cpf NOT IN( "
			+ "SELECT empregados.cpf FROM empregados "
			+ "	INNER JOIN trabalha ON trabalha.cpf_emp = empregados.cpf "
			+ ") "
			+ "ORDER BY empregados.cpf")
	List<EmpregadoDeptProjection> serarch1();
	
	//Segunda consulta
	@Query(nativeQuery = true, value = "SELECT empregados.cpf, empregados.enome, departamentos.dnome "
			+ "FROM empregados "
			+ "INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero "
			+ "LEFT JOIN trabalha ON trabalha.cpf_emp = empregados.cpf "
			+ "WHERE trabalha.cpf_emp IS NULL "
			+ "ORDER BY empregados.cpf")
	List<EmpregadoDeptProjection> serarch2();
	
	//JPQL                                                                                //Navega no atributo          
	@Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(obj.cpf, obj.enome, obj.departamento.dnome) "
			+ "FROM Empregado obj "
			+ "WHERE obj.cpf NOT IN( "
			+ "SELECT obj.cpf FROM obj "
			+ "	INNER JOIN obj.projetosOndeTrabalha " //atributo ManyToMany dentro da classe - identifica os projetos que trabalha
			+ ") "
			+ "ORDER BY obj.cpf")
	List<EmpregadoDeptDTO> serarch3();
}


