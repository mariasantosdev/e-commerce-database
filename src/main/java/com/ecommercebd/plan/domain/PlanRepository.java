package com.ecommercebd.plan.domain;

import java.util.List;
import java.util.Optional;

public interface PlanRepository {
	
	List<Plan> findAll();
	Plan save(Plan entity);
	Optional<Plan> findById(Long id);
	void delete(Plan entity);
	
}
