package com.underbell.ipfilter.common.rest.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IPDto {
    private String sourceIp;
    private HttpStatus status;

    @Builder
    private IPDto(final String sourceIp, final HttpStatus status) {
        this.sourceIp = sourceIp;
        this.status = status;
    }
}
