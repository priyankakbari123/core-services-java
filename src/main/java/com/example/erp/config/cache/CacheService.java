package com.example.erp.config.cache;

import com.example.erp.url.domain.UrlShortener;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class CacheService {
    private CacheManager inMemoryCacheManager;
    private CacheManager redisCacheManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private ObjectMapper objectMapper;

    public enum CacheName {URLS}

    public void addUrls(List<UrlShortener> urls, String key) {
        putValueInCache(CacheName.URLS.name(), key, urls);
    }

    public List<UrlShortener> getUrls(String key) {
        return getListValueFromCache(CacheName.URLS.name(), key);
    }

    public void removeUrls(String key) {
        removeFromCache(CacheService.CacheName.URLS.name(), key);
    }

    /**
     * Methods for Cache
     **/

    public void putValueInCache(String cacheName, String key, List<?> value) {
        Cache cache = cache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    public void putValueInCache(String cacheName, String key, Object value) {
        Cache cache = cache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    public Object getValueFromCache(String cacheName, String key) {
        Cache cache = cache(cacheName);
        if (cache != null) {
            return cache.get(key);
        }
        return null;
    }

//    public <T> List<T> getListValueFromCache(String cacheName, String key, Class<T> type) {
//        Cache cache = cache(cacheName);
//        if (cache != null) {
//            List<T> cachedList = cache.get(key, List.class);
//            if (cachedList != null && !cachedList.isEmpty() && type.isInstance(cachedList.get(0))) {
//                return cachedList;
//            }
//        }
//        return null;
//    }

    //TODO can be generic for all list type
    public List<UrlShortener> getListValueFromCache(String cacheName, String key) {
        Cache cache = cache(cacheName);
        if (cache != null) {
            List cachedList = cache.get(key, List.class);
            if (cachedList != null && !cachedList.isEmpty() ) {
                return cachedList;
            }
        }
        return null;
    }

    public void removeFromCache(String cacheName, String key) {
        Cache cache = cache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }

    private Cache cache(String cacheName) {
        return cacheManager().getCache(cacheName);

        //comment above and uncomment below code for redis cache
//        return redisCacheManager().getCache(cacheName);

    }

    private CacheManager cacheManager() {
        if (inMemoryCacheManager == null) {
            inMemoryCacheManager = new ConcurrentMapCacheManager();
        }
        return inMemoryCacheManager;
    }

    public CacheManager redisCacheManager() {
        if (redisCacheManager == null) {
            GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);
            RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofMinutes(10))
                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
            redisCacheManager = RedisCacheManager.builder(redisConnectionFactory)
                    .cacheDefaults(redisCacheConfiguration)
                    .build();
        }
        return redisCacheManager;
    }
}
