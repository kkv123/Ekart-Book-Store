package com.ekart.bookStore.entity.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ekart.bookStore.entity.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="order_item_details")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderitem_id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="order_id",nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int amount;

}
