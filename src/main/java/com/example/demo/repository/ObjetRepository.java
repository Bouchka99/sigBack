package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Objet;

@Repository
public interface ObjetRepository extends MongoRepository <Objet,Integer>{

}
