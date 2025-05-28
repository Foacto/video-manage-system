package com.tuan.vtube.apigateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class UserDataFilter implements WebFilter {
    private final String USER_DATA = "vtube-auth";
    private final String USER_DATA_ACCESS = "vtube-access";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> {

                    ServerHttpRequest mutatedRequest = exchange.getRequest()
                            .mutate()
                            .header(USER_DATA, securityContext.getAuthentication().getName())
                            .header(USER_DATA_ACCESS, securityContext.getAuthentication().getAuthorities().toString())
                            .build();

                    return exchange.mutate()
                            .request(mutatedRequest)
                            .build();
                }).flatMap(chain::filter);
    }
}
