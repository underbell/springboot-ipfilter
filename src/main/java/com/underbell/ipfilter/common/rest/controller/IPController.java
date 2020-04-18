package com.underbell.ipfilter.common.rest.controller;

import com.underbell.ipfilter.common.rest.dto.IPDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("ipv4")
public class IPController {
    @GetMapping
    public Mono<IPDto> getSourceIp(ServerWebExchange exchange) {
        String ip = Optional.ofNullable(exchange.getRequest().getHeaders().get("X-FORWARDED-FOR"))
                .orElseGet(() -> Arrays.asList(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()))
                .get(0);
        boolean blacklist = Optional.ofNullable(exchange.getRequest().getHeaders().get("blacklist")).isPresent();
        return Mono.just(IPDto.builder().sourceIp(ip).status(blacklist ? "Deny" : "Allow").build());
    }
}
