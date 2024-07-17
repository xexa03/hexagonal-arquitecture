package com.example.hexagonal.adapter.in.web;

import com.example.hexagonal.application.ports.in.OrderUseCase;
import com.example.hexagonal.domain.model.Order;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {


    OrderUseCase orderUseCase;

    @Inject
    public OrderResource(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @POST
    public Response createOrder(Order order) {
        Order createOrder = orderUseCase.save(order);
        return Response.status(Response.Status.CREATED).entity(createOrder).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Optional<Order> order = orderUseCase.findById(id);
        return order.map(response -> Response.status(Response.Status.OK).entity(response).build()).orElse(null);
    }

    @GET
    public Response getAllOrders() {
        return Response.ok(orderUseCase.findAll()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Long orderId) {
        orderUseCase.deleteById(orderId);
        return Response.noContent().build();
    }
}
