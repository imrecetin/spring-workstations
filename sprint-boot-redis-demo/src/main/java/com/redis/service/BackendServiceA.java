package com.redis.service;

import com.redis.service.dto.ResponseA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BackendServiceA {
    /**
     * !!! Never ever call a method annotated with @Cacheable or any of the other methods from within the same class!!!
     * The reason is that Spring proxies the access to this methods to make the Cache Abstraction work.
     * When you call it within the same class this Proxy mechanic is not kicking in.
     * By this you basically bypass your Cache and make it non-effective.
     * Your cache will never get used from within the same class.
     * **/
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendServiceA.class);

    public ResponseA getBy(final String value) {
        return new ResponseA(value);
    }

    @Cacheable(cacheNames = "myCache", key = "'myPrefix_'.concat(#relevant)")
    public String cacheThis(String relevant, String unrelevantTrackingId){
        LOGGER.info("Returning NOT from cache. Tracking: {}!", unrelevantTrackingId);
        return "this Is it";
    }

    @CacheEvict(cacheNames = "myCache", key = "'myPrefix_'.concat(#relevant)")
    public void forgetAboutThis(String relevant){
        LOGGER.info("Forgetting everything about this '{}'!", relevant);
    }

    @Cacheable(cacheNames = "myCache", key = "T(com.redis.service.BackendServiceA).getCacheKey(#relevant)")
    public String getFromCache(String relevant) {
        return null;
    }

    @CachePut(cacheNames = "myCache", key = "T(com.redis.service.BackendServiceA).getCacheKey(#relevant)")
    public String populateCache(String relevant, String unrelevantTrackingId) {
        return "this is it again!";
    }

    @CacheEvict(cacheNames = "myCache", key = "T(com.redis.service.BackendServiceA).getCacheKey(#relevant)")
    public void removeFromCache(String relevant) {
    }

    private static final String CONTROLLED_PREFIX = "myPrefix_";

    public static String getCacheKey(String relevant){
        return CONTROLLED_PREFIX + relevant;
    }
}
