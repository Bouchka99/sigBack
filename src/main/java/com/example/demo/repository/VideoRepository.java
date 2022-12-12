package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Video;

@Repository
public interface VideoRepository extends MongoRepository<Video,Integer> {
	

}
