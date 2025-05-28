package com.tuan.vtube.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Value("${jwt.auth.converter.principle-attribute}")
    private String PRINCIPLE_ATTRIBUTE;
    @Value("${jwt.auth.converter.realm-access}")
    private String REALM_ACCESS;
    @Value("${jwt.auth.converter.resource-access}")
    private String RESOURCE_ACCESS;
    @Value("${jwt.auth.converter.resource-id}")
    private String RESOURCE_ID;
    @Value("${jwt.auth.converter.role}")
    private String ROLE;

    @Override
    public Mono<AbstractAuthenticationToken> convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                        extractResourceRolesAndAction(jwt).stream())
                .collect(Collectors.toSet());

        return Mono.just(new JwtAuthenticationToken(jwt, authorities, getPrincipleClaimName(jwt)));
    }

    private String getPrincipleClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (PRINCIPLE_ATTRIBUTE != null) {
            claimName = PRINCIPLE_ATTRIBUTE;
        }
        return jwt.getClaim(claimName);
    }

    private Collection<? extends GrantedAuthority> extractResourceRolesAndAction(Jwt jwt) {
        return Stream.concat(extractResourceRoles(jwt).stream(),
                        extractResourceAction(jwt).stream())
                .collect(Collectors.toSet());
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> realmAccess;
        Collection<String> roles;
        if (jwt.getClaim(REALM_ACCESS) == null) {
            return Set.of();
        }
        realmAccess = jwt.getClaim(REALM_ACCESS);

        roles = (Collection<String>) realmAccess.get(ROLE);
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.replace("-", "_")))
                .collect(Collectors.toSet());
    }

    private Collection<? extends GrantedAuthority> extractResourceAction(Jwt jwt) {
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;
        if (jwt.getClaim(RESOURCE_ACCESS) == null) {
            return Set.of();
        }
        resourceAccess = jwt.getClaim(RESOURCE_ACCESS);

        if (resourceAccess.get(RESOURCE_ID) == null) {
            return Set.of();
        }
        resource = (Map<String, Object>) resourceAccess.get(RESOURCE_ID);

        resourceRoles = (Collection<String>) resource.get(ROLE);
        return resourceRoles.stream()
                .map(action -> new SimpleGrantedAuthority("ACTION_" + action.replace("-", "_")))
                .collect(Collectors.toSet());
    }
}
