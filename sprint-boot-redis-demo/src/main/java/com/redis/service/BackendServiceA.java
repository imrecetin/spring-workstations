package com.redis.service;

import com.redis.service.dto.ResponseA;
import org.springframework.stereotype.Service;

@Service
public class BackendServiceA {

    public ResponseA getBy(final String value) {
        return new ResponseA(value);
    }
}
