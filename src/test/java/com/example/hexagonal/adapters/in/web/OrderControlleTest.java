package com.example.hexagonal.adapters.in.web;
import com.example.hexagonal.domain.model.Order;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@QuarkusTest
public class OrderControlleTest {

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
