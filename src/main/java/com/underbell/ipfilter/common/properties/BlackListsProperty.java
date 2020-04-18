package com.underbell.ipfilter.common.properties;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.util.SubnetUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Component
@ConfigurationProperties("blacklists")
@Getter
public class BlackListsProperty {
    private boolean filter;
    private List<String> ips;
    private HashSet<String> ipHsahs = new HashSet<>();

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    public void setIps(List<String> ips) {
        this.ips = ips;
        for (String ip : this.getIps()) {
            SubnetUtils subnetUtils = new SubnetUtils(ip);
            subnetUtils.setInclusiveHostCount(true);
            this.ipHsahs.addAll(Arrays.asList(subnetUtils.getInfo().getAllAddresses()));
        }
        log.info("ipHsahs size = {}", this.ipHsahs.size());
    }
}
