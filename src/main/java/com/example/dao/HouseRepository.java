package com.example.dao;

import com.example.data.House;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by sridattap on 08/05/17.
 */
public interface HouseRepository extends ReactiveMongoRepository<House, String> {
}
