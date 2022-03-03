package com.ecommercebd.order.application;

import com.ecommercebd.plan.domain.Plan;
import com.ecommercebd.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderRequest {

    @DecimalMin(value = "0.1")
    private BigDecimal subtotal;
    @NotNull  @Valid
    private User customer;
    @NotNull  @Valid
    private List<Plan> plans = new ArrayList<>();
}
