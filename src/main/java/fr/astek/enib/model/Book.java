package fr.astek.enib.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private int id;
    private String title;
    private String author;
    private List<String> genre;
    private Date releaseDate;
    private float rating;
    private int sales;
}
