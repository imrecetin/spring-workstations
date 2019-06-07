package com.spring.redis.service;

import com.spring.redis.model.Post;
import com.spring.redis.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    private static final String CONTROLLED_PREFIX = "myPostPrefix_";

    public Post getPostByID(String id) {
        return new Post();
    }

    public List<Post> getTopPosts() {
        return Collections.emptyList();
    }

    public void updatePost(Post post) {

    }
    public void deletePost(String id) {
    }

    //Cache Key Generator
    public static String getCacheKey(String relevant){
        return CONTROLLED_PREFIX + relevant;
    }

    @Cacheable(cacheNames = "myServiceCache", key = "T(com.spring.redis.service.PostService).getCacheKey(#relevant)")
    public String getFromCache(String relevant) {
        return null;
    }

    @CachePut(cacheNames = "myServiceCache", key = "T(com.spring.redis.service.PostService).getCacheKey(#relevant)")
    public String populateCache(String relevant, String unrelevantTrackingId) {
        return "this is it again!";
    }

    @CacheEvict(cacheNames = "myServiceCache", key = "T(com.spring.redis.service.PostService).getCacheKey(#relevant)")
    public void removeFromCache(String relevant) {
    }
}
