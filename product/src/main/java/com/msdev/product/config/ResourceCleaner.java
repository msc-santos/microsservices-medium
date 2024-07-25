package com.msdev.product.config;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class ResourceCleaner {
    @PreDestroy
    public void cleanUp() {
        System.out.println("Lógica para limpar os recursos...");
    }
}
