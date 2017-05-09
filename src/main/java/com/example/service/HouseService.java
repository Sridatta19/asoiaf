package com.example.service;

import com.example.dao.CharacterRepository;
import com.example.dao.HouseRepository;
import com.example.data.Character;
import com.example.data.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        characterRepository.findAll().filter(c -> c.getHouseId().equals(houseId)).doOnNext(characterRepository::delete).subscribe();
        return houseRepository.deleteById(houseId).map(m -> "Successfully Deleted");
    }
}
