package me.projects.billingservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projects.billingservice.models.Product;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    @ManyToOne
    private Bill bill;
    private Integer quantity;
    private Double price;
    private Double discount;
    @Transient
    private Product product;
}
