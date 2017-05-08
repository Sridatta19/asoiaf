package com.example.service;

import com.example.dao.CharacterRepository;
import com.example.dao.HouseRepository;
import com.example.data.House;
import com.example.data.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by sridattap on 08/05/17.
 */
@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private HouseRepository houseRepository;

    public Flux<Character> findAllCharacters() {
        return characterRepository.findAll();
    }

    public Mono<Character> findById(String characterId) {
        return characterRepository.findById(characterId);
    }

    public Mono<House> addCharacterToHouse(String houseId, String content){
        Mono<Character> character = Mono.just(new Character(houseId, content)).flatMap(characterRepository::save);
        Mono<House> house = houseRepository.findById(houseId);
        return Flux.zip(character, house).map(a -> a.getT2().addCharacter(a.getT1())).flatMap(houseRepository::save).singleOrEmpty();
    }

    public Mono<Character> likeCharacter(String id) {
        return characterRepository.findById(id).doOnNext(Character::like).flatMap(characterRepository::save);
    }

    public Mono<Void> deleteCharacter(String objectId){
        return characterRepository.deleteById(objectId);
    }

    public Flux<Character> findByHouseId(String houseId) {
        return characterRepository.findAll().filter(l -> l.getHouseId().equals(houseId));
    }
}
