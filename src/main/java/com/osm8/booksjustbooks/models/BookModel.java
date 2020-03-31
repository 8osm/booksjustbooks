package com.osm8.booksjustbooks.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public BookModel() {
    }

    public BookModel(String title, String authors, String genres, String description, int price){
        this.title = title;
        this.authors = authors;
        this.genres = genres;
        this.description = description;
        this.price = price;
    }

    String title, authors, genres, description;
    int price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public ArrayList<String> getAuthorList(){
        String[] authorsParsed = authors.split(",");
        ArrayList<String> authorsParsedList = new ArrayList<>();
        for (String genre: authorsParsed) {
            authorsParsedList.add(genre.strip());
        }
        return authorsParsedList;
    }

    public Long getId() {
        return id;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getGenres() {
        return genres;
    }

    public ArrayList<String> getGenresList(){
        String[] genresParsed = genres.split(",");
        ArrayList<String> genresParsedList = new ArrayList<>();
        for (String genre: genresParsed) {
            genresParsedList.add(genre.strip());
        }
        return genresParsedList;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
