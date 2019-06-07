package com.spring.redis.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String image;
    private int shares;
    private Author author;
}