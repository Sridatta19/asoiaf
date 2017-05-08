package com.example.dao;

import com.example.data.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by sridattap on 08/05/17.
 */
public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
}
