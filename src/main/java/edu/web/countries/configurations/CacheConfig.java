package edu.web.countries.configurations;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@Configuration
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new CustomTtlCacheManager();
    }

    private static class CustomTtlCacheManager implements CacheManager {
        private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>();

        @Override
        public Cache getCache(String name) {
            if (cacheMap.containsKey(name)) {
                return cacheMap.get(name);
            } else {
                Cache cache = createCache(name);
                cacheMap.put(name, cache);
                return cache;
            }
        }

        @Override
        public Collection<String> getCacheNames() {
            return cacheMap.keySet();
        }

        private Cache createCache(String name) {
            return new CustomTtlConcurrentMapCache(name);
        }
    }

    private static class CustomTtlConcurrentMapCache extends ConcurrentMapCache {
        CustomTtlConcurrentMapCache(String name) {
            super(name, new ConcurrentHashMap<>(), false);
        }

        // You can implement custom behavior for TTL enforcement here
    }
}
