package com.accenture.mocktibco.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collections;

@Slf4j
public class CacheConfig extends CachingConfigurerSupport {

    private final String CACHENAME = "tibco";

    @Bean
    @Override
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Collections.singletonList(new ConcurrentMapCache(CACHENAME)));
        return cacheManager;
    }

    @CacheEvict(cacheNames = CACHENAME, allEntries = true)
    @Scheduled(fixedRate = 1000 * 60 * 15)
    public void clearCache() {
        log.info("Cache [{}] evicted", CACHENAME);
    }
}
