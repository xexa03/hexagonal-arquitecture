package com.example.hexagonal.application.service;

import com.example.hexagonal.application.ports.out.OrderPort;
import com.example.hexagonal.domain.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    OrderPort orderPort;

    @InjectMocks
    OrderService orderService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave(){
        Order order = new Order(LocalDateTime.now(), "status");
        when(orderPort.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.save(order);

        assertEquals(savedOrder.getDescription(), order.getDescription());
        verify(orderPort,times(1)).save(order);
    }

    @Test
    void testFindAll(){
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(LocalDateTime.now(), "desc1"));
        orders.add(new Order(LocalDateTime.now(), "desc2"));
        when(orderPort.findAll()).thenReturn(orders);

        List<Order> foundOrders = orderService.findAll();

        assertEquals(foundOrders.size(), orders.size());
        verify(orderPort,times(1)).findAll();

    }

    @Test
    void testFindById(){
        Order order = new Order(LocalDateTime.now(), "status");

        when(orderPort.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> getOrder = orderService.findById(1L);

        assertEquals(order.getDescription(), getOrder.get().getDescription());
        verify(orderPort,times(1)).findById(1L);

    }

    @Test
    void testDelete(){
        doNothing().when(orderPort).deleteById(1L);

        orderService.deleteById(1L);

        verify(orderPort,times(1)).deleteById(1L);
    }

}