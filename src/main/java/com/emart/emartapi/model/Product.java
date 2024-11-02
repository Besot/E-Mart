package com.emart.emartapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")

public class Product extends  BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;
}
