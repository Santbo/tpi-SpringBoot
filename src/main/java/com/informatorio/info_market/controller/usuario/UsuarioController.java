package com.informatorio.info_market.controller.usuario;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatorio.info_market.dto.usuario.UsuarioDto;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;
import com.informatorio.info_market.service.usuario.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/usuarios")
@AllArgsConstructor
public class UsuarioController {
    
    private UsuarioService usuarioService;

    @GetMapping()
    public List<UsuarioDto> getAllUsuarios(@RequestParam(value = "carritoConEstado", required = false) EstadoCarritoEnum carritoConEstado){

        
       return usuarioService.getAllUsuarios(carritoConEstado);
    }
}
