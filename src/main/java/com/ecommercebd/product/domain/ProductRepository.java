package com.ecommercebd.product.domain;

import com.ecommercebd.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

	List<Product> findAll();
	Product save(Product entity);
	Optional<Product> findById(Long id);
	void delete(Product entity);
	
}
