package com.informatorio.info_market.service.usuario;

import java.util.List;
import java.util.UUID;

import com.informatorio.info_market.domain.Usuario;
import com.informatorio.info_market.dto.usuario.UsuarioDto;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;

public interface UsuarioService {
    
    Usuario getUsuarioEntityById(UUID id);
    List<UsuarioDto> getAllUsuarios(EstadoCarritoEnum carritoConEstado);
}
