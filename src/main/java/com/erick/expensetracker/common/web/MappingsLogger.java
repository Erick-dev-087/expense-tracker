package com.erick.expensetracker.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import jakarta.annotation.PostConstruct;
import java.util.Map;

@Component
public class MappingsLogger {
    private final RequestMappingHandlerMapping mapping;
    private static final Logger log = LoggerFactory.getLogger(MappingsLogger.class);

    public MappingsLogger(RequestMappingHandlerMapping mapping) {
        this.mapping = mapping;
    }

    @PostConstruct
    public void logAllMappings() {
        Map<RequestMappingInfo, ?> map = mapping.getHandlerMethods();
        log.info("==== Registered request mappings ====");
                map.forEach((info, handler) -> log.info(info + " -> " + handler));
                        log.info("====================================");
    }
}