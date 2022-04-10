package com.ecommercebd.order.application;

import com.ecommercebd.plan.domain.Plan;
import com.ecommercebd.user.application.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private BigDecimal subtotal;
    private UserResponse customer;
    private List<Plan> plans = new ArrayList<>();
}
