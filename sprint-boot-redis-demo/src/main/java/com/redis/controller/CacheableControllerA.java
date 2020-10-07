package com.redis.controller;

import com.redis.service.BackendServiceA;
import com.redis.service.dto.ResponseA;
import com.redis.service.dto.ResponseAUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cacheableControllerA")
public class CacheableControllerA {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheableControllerA.class);

    @Autowired
    private BackendServiceA backendServiceA;

    @CacheEvict(value = "sample-redis-cache", allEntries = true)
    public void evictCache() {
        LOGGER.debug("all entries have been evicted");
    }

    @GetMapping(value = "/unconditional/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Cacheable(cacheNames = "sample-redis-cache", key = "#value")
    public ResponseA backendA(@PathVariable final String value) {
        LOGGER.debug("no entry found for {}", value);
        return backendServiceA.getBy(value);
    }

    @GetMapping(value = "/conditional/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Cacheable(cacheNames = "sample-redis-cache", key = "#value", condition = "#value.length() < 32")
    public ResponseA backendAConditional(@PathVariable final String value) {
        LOGGER.debug("no entry found for {} - having less than 32 character", value);
        return backendServiceA.getBy(value);
    }

    @PostMapping(value = "/unconditional/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CacheEvict(cacheNames = "sample-redis-cache", key = "#value")
    public ResponseA backendAUpdate(@PathVariable final String value, @RequestBody final ResponseAUpdate updateRequest) {
        final ResponseA result = backendServiceA.getBy(value);
        result.setValue(updateRequest.getNewValue());
        return result;
    }

}
