package com.ecommercebd.plan.domain;

import com.ecommercebd.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    @Column(unique=true)
    @Size(min =1, max=200)
    private String name;
    @Size(min = 1, max=500)
    private String description;
    @Size(min = 1, max=500)
    private String characteristic;
    private Long storage;
    @Positive
    private int limitOfConnections;
    @Positive
    private int limitOfUsers;
    @ManyToOne
    @JoinColumn(name = "product_id",
            foreignKey = @ForeignKey(name = "fk_plan_product_id"))
    private Product product;

    public Plan(BigDecimal price, String name, String description, String characteristic, Long storage,
                int limitOfConnections, int limitOfUsers) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.characteristic = characteristic;
        this.storage = storage;
        this.limitOfConnections = limitOfConnections;
        this.limitOfUsers = limitOfUsers;
    }
}
