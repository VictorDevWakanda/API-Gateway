package com.gateway.nimble.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gateway.nimble.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

        private static final String DEFAULT_URL = "http://localhost:8081";
        private static final String HEADER_NAME = "X-Gateway";
        private static final String HEADER_VALUE = "Spring";

        @Bean
        public RouteLocator customRouteLocator(RouteLocatorBuilder builder, JwtAuthenticationFilter jwt) {
                return builder.routes()
                                .route("usuario-cadastro", r -> r.path("/nimble-pagamento/api/usuario")
                                                .filters(f -> f.addRequestHeader(HEADER_NAME, HEADER_VALUE)) // sem jwt
                                                .uri(DEFAULT_URL))
                                .route("login", r -> r.path("/nimble-pagamento/api/usuario/autenticacao/login")
                                                .filters(f -> f.addRequestHeader(HEADER_NAME, HEADER_VALUE))
                                                .uri(DEFAULT_URL))
                                .route("usuario-protegido", r -> r.path("/nimble-pagamento/api/usuario/**")
                                                .filters(f -> f.filter(jwt)
                                                                .addRequestHeader(HEADER_NAME, HEADER_VALUE))
                                                .uri(DEFAULT_URL))

                                .route("cobranca", r -> r.path("/nimble-pagamento/cobrancas/**")
                                                .filters(f -> f.filter(jwt).addRequestHeader(HEADER_NAME, HEADER_VALUE))
                                                .uri(DEFAULT_URL))
                                .route("cobranca", r -> r.path("/nimble-pagamento/cobrancas")
                                                .filters(f -> f.filter(jwt).addRequestHeader(HEADER_NAME, HEADER_VALUE))
                                                .uri(DEFAULT_URL))
                                .build();
        }

}
