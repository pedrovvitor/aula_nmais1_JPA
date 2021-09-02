package com.example.demo.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> find(PageRequest pageRequest) {
		/* 
		 * Busca Paginada, porém, sem o método findProductCategories,
		 *  o JPA teria de fazer uma select para cada produto da página
		 */
		Page<Product> page = repository.findAll(pageRequest);

		/*
		 * O método abaixo força o JPA a buscar todos os produtos com suas respectivas
		 * categorais e instanciá-los em memória. Quando o DTO for buscar as categorias,
		 * elas já estarão em memória, evitando novas consultas em banco
		 */
		repository.findProductCategories(page.stream().collect(Collectors.toList()));
		return page.map(x -> new ProductDTO(x));
	}
}
