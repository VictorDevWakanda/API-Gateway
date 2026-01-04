package com.gateway.nimble.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gateway.nimble.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

        private static final String DEFAULT_URL = "http://localhost:8081";

        @Bean
        public RouteLocator customRouteLocator(RouteLocatorBuilder builder, JwtAuthenticationFilter jwt) {
                return builder.routes()
                                .route("usuario-cadastro", r -> r.path("/nimble-pagamento/api/usuario")
                                                .filters(f -> f.addRequestHeader("X-Gateway", "Spring")) // sem jwt
                                                .uri(DEFAULT_URL))
                                .route("usuario-protegido", r -> r.path("/nimble-pagamento/api/usuario/**")
                                                .filters(f -> f.filter(jwt)
                                                                .addRequestHeader("X-Gateway", "Spring"))
                                                .uri(DEFAULT_URL))

                                .route("login", r -> r.path("/nimble-pagamento/api/usuario/autenticacao/login")
                                                .uri(DEFAULT_URL))
                                .build();
        }

}
