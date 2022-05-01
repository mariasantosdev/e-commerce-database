package com.ecommercebd.order.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewMyOrderRequest {
    @DecimalMin(value = "0.1")
    @NotNull(message = "Subtotal inválido")
    @JsonProperty
    private BigDecimal subtotal;
    @NotNull  @Valid
    private List<PlanId> plans = new ArrayList<>();
}
