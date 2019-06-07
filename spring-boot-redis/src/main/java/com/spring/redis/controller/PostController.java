package com.spring.redis.controller;

import com.spring.redis.exception.PostNotFoundException;
import com.spring.redis.model.Post;
import com.spring.redis.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redis/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Cacheable(value = "post-single", key = "#id", unless = "#result.shares < 500")
    @GetMapping("/{id}")
    public Post getPostByID(@PathVariable String id) throws PostNotFoundException {
        return postService.getPostByID(id);
    }

    @Cacheable(value = "post-top")
    @GetMapping("/top")
    public List<Post> getTopPosts() {
        return postService.getTopPosts();
    }

    @CachePut(value = "post-single", key = "#post.id")
    @PutMapping
    public Post updatePostByID(@RequestBody Post post) throws PostNotFoundException {
        postService.updatePost(post);
        return post;
    }

    @CacheEvict(value = "post-single", key = "#id")
    @DeleteMapping("/{id}")
    public void deletePostByID(@PathVariable String id) throws PostNotFoundException {
        postService.deletePost(id);
    }

    @CacheEvict(value = "post-top")
    @GetMapping("/top/evict")
    public void evictTopPosts() {
    }


}
