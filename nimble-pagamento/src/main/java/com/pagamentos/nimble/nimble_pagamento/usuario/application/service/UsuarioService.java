package com.pagamentos.nimble.nimble_pagamento.usuario.application.service;

import com.pagamentos.nimble.nimble_pagamento.usuario.application.api.UsuarioAlteracaoRequest;
import com.pagamentos.nimble.nimble_pagamento.usuario.application.api.UsuarioDetalhadoResponse;
import com.pagamentos.nimble.nimble_pagamento.usuario.application.api.UsuarioRequest;
import com.pagamentos.nimble.nimble_pagamento.usuario.application.api.UsuarioResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

public interface UsuarioService {

    UsuarioResponse criaUsuario(@Valid UsuarioRequest usuarioRequest);

    UsuarioDetalhadoResponse buscaUsuarioAtravesId(HttpServletRequest request);

    void deletaUsuarioAtravesId(HttpServletRequest request);

    void patchAlteraUsuario(HttpServletRequest request, UsuarioAlteracaoRequest usuarioAlteracaoRequest);
}
