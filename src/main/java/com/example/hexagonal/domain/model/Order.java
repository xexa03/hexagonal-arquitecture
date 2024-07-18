package com.example.hexagonal.domain.model;

import jakarta.persistence.GeneratedValue;

import java.util.List;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.example.hexagonal.domain.model.OrderItem;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime creationDate;
    private String description;
    private OrderStatus status;

    private List<OrderItem> orderItems;

    public Order(){

    }

   public Order(Long id, String description, LocalDateTime creationDate, OrderStatus status,
                List<OrderItem> orderItems) {
        this.id = id;
        this.creationDate = creationDate;
        this.description = description;
        this.status = status;
        this.orderItems = orderItems;
   }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
