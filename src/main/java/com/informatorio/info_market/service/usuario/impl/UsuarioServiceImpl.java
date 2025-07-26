package com.informatorio.info_market.service.usuario.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.informatorio.info_market.domain.Usuario;
import com.informatorio.info_market.dto.usuario.UsuarioDto;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;
import com.informatorio.info_market.exceptions.notfound.NotFoundException;
import com.informatorio.info_market.mapper.usuario.UsuarioMapper;
import com.informatorio.info_market.repository.usuario.UsuarioRepository;
import com.informatorio.info_market.service.usuario.UsuarioService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    
    UsuarioRepository usuarioRepository;
    UsuarioMapper usuarioMapper;

    public Usuario getUsuarioEntityById(UUID id){

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()){

            return usuario.get();
        } else {
           throw new NotFoundException("No se encontro el usuario con el id: "+ id);
        }
    }

    public List<UsuarioDto> getAllUsuarios(EstadoCarritoEnum carritoConEstado){

        if (carritoConEstado instanceof EstadoCarritoEnum){

            return usuarioRepository.findAllUsuarioConEstadoCarrito(carritoConEstado).stream() 
                                                                                     .map(usuarioMapper::usuarioToUsuarioDto)
                                                                                     .toList();
        }
        return usuarioRepository.findAll().stream().map(usuarioMapper::usuarioToUsuarioDto).toList();
    }
}
