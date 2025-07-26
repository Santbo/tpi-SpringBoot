package com.informatorio.info_market.repository.producto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.informatorio.info_market.domain.Producto;

public interface ProductoRepository extends JpaRepository<Producto, UUID>{
    
    //Query Methods
    //Filtro por minStock minPrice y maxPrice
    List<Producto> findAllByStockIsGreaterThanAndPrecioIsBetween(int minStock, Double minPrice, Double maxPrice);

    //Filtrar por maxPrice
    List<Producto> findAllByPrecioIsLessThan(Double maxPrice);

    //Filtrar por minStock
    List<Producto> findAllByStockIsGreaterThan(int minStock);

    //HQL

    @Query("SELECT p FROM Producto p WHERE p.precio > :minPrecio")
    List<Producto> obtenerTodosLosProductosConUnPrecioMayoA(Double minPrecio);

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombreProd, '%'))")
    List<Producto> obtenerTodosLosProductosConNombreProd(String nombreProd);

    //SQL - NATIVE QUERY

    @Query(value= "SELECT * FROM producto WHERE fecha_de_creacion >:fecha" , nativeQuery = true)
    List<Producto> obtenerProductosCreadosDespuesDe(@Param("fecha") LocalDate fecha); //se crea sql puro

    
    @Query(value = """
            SELECT p.* FROM producto p 
            JOIN producto_categoria pc ON pc.producto_id = p.uuid
            JOIN categorias c ON c.id = pc.categorias_id
            WHERE c.nombre = :nombreCategoria
            """, nativeQuery = true)
    List<Producto> obtenerTodosLosProductosConNombreCategoriaNative(@Param("nombreCategoria") String nombreCategoria);
}
