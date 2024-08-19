package com.example.erp.config.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheService {
    @Autowired
    CacheManager cacheManager;

    private Cache defaultCache = null;

    public void putValueInCache(String key, List<?> value) {
        Cache cache = cache();
        if (cache != null) {
            cache.put(key, value);
        }
    }

    public void putValueInCache(String key, Object value) {
        Cache cache = cache();
        if (cache != null) {
            cache.put(key, value);
        }
    }

    public Object getValueFromCache(String key) {
        Cache cache = cache();
        if (cache != null) {
            return cache.get(key);
        }
        return null;
    }

    public List<?> getListValueFromCache(String key) {
        Cache cache = cache();
        if (cache != null) {
            return cache.get(key, List.class);
        }
        return null;
    }

    public void removeFromCache(String key) {
        Cache cache = cache();
        if (cache != null) {
            cache.evict(key);
        }
    }

    private Cache cache() {
        if (defaultCache != null) {
            return defaultCache;
        }
        defaultCache = cacheManager.getCache(CacheConfig.CACHE_NAME);
        return defaultCache;
    }
}
