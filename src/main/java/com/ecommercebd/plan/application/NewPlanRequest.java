package com.ecommercebd.plan.application;

import com.ecommercebd.plan.domain.Plan;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.math.BigDecimal;

@Getter
@Setter
class NewPlanRequest {
    @NotNull(message = "Por favor insira um preço")
    @JsonProperty
    private BigDecimal price;
    @NotBlank(message = "Por favor insira um nome")
    @JsonProperty
    private String name;
    @NotBlank(message = "Por favor insira uma descrição")
    @JsonProperty
    private String description;
    @NotBlank(message = "Por favor insira as caracteristicas do produto")
    @JsonProperty
    private String characteristic;
    @NotNull(message = "Por favor insira o tamanho do armazenamento desse banco de dados")
    @JsonProperty
    private Long storage;
    @NotNull(message = "Por favor insira a quantidade limite de conexões")
    @JsonProperty
    @Positive(message = "Por favor insira um número de conexões válido")
    private int limitOfConnections;
    @NotNull(message = "Por favor insira a quantidade limites de usuários")
    @JsonProperty
    @Positive(message = "Por favor insira um número de limite de usuários válido")
    private int limitOfUsers;
    @NotNull
    @Valid
    private ProductId product;

    Plan toEntity() {
        return new Plan(price, name, description, characteristic, storage, limitOfConnections, limitOfUsers);
    }
}
