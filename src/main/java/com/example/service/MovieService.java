package com.example.service;

import com.example.dao.MovieRepository;
import com.example.data.Movie;
import com.example.data.MovieEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by sridattap on 08/05/17.
 */
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public Flux<MovieEvent> streamStreams(Movie movie){
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<MovieEvent> events = Flux.fromStream(Stream.generate(() -> new MovieEvent(movie, new Date(), randomUser())));
        return Flux.zip(interval, events).map(Tuple2::getT2);
    }

    private String randomUser(){
        String[] genres = new String[]{"Sri", "Datta", "Pasum"};
        return genres[new Random().nextInt(genres.length)];
    }

    public Flux<Movie> all(){
        return movieRepository.findAll();
    }

    public Mono<Movie> byId(String id){
        return movieRepository.findById(id);
    }
}
