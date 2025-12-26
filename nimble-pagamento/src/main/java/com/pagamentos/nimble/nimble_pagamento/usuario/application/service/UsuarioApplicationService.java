package com.pagamentos.nimble.nimble_pagamento.usuario.application.service;

import org.springframework.stereotype.Service;

import com.pagamentos.nimble.nimble_pagamento.security.authentication.service.UsuarioAutenticadoResolver;
import com.pagamentos.nimble.nimble_pagamento.usuario.application.api.UsuarioAlteracaoRequest;
import com.pagamentos.nimble.nimble_pagamento.usuario.application.api.UsuarioDetalhadoResponse;
import com.pagamentos.nimble.nimble_pagamento.usuario.application.api.UsuarioRequest;
import com.pagamentos.nimble.nimble_pagamento.usuario.application.api.UsuarioResponse;
import com.pagamentos.nimble.nimble_pagamento.usuario.application.repository.UsuarioRepository;
import com.pagamentos.nimble.nimble_pagamento.usuario.domain.Usuario;
import com.pagamentos.nimble.nimble_pagamento.usuario.domain.service.SenhaHashService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class UsuarioApplicationService implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final SenhaHashService senhaHashService;
    private final UsuarioAutenticadoResolver resolver;

    @Override
    public UsuarioResponse criaUsuario(UsuarioRequest usuarioRequest) {
        log.info("[Inicia] UsuarioApplicationService - criaUsuario");
        String senhaHash = senhaHashService.gerarHash(usuarioRequest.getSenha());
        Usuario novoUsuario = new Usuario(usuarioRequest, senhaHash);
        usuarioRepository.salvaUsuario(novoUsuario);
        log.debug("[Finaliza] UsuarioApplicationService - criaUsuario");
        return new UsuarioResponse(novoUsuario.getIdUsuario());
    }

    @Override
    public UsuarioDetalhadoResponse buscaUsuarioAtravesId(HttpServletRequest request) {
        log.info("[Inicia] UsuarioApplicationService - buscaUsuarioAtravesId");
        Usuario usuario = resolver.resolveAutenticacao(request);
        log.debug("[Finaliza] UsuarioApplicationService - buscaUsuarioAtravesId");
        return new UsuarioDetalhadoResponse(usuario);
    }

    @Override
    public void deletaUsuarioAtravesId(HttpServletRequest request) {
        log.info("[Inicia] UsuarioApplicationService - deletaUsuarioAtravesId");
        Usuario usuario = resolver.resolveAutenticacao(request);
        usuarioRepository.deletaUsuario(usuario);
        log.debug("[Finaliza] UsuarioApplicationService - deletaUsuarioAtravesId");
    }

    @Override
    public void patchAlteraUsuario(HttpServletRequest request, UsuarioAlteracaoRequest usuarioAlteracaoRequest) {
        log.info("[Inicia] UsuarioApplicationService - patchAlteraUsuario");
        Usuario usuario = resolver.resolveAutenticacao(request);
        String senhaHash = senhaHashService.gerarHash(usuarioAlteracaoRequest.getSenha());
        usuario.altera(usuarioAlteracaoRequest, senhaHash);
        usuarioRepository.salvaUsuario(usuario);
        log.debug("[Finaliza] UsuarioApplicationService - patchAlteraUsuario");
    }

}
