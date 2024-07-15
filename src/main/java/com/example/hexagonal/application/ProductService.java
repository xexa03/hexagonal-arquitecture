package com.example.hexagonal.application;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductService {

    public String getProducts(){
        //Lógica de implementación de servicio
        return "Listado de productos";
    }
}
