package com.resort.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderFood")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "foodItemId")
    private FoodItem foodItem;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public enum OrderStatus {
        CREATED,
        DISPATCHED,
        DELIVERED
    }

    // Getters, Setters, Constructors, etc.
}