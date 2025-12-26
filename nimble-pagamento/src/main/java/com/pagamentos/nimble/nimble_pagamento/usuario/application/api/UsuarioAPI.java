package com.pagamentos.nimble.nimble_pagamento.usuario.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pagamentos.nimble.nimble_pagamento.docs.swagger.UsuarioAPIDocs;
import com.pagamentos.nimble.nimble_pagamento.usuario.application.service.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "UsuarioAPI", description = "Operações relacionadas ao usuário.")
public class UsuarioAPI {
        private final UsuarioService usuarioService;

        @UsuarioAPIDocs.CadastroUsuario
        @PostMapping()
        @ResponseStatus(HttpStatus.CREATED)
        public UsuarioResponse postUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
                log.info("[Inicia] UsuarioAPI - postUsuario");
                UsuarioResponse usuarioCriado = usuarioService.criaUsuario(usuarioRequest);
                log.debug("[Finaliza] UsuarioAPI - postUsuario");
                return usuarioCriado;
        }

        @UsuarioAPIDocs.ConsultaUsuarioPorId
        @GetMapping()
        @ResponseStatus(HttpStatus.OK)
        public UsuarioDetalhadoResponse getUsuarioAtravesId(HttpServletRequest request) {
                log.info("[Inicia] UsuarioAPI - getUsuarioAtravesId");
                UsuarioDetalhadoResponse usuarioDetalhado = usuarioService.buscaUsuarioAtravesId(request);
                log.debug("[Finaliza] UsuarioAPI - getUsuarioAtravesId");
                return usuarioDetalhado;
        }

        @UsuarioAPIDocs.DeletaUsuario
        @DeleteMapping()
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deletaUsuarioAtravesId(HttpServletRequest request) {
                log.info("[Inicia] UsuarioAPI - deletaUsuarioAtravesId");
                usuarioService.deletaUsuarioAtravesId(request);
                log.debug("[Finaliza] UsuarioAPI - deletaUsuarioAtravesId");
        }

        @UsuarioAPIDocs.AlteraUsuario
        @PatchMapping()
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void patchAlteraUsuario(HttpServletRequest request,
                        @Valid @RequestBody UsuarioAlteracaoRequest usuarioAlteracaoRequest) {
                log.info("[Inicia] UsuarioAPI - patchAlteraUsuario");
                usuarioService.patchAlteraUsuario(request, usuarioAlteracaoRequest);
                log.debug("[Finaliza] UsuarioAPI - patchAlteraUsuario");
        }
}
