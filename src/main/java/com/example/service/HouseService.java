package com.example.service;

import com.example.dao.CharacterRepository;
import com.example.dao.HouseRepository;
import com.example.data.Character;
import com.example.data.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 * Created by sridattap on 08/05/17.
 */
@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private CharacterRepository characterRepository;

    public Flux<House> findAllHouses() {
        return houseRepository.findAll();
    }

    public Mono<House> findById(String houseId) {
        return houseRepository.findById(houseId);
    }

    public Mono<House> addHouse(String title, String quote){
        return Mono.just(new House(title, quote)).flatMap(houseRepository::save);
    }

    public Mono<String> deleteHouse(String houseId){
        final Mono<Void> found = characterRepository.findAll()
                .filter(c -> {
                    return c.getHouseId().equals(houseId);
                })
                .flatMap(characterRepository::delete)
                .singleOrEmpty();
        Mono<Void> house = houseRepository.deleteById(houseId);
        return Flux.zip(found, house)
                   .singleOrEmpty()
                   .map(c -> "Successfully Deleted");
    }
}
