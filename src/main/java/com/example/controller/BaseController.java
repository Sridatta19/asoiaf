package com.example.controller;

import com.example.data.Character;
import com.example.data.House;
import com.example.data.Movie;
import com.example.data.MovieEvent;
import com.example.service.CharacterService;
import com.example.service.HouseService;
import com.example.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Created by sridattap on 08/05/17.
 */
@RestController
public class BaseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private MovieService movieService;

    @Bean
    RouterFunction routes(){
        return RouterFunctions.route(GET("/movies"), getAllMovies)
                .andRoute(GET("/movies/{id}"), getMovieById)
                .andRoute(GET("/movies/{id}/events"), getMovieAndEvents)
                .andRoute(GET("/houses"), getAllHouses)
                .andRoute(GET("/houses/{id}"), getHouseById)
                .andRoute(POST("/houses/addHouse"), addHouse)
                .andRoute(POST("/houses/deleteHouse/{id}"), deleteHouse)
                .andRoute(GET("/houses/characters/{id}"), getCharactersByHouse)
                .andRoute(GET("/characters"), getAllCharacters)
                .andRoute(GET("/characters/{id}"), getCharacterById)
                .andRoute(POST("/characters/addCharacter"), addCharacter)
                .andRoute(POST("/characters/likeCharacter/{id}"), likeCharacter)
                ;
    }

    private HandlerFunction<ServerResponse> getMovieAndEvents = request -> ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(movieService.byId(request.pathVariable("id")).flatMapMany(movieService::streamStreams), MovieEvent.class);

    private HandlerFunction<ServerResponse> getAllMovies = request -> ok().body(movieService.all(), Movie.class);

    private HandlerFunction<ServerResponse> getMovieById = request -> ok().body(movieService.byId(request.pathVariable("id")), Movie.class);

    private HandlerFunction<ServerResponse> getAllHouses = request -> ok().body(houseService.findAllHouses(), House.class);

    private HandlerFunction<ServerResponse> getCharactersByHouse = request -> ok().body(characterService.findByHouseId(request.pathVariable("id")), Character.class);

    private HandlerFunction<ServerResponse> getHouseById = request -> ok().body(houseService.findById(request.pathVariable("id")), House.class);

    private HandlerFunction<ServerResponse> deleteHouse = request -> ok().body(houseService.deleteHouse(request.pathVariable("id")), String.class);

    private HandlerFunction<ServerResponse> addHouse = request -> ok().body(houseService.addHouse(request.queryParams("title").get(0)), House.class);

    private HandlerFunction<ServerResponse> addCharacter = request -> ok().body(addACharacter(request), House.class);

    private HandlerFunction<ServerResponse> likeCharacter = request -> ok().body(characterService.likeCharacter(request.pathVariable("id")), Character.class);

    private Mono<House> addACharacter(ServerRequest request) {
        String houseId = request.queryParams("houseId").get(0);
        String content = request.queryParams("content").get(0);
        return characterService.addCharacterToHouse(houseId, content);
    }

    private HandlerFunction<ServerResponse> getAllCharacters = request -> ok().body(characterService.findAllCharacters(), Character.class);

    private HandlerFunction<ServerResponse> getCharacterById = request -> ok().body(characterService.findById(request.pathVariable("id")), Character.class);
}
