package com.example.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sridattap on 08/05/17.
 */
@NoArgsConstructor
@AllArgsConstructor
@Document
@Data
@ToString
public class House {

    @Id
    private String id;

    private String title;

    private String sigilImage;

    private List<Character> characters = new ArrayList<>();

    public House(String houseTitle){
        this.title = houseTitle;
    }

    public House addCharacter(Character character){
        this.characters.add(character);
        return this;
    }

}
