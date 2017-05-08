package com.example.dao;

import com.example.data.Character;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by sridattap on 08/05/17.
 */
public interface CharacterRepository extends ReactiveMongoRepository<Character, String> {
}
