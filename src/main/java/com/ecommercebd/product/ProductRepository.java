package com.ecommercebd.product;

import com.ecommercebd.plan.domain.Plan;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

	List<Plan> findAll();
	Plan save(Plan entity);
	Optional<Plan> findById(Long id);
	void delete(Plan entity);
	
}
