package com.tuan.vtube.apigateway.config;

import com.google.gson.Gson;
import com.tuan.vtube.apigateway.common.Constants;
import com.tuan.vtube.common.AESEncryption;
import com.tuan.vtube.model.UserData;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        Gson gson = new Gson();
        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> {

                    ServerHttpRequest mutatedRequest = exchange.getRequest()
                            .mutate()
                            .header(Constants.USER_DATA, AESEncryption.encrypt(gson.toJson(getUserDataFromContext(securityContext))))
                            .build();

                    return exchange.mutate()
                            .request(mutatedRequest)
                            .build();
                }).flatMap(chain::filter);
    }

    private UserData getUserDataFromContext(SecurityContext securityContext) {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContext.getAuthentication().getAuthorities();
        List<String> roles = authorities.stream()
                .filter(grantedAuthority -> grantedAuthority.getAuthority().startsWith(Constants.ROLE))
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        List<String> actions = authorities.stream()
                .filter(grantedAuthority -> grantedAuthority.getAuthority().startsWith(Constants.ACTION))
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        List<String> scopes = authorities.stream()
                .filter(grantedAuthority -> grantedAuthority.getAuthority().startsWith(Constants.SCOPE))
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Jwt jwt = (Jwt) securityContext.getAuthentication().getPrincipal();
        return UserData.builder()
                .id(jwt.getClaim("sub"))
                .name(jwt.getClaim("name"))
                .email(jwt.getClaim("email"))
                .roles(roles)
                .actions(actions)
                .scopes(scopes)
                .build();
    }
}
