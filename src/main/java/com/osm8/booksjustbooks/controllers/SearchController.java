package com.osm8.booksjustbooks.controllers;

import com.osm8.booksjustbooks.models.BookModel;
import com.osm8.booksjustbooks.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class SearchController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/search")
    public String search(@RequestParam() String type, @RequestParam() String query, @CookieValue(value = "loggedInAs", defaultValue = "user") String loggedInAs, Model model){
        ArrayList<BookModel> foundBooks = new ArrayList<>();
        Iterable<BookModel> bookModels = bookRepository.findAll();
        if(type.equals("genre")){
            for (BookModel book: bookModels) {
                for(String genre: book.getGenresList()){
                    if(genre.toLowerCase().contains(query.toLowerCase())){
                        foundBooks.add(book);
                        break;
                    }
                }
            }
        }
        else if(type.equals("author")){
            for (BookModel book: bookModels) {
                for(String author: book.getAuthorList()){
                    if(author.toLowerCase().contains(query.toLowerCase())){
                        foundBooks.add(book);
                        break;
                    }

                }
            }
        }
        if (loggedInAs.equals("admin")){
            model.addAttribute("admin" , true);
        }
        model.addAttribute("books", foundBooks);
        model.addAttribute("list_title", "Search Results");
        model.addAttribute("type", type);
        model.addAttribute("query", query);
        return "booklist";
    }

    @GetMapping("/searchbyauthor")
    public String search(@RequestParam() String query, @CookieValue(value = "loggedInAs", defaultValue = "user") String loggedInAs, Model model){
        ArrayList<BookModel> foundBooks = new ArrayList<>();
        Iterable<BookModel> bookModels = bookRepository.findAll();
        for (BookModel book: bookModels) {
            for(String author: book.getAuthorList()){
                if(author.toLowerCase().contains(query.toLowerCase())){
                    foundBooks.add(book);
                    break;
                }
            }
        }

        if (loggedInAs.equals("admin")){
            model.addAttribute("admin" , true);
        }
        model.addAttribute("books", foundBooks);
        model.addAttribute("list_title", "Search Results");
        model.addAttribute("type", "author");
        model.addAttribute("query", query);
        return "booklist";
    }
    @GetMapping("/searchbygenre")
    public String searchByGenre(@RequestParam() String query, @CookieValue(value = "loggedInAs", defaultValue = "user") String loggedInAs, Model model){
        ArrayList<BookModel> foundBooks = new ArrayList<>();
        Iterable<BookModel> bookModels = bookRepository.findAll();
        for (BookModel book: bookModels) {
            for(String genre: book.getGenresList()){
                if(genre.toLowerCase().contains(query.toLowerCase())){
                    foundBooks.add(book);
                    break;
                }
            }
        }
        if (loggedInAs.equals("admin")){
            model.addAttribute("admin" , true);
        }
        model.addAttribute("books", foundBooks);
        model.addAttribute("list_title", "Search Results");
        model.addAttribute("type", "genre");
        model.addAttribute("query", query);
        return "booklist";
    }
}
