package com.underbell.ipfilter.common.rest.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IPDto {
    private String sourceIp;
    private String status;

    @Builder
    private IPDto(final String sourceIp, final String status) {
        this.sourceIp = sourceIp;
        this.status = status;
    }
}
