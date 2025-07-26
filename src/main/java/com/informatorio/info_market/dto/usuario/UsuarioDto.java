package com.informatorio.info_market.dto.usuario;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
    
    private UUID id;
    private String nombre;
    private String apellido;
    private int dni;
}
