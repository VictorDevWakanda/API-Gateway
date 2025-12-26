package com.gateway.nimble.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gateway.nimble.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

        @Bean
        public RouteLocator customRouteLocator(RouteLocatorBuilder builder, JwtAuthenticationFilter jwt) {
                return builder.routes()
                                .route("usuario", r -> r.path("/nimble-pagamento/api/usuario/**")
                                                .filters(f -> f.filter(jwt)
                                                                .addRequestHeader("X-Gateway", "Spring"))
                                                .uri("http://localhost:8081"))
                                .route("login", r -> r.path("/nimble-pagamento/usuario/autenticacao/login")
                                                .uri("http://localhost:8081"))
                                .build();
        }

}
