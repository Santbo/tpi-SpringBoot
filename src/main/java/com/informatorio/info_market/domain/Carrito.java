package com.informatorio.info_market.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.informatorio.info_market.enumerations.EstadoCarritoEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Carrito {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", nullable = false, updatable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private EstadoCarritoEnum estadoCarrito;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "carrito")
    private List<ItemCarrito> itemsCarrito = new ArrayList<>();

    @OneToOne
    private Factura factura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

    public Carrito(){

    }

    public Carrito(UUID id, 
                   EstadoCarritoEnum estadoCarrito, 
                   List<ItemCarrito> itemsCarrito, 
                   Factura factura, 
                   Usuario usuario, 
                   LocalDate fechaCreacion, 
                   LocalDate fechaActualizacion){

        
        this.id = id;
        this.estadoCarrito = estadoCarrito;
        this.itemsCarrito = itemsCarrito;
        this.factura = factura;
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }
    
    public UUID getId(){
        return id;
    }

    public void setId(UUID id){

        this.id = id;
    }

    public EstadoCarritoEnum getEstadoCarrito(){
        
        return estadoCarrito;
    }

    public void setEstadoCarrito(EstadoCarritoEnum estadoCarrito){

        this.estadoCarrito = estadoCarrito;
    }

    public List<ItemCarrito> getItemsCarrito(){

        return this.itemsCarrito;
    }

    public void setItemsCarrito(List<ItemCarrito> itemsCarrito){

        this.itemsCarrito = itemsCarrito;
    }

    public Factura getFactura(){

        return this.factura;
    }

    public void setFactura(Factura factura){

        this.factura = factura;
    }

    public Usuario getUsuario(){

        return this.usuario;
    }

    public void setUsuario(Usuario usuario){

        this.usuario = usuario;
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
