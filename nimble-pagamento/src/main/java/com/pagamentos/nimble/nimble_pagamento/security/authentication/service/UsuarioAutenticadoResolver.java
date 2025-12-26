package com.pagamentos.nimble.nimble_pagamento.security.authentication.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.pagamentos.nimble.nimble_pagamento.handler.APIException;
import com.pagamentos.nimble.nimble_pagamento.usuario.application.repository.UsuarioRepository;
import com.pagamentos.nimble.nimble_pagamento.usuario.domain.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class UsuarioAutenticadoResolver {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public Usuario resolveAutenticacao(HttpServletRequest request) {
        String token = extrairToken(request);
        String idUsuario = tokenService.validateToken(token);
        if (idUsuario == null) {
            throw APIException.build(HttpStatus.UNAUTHORIZED, "Token inválido ou expirado");
        }
        return buscarUsuarioAutenticado(idUsuario);
    }

    private String extrairToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return (authHeader != null && authHeader.startsWith("Bearer "))
                ? authHeader.replace("Bearer ", "")
                : null;
    }

    private Usuario buscarUsuarioAutenticado(String idUsuario) {
        return usuarioRepository.buscaUsuarioAtravesId(UUID.fromString(idUsuario));

    }

}
