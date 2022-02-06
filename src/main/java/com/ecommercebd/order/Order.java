package com.ecommercebd.order;

import com.ecommercebd.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OffsetDateTime startDate;
    private OffsetDateTime expirationDate;
    private BigDecimal subtotal;
    private String characteristic;
    private Long storage;
    @Positive
    private int limitOfConnections;
    @Positive
    private int limitOfUsers;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User customer;
}
