package com.example.hexagonal.adapter.out.persistence;


import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.model.OrderStatus;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
@Transactional
class JpaOrderRepositoryTest {

    @Inject
    JpaOrderRepository jpaOrderRepository;

    @Inject
    EntityManager em;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Order order = new Order(null, "Test Order", LocalDateTime.now(), OrderStatus.PENDING,new ArrayList<>());

        em.persist(order);
        em.flush();
        em.clear();

        Optional<Order> order2 = jpaOrderRepository.findById(order.getId());

        assertEquals(order2.get().getId().intValue(), order.getId().intValue());
        assertEquals(order2.get().getDescription(), order.getDescription());
        assertTrue(order2.isPresent());

    }

    @Test
    void testFindAll() {
    }

    @Test
    void testSave() {
        Order order = new Order(null, "Test Order", LocalDateTime.now(), OrderStatus.PENDING,new ArrayList<>());

        jpaOrderRepository.save(order);
        jpaOrderRepository.save(order);

        Optional<Order> order2 = jpaOrderRepository.findById(order.getId());

        assertEquals(order2.get().getId().intValue(), order.getId().intValue());
        assertEquals(order2.get().getDescription(), order.getDescription());
        assertTrue(order2.isPresent());

    }


    @Test
    void testDeleteById() {
        Order order = new Order(null, "Test Order", LocalDateTime.now(), OrderStatus.PENDING,new ArrayList<>());

        jpaOrderRepository.deleteById(1L);

        verify(em,times(1)).find(Order.class, 1L);
    }

    @Test
    void testUpdate() {
        Order order = new Order(1L, "Test Order", LocalDateTime.now(), OrderStatus.PENDING,new ArrayList<>());

        jpaOrderRepository.update(order);

        Optional<Order> order2 = jpaOrderRepository.findById(order.getId());

        assertEquals(order2.get().getId().intValue(), order.getId().intValue());
        assertEquals(order2.get().getDescription(), order.getDescription());
        assertTrue(order2.isPresent());

    }
}