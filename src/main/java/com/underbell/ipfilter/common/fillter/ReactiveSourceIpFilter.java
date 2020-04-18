package com.underbell.ipfilter.common.fillter;

import com.underbell.ipfilter.common.properties.BlackListsProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReactiveSourceIpFilter implements WebFilter {
    private final BlackListsProperty blackListsProperty;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (blackListsProperty.isFilter()) {
            String remoteIp = Optional.ofNullable(exchange.getRequest().getHeaders().get("X-FORWARDED-FOR"))
                    .orElseGet(() -> Arrays.asList(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()))
                    .get(0);

            if (blackListsProperty.getIpHsahs().contains(remoteIp)) {
                return Mono.error(() -> new RuntimeException("deny"));
            }
        }

        return chain.filter(exchange);
    }
}
