package com.sparta.juteukki02.juteukki_week02.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@EnableCaching
@Configuration
public class LocalCacheConfig {
    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("post"),new ConcurrentMapCache("my_like")));
        return simpleCacheManager;
    }
}
