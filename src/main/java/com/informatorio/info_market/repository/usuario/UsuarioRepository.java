package com.informatorio.info_market.repository.usuario;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.informatorio.info_market.domain.Usuario;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{

    @Query("SELECT DISTINCT u FROM Usuario u JOIN u.carritos c WHERE c.estadoCarrito = :carritoConEstado")
    List<Usuario> findAllUsuarioConEstadoCarrito (@Param("carritoConEstado") EstadoCarritoEnum carritoConEstado);
}
