package com.gateway.nimble.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import com.gateway.nimble.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

        private static final String DEFAULT_URL = "http://localhost:8081";
        private static final String HEADER_NAME = "X-Gateway";
        private static final String HEADER_VALUE = "Spring";

        @Bean
        public RouteLocator customRouteLocator(RouteLocatorBuilder builder, JwtAuthenticationFilter jwt) {
                return builder.routes()
                                // Rotas publicas (sem JWT)
                                .route("usuario-cadastro", r -> r.path("/nimble-pagamento/api/usuario").and()
                                                .method(HttpMethod.POST)
                                                .filters(f -> f.addRequestHeader(HEADER_NAME, HEADER_VALUE))
                                                .uri(DEFAULT_URL))
                                .route("login", r -> r.path("/nimble-pagamento/api/usuario/autenticacao/login").and()
                                                .method(HttpMethod.POST)
                                                .filters(f -> f.addRequestHeader(HEADER_NAME, HEADER_VALUE))
                                                .uri(DEFAULT_URL))
                                .route("swagger", r -> r.path("/nible-pagamento/api/v3/api-docs",
                                                "/nible-pagamento/api/swagger",
                                                "/nimble-pagamento/api/swagger/**")
                                                .filters(f -> f.addRequestHeader(HEADER_NAME, HEADER_VALUE))
                                                .uri(DEFAULT_URL))

                                // Rotas protegidas (com JWT)
                                .route("usuario-protegido", r -> r.path("/nimble-pagamento/api/usuario/**")
                                                .filters(f -> f.filter(jwt).addRequestHeader(HEADER_NAME, HEADER_VALUE))
                                                .uri(DEFAULT_URL))
                                .route("cobranca-sub", r -> r.path("/nimble-pagamento/api/cobrancas/**")
                                                .filters(f -> f.filter(jwt).addRequestHeader(HEADER_NAME, HEADER_VALUE))
                                                .uri(DEFAULT_URL))
                                .route("cobranca-root", r -> r.path("/nimble-pagamento/api/cobrancas")
                                                .filters(f -> f.filter(jwt).addRequestHeader(HEADER_NAME, HEADER_VALUE))
                                                .uri(DEFAULT_URL))
                                .build();
        }

}
