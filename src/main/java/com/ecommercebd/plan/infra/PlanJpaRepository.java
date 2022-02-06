package com.ecommercebd.plan.infra;

import com.ecommercebd.plan.domain.Plan;
import com.ecommercebd.plan.domain.PlanRepository;
import org.springframework.data.jpa.repository.JpaRepository;

interface PlanJpaRepository extends JpaRepository<Plan, Long>, PlanRepository {
	
}
