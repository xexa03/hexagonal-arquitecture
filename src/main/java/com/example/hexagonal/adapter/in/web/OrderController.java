package com.example.hexagonal.adapter.in.web;

import com.example.hexagonal.application.service.OrderService;
import com.example.hexagonal.domain.model.Order;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

    OrderService orderService;

    @Inject
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @POST
    public Response createOrder(Order order) {
        Order createOrder = orderService.save(order);
        return Response.status(Response.Status.CREATED).entity(createOrder).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Optional<Order> order = orderService.findById(id);
        return order.map(response -> Response.status(Response.Status.OK).entity(response).build()).orElse(null);
    }

    @GET
    public Response getAllOrders() {
        return Response.ok(orderService.findAll()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Long orderId) {
        orderService.deleteById(orderId);
        return Response.noContent().build();
    }

}
