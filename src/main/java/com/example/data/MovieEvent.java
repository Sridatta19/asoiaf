package com.example.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created by sridattap on 08/05/17.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MovieEvent {

    private Movie movie;

    private Date when;

    private String user;
}
