package com.informatorio.info_market.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", nullable = false, updatable = false)
    private UUID id;

    @Column (nullable = false, unique = true, updatable = false)
    private int dni;

    @Column (nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carrito> carritos;

    @Column (nullable = false, length = 50)
    private String apellido;

    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

    public Usuario(){

    }

    public Usuario(UUID id,
                   int dni, 
                   String nombre, 
                   String apellido, 
                   List<Carrito> carritos, 
                   LocalDate fechaCreacion, 
                   LocalDate fechaActualizacion){
        
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carritos = carritos;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }


    public UUID getId(){
        return id;
    }

    public void setId(UUID id){

        this.id = id;
    }

    public int getDni(){
        
        return this.dni;
    }

    public void setDni(int dni){

        this.dni = dni;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){

        this.nombre = nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public void setApellido(String apellido){

        this.apellido = apellido;
    }

    public List<Carrito> getCarritos(){

        return this.carritos;
    }

    public void setCarritos(List<Carrito> carritos){

        this.carritos = carritos;
    }

     public LocalDate getFechaCreacion(){
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion){

        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaActualizacion(){
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion){

        this.fechaActualizacion = fechaActualizacion;
    }
}
