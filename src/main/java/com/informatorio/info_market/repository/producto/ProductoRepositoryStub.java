package com.informatorio.info_market.repository.producto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.informatorio.info_market.domain.Producto;

public class ProductoRepositoryStub {
    
    public static List<Producto> getAllProductos(){

        List<Producto> productos = new ArrayList<>();
        Producto producto = new Producto();
        producto.setId( UUID.randomUUID());
        producto.setNombre("Producto");
        producto.setDescripcion("Este es un producto");
        producto.setPrecio(10000);
        producto.setStock(10);
        producto.setFechaDeCreacion(LocalDate.now());
        producto.setFechaActualizacion(LocalDate.now());
        productos.add(producto);
        
        Producto producto2 = new Producto();
        producto2.setId( UUID.randomUUID());
        producto2.setNombre("Producto 2");
        producto2.setDescripcion("Este es un producto 2");
        producto2.setPrecio(15123);
        producto2.setStock(5);
        producto2.setFechaDeCreacion(LocalDate.now());
        producto2.setFechaActualizacion(LocalDate.now());
        productos.add(producto2);
        return productos;
    }
}
