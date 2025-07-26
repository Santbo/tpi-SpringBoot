package com.informatorio.info_market.mapper.usuario;

import org.mapstruct.Mapper;

import com.informatorio.info_market.domain.Usuario;
import com.informatorio.info_market.dto.usuario.UsuarioDto;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
}
