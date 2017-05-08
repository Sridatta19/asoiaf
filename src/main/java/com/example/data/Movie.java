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
@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Movie {

    @Id
    private String id;

    private String title;

    private String genre;
}
