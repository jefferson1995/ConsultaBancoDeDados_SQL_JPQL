package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	//Exeuta no ínicio da aplicação
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//Busca no BD. - SQL RAIZ
		List<CustomerMinProjection> list = repository.search1("RS");
		List<CustomerMinDTO> result1 = list.stream().map(x -> new CustomerMinDTO(x))
				.collect(Collectors.toList());
		
		System.out.println("\n*** SQL RAIZ ");
		//Imprime na tela o que foi retornado na lista.
		for (CustomerMinDTO obj : result1) {
			System.out.println(obj);
		}
		System.out.println("\n\n");
		
		//Busca no BD - JPQL exemplo
		List<CustomerMinDTO> result2 = repository.search2("RS");
		
		System.out.println("\n*** Resultado: JPQL ");
		for(CustomerMinDTO obj : result2) {
			System.out.println(obj);
		}

	}
	
	
}
