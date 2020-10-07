package com.redis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.config.RedisConfigurationTestUtilities;
import com.redis.service.BackendServiceA;
import com.redis.service.dto.ResponseA;
import com.redis.service.dto.ResponseAUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Assert;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RedisConfigurationTestUtilities.class)
@AutoConfigureMockMvc
public class CacheableControllerAIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CacheableControllerA cacheableControllerA;

    @SpyBean
    private BackendServiceA backendA;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String LONG_TEST_VALUE = "this-input-is-longer-than-32-characters";
    private static final String TEST_VALUE = "test-value";
    private static final String NEW_TEST_VALUE = "new-test-value";
    private static final String REDIS_CACHE_NAME = "sample-redis-cache";

    @BeforeEach
    public void beforeEach() {
        cacheableControllerA.evictCache();
    }

    @Test
    public void verifyControllerAReturnsCorrectValue() throws Exception {
        mockMvc.perform(get(String.format("/cacheableControllerA/unconditional/%s", TEST_VALUE)))
                .andExpect(status().isOk())
                .andExpect(content().json(String.format("{'value': '%s'}", TEST_VALUE)));
    }

    @Test
    public void verifyControllerAConditionalReturnsCorrectValue() throws Exception {
        mockMvc.perform(get(String.format("/cacheableControllerA/conditional/%s", TEST_VALUE)))
                .andExpect(status().isOk())
                .andExpect(content().json(String.format("{'value': '%s'}", TEST_VALUE)));
    }

    @Test
    public void verifyControllerUpdateAReturnsCorrectValue() throws Exception {
        final String requestAsJsonString = objectMapper.writeValueAsString(new ResponseAUpdate(NEW_TEST_VALUE));
        mockMvc.perform(post(String.format("/cacheableControllerA/unconditional/%s", TEST_VALUE))
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestAsJsonString))
                .andExpect(status().isOk())
                .andExpect(content().json(String.format("{'value': '%s'}", NEW_TEST_VALUE)));
    }

    @Test
    public void verifyRequestsCachedProperly() {
        ResponseA responseA = cacheableControllerA.backendA(TEST_VALUE);
        Assert.assertEquals(TEST_VALUE, responseA.getValue());
        responseA = cacheableControllerA.backendA(TEST_VALUE);
        Assert.assertEquals(TEST_VALUE, responseA.getValue());
        verify(backendA, times(1)).getBy(TEST_VALUE);
        Assert.assertEquals(TEST_VALUE, ((ResponseA) retrieveCachedResponse(TEST_VALUE)).getValue());
    }

    @Test
    public void verifyRequestsCachedProperlyInBackendAConditional() {
        ResponseA responseA = cacheableControllerA.backendAConditional(TEST_VALUE);
        Assert.assertEquals(TEST_VALUE, responseA.getValue());
        responseA = cacheableControllerA.backendAConditional(TEST_VALUE);
        Assert.assertEquals(TEST_VALUE, responseA.getValue());
        verify(backendA, times(1)).getBy(TEST_VALUE);
        Assert.assertEquals(TEST_VALUE, ((ResponseA) retrieveCachedResponse(TEST_VALUE)).getValue());
    }

    @Test
    public void verifyRequestsNotCachedWhenLongInputGivenToBackendAConditional() {
        ResponseA responseA = cacheableControllerA.backendAConditional(LONG_TEST_VALUE);
        Assert.assertEquals(LONG_TEST_VALUE, responseA.getValue());
        responseA = cacheableControllerA.backendAConditional(LONG_TEST_VALUE);
        Assert.assertEquals(LONG_TEST_VALUE, responseA.getValue());
        verify(backendA, times(2)).getBy(LONG_TEST_VALUE);
        Assert.assertNull(retrieveCachedResponse(LONG_TEST_VALUE));
    }

    @Test
    public void verifyUpdateEvictsCache() {
        ResponseA responseA = cacheableControllerA.backendA(TEST_VALUE);
        Assert.assertEquals(TEST_VALUE, responseA.getValue());
        ResponseA newResponseA = cacheableControllerA.backendAUpdate(TEST_VALUE, new ResponseAUpdate(NEW_TEST_VALUE));
        Assert.assertEquals(NEW_TEST_VALUE, newResponseA.getValue());
        Assert.assertNull(retrieveCachedResponse(TEST_VALUE));
        responseA = cacheableControllerA.backendA(TEST_VALUE);
        Assert.assertEquals(TEST_VALUE, responseA.getValue());
        Assert.assertEquals(TEST_VALUE, ((ResponseA) retrieveCachedResponse(TEST_VALUE)).getValue());
        verify(backendA, times(3)).getBy(TEST_VALUE);
    }

    private Object retrieveCachedResponse(final String key) {
        return redisTemplate.opsForValue().get(String.format("%s::%s", REDIS_CACHE_NAME, key));
    }

}
