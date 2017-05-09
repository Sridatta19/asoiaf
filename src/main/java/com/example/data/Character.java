package com.example.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sridattap on 08/05/17.
 */
@NoArgsConstructor
@AllArgsConstructor
@Document
@Data
@ToString
public class Character {

    @Id
    private String id;

    private String name;

    private String quote;

    private String avatar;

    private int likes;

    private String houseId;

    public Character(String houseId, String name, String quote){
        this.name = name;
        this.houseId = houseId;
        this.quote = quote;
    }

    public void like(){
        ++this.likes;
    }
}
