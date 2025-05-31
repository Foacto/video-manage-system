package com.tuan.vtube.apigateway.config;

import com.tuan.vtube.apigateway.common.Constants;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class UserDataFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> {

                    ServerHttpRequest mutatedRequest = exchange.getRequest()
                            .mutate()
                            .header(Constants.USER_DATA,
                                    ((Jwt)securityContext.getAuthentication().getPrincipal()).getClaim("sub")
                                            + Constants.DELIMITER +
                                            securityContext.getAuthentication().getName())
                            .header(Constants.USER_DATA_ACCESS, securityContext.getAuthentication().getAuthorities().toString())
                            .header(Constants.USER_DATA_ACCESS, securityContext.getAuthentication().getAuthorities().toString())
                            .build();

                    return exchange.mutate()
                            .request(mutatedRequest)
                            .build();
                }).flatMap(chain::filter);
    }
}
