package com.ecommercebd.plan.application;

import com.ecommercebd.exception.NotFoundException;
import com.ecommercebd.mapper.Mapper;
import com.ecommercebd.plan.domain.Plan;
import com.ecommercebd.plan.domain.PlanRepository;
import com.ecommercebd.product.domain.Product;
import com.ecommercebd.product.domain.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/plans")
class PlanController {

    private final PlanRepository planRepository;
    private final Mapper mapper;
    private final ProductRepository productRepository;

    @GetMapping
    List<PlanResponse> findAll() {
        return planRepository.findAll()
                .stream()
                .map(p -> mapper.map(p, PlanResponse.class)).toList();
    }

    @GetMapping("{planId}")
    PlanResponse findById(@PathVariable Long planId) {
        return planRepository.findById(planId)
                .map(p -> mapper.map(p, PlanResponse.class))
                .orElseThrow(() -> new NotFoundException("Plano não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
        //TODO alterar esse método create para retornar PlanResponse
    //TODO Ta dando 500 ao cadastrar duas vezes o mesmo registro - tratar
    PlanResponse create(@RequestBody @Valid NewPlanRequest newPlanRequest) {
        Plan plan = mapper.map(newPlanRequest, Plan.class);

        Product product = plan.getProduct();
        Long productId = product.getId();

        product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(String.format("Produto do plano %s não encontrado", productId)));

        plan.setProduct(product);
        planRepository.save(plan);
        return this.mapper.map(plan, PlanResponse.class);
    }

    @PutMapping("{planId}")
    PlanResponse update(@PathVariable Long planId,
                        @RequestBody @Valid NewPlanRequest newPlanRequest) {
        final Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new NotFoundException("Plano não encontrado."));

        this.mapper.map(newPlanRequest, plan);
        this.planRepository.save(plan);

        return this.mapper.map(plan, PlanResponse.class);
    }

    @DeleteMapping("{planId}")
    void delete(@PathVariable Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new NotFoundException("Plano não encontrado."));

        planRepository.delete(plan);
    }
}