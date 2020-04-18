package com.underbell.ipfilter.common.rest.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class IPControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void test01_ipv4() {
        String ip = webTestClient.mutate().defaultHeader("X-FORWARDED-FOR", "127.0.0.1").build()
                .get()
                .uri("/ipv4")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(ip);
        Assertions.assertEquals(ip, "127.0.0.1");
    }
}