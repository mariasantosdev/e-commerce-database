package com.ecommercebd.plan.application;

import com.ecommercebd.product.application.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class PlanResponse {
    private Long id;
    private BigDecimal price;
    private String name;
    private String description;
    private String characteristic;
    private Long storage;
    private int limitOfConnections;
    private int limitOfUsers;
    private ProductResponse product;
}
