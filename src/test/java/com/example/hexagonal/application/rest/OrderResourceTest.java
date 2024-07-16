package com.example.hexagonal.application.rest;
import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.model.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class OrderResourceTest {

    @Test
    public void testCreateEndPoint(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                .statusCode(201)
                .body("status",is("PENDING"));
    }


    @Test
    public void testAddItemToOrderEndPoint(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                .statusCode(201);

        OrderItem item = new OrderItem("product1", 2, new BigDecimal("50.00"));
        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders/{orderId}/items", order.getId())
                .then()
                .statusCode(200);
    }

    @Test
    public void testUpdateOrderStatusEndPoint(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                .statusCode(201);

        String statusUpdate = "CONFIRMED";

        given()
                .contentType("application/json")
                .body(statusUpdate)
                .when().post("/orders/{orderId}/status", order.getId())
                .then()
                .statusCode(200)
                .body("status",is("CONFIRMED"));
    }
}
