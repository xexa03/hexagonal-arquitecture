package com.example.hexagonal.domain.service;

import com.example.hexagonal.application.service.OrderService;
import com.example.hexagonal.domain.model.Order;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class OrderServiceTest {

    @Inject
    OrderService orderService;

    @Test
    public void testCreateOrder() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.save(order);
        assertNotNull(order.getId());
    }

    @Test
    public void testUpdateOrderStatus() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.save(order);
        Optional<Order> orderFind = orderService.findById(order.getId());
        assertEquals(order,orderFind.get());
    }



}
