package com.informatorio.info_market.domain;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Representa la relacion entre un producto y su carrito
//Tendremos un carrito de la lista de carritos con todos los productos y sus cantidades
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Carrito carrito;

    private int cantidad;
}
