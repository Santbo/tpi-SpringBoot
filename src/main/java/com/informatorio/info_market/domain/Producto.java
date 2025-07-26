package com.informatorio.info_market.domain;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", nullable = false, updatable = false)
    private UUID id;

    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private LocalDate fechaDeCreacion;
    private LocalDate fechaActualizacion;

    @ManyToMany
    private List<Categoria> categorias;

    public Producto(){

    }

    public Producto(String nombre, 
                    UUID id, 
                    String descripcion, 
                    double precio, 
                    int stock, 
                    LocalDate fechaDeCreacion, 
                    LocalDate fechaActualizacion,
                    List<Categoria> categorias){

        
        this.nombre = nombre;
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.categorias = categorias;
    }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){

        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){

        this.nombre = nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){

        this.descripcion = descripcion;
    }

    public double getPrecio(){
        return precio;
    }

    public void setPrecio(double precio){

        this.precio = precio;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){

        this.stock = stock;
    }

    public LocalDate getFechaDeCreacion(){
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion){

        this.fechaDeCreacion = fechaDeCreacion;
    }

    public LocalDate getFechaActualizacion(){
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion){

        this.fechaActualizacion = fechaActualizacion;
    }

    public List<Categoria> getCategorias(){

        return this.categorias;
    }

    public void setCategorias(List<Categoria> categorias){

        this.categorias = categorias;
    }
}
