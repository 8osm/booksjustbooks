package com.osm8.booksjustbooks.controllers;

import com.osm8.booksjustbooks.models.BookModel;
import com.osm8.booksjustbooks.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/admin")
    public String adminPanel(@CookieValue(value = "loggedInAs",defaultValue = "user") String loggedInAs , Model model){
        if(loggedInAs == null|| !loggedInAs.equals("admin")){
            return "redirect:/login";
        }
        Iterable<BookModel> books = bookRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("list_title", "Admin Panel");
        model.addAttribute("admin" , true);
        return "booklist";
    }

    @GetMapping("/create")
    public String bookCreatePage(@CookieValue(value = "loggedInAs", defaultValue = "user") String loggedInAs, Model model){
        if (!loggedInAs.equals("admin")){ return "redirect:/"; }
        return "book_create";
    }

    @PostMapping("/book/create")
    public String bookCreate(@RequestParam String title, @RequestParam String authors, @RequestParam String genres, @RequestParam String description, @RequestParam int price,@CookieValue(value = "loggedInAs", defaultValue = "user") String loggedInAs,Model model){
        if (!loggedInAs.equals("admin")){ return "redirect:/"; }
        BookModel newBook = new BookModel(title, authors, genres, description, price);
        bookRepository.save(newBook);
        return "redirect:/";
    }

    @PostMapping("/book/edit")
    public String bookEdit(@RequestParam String title, @RequestParam String authors, @RequestParam String genres, @RequestParam String description, @RequestParam int price,@RequestParam Long bookId,@CookieValue(value = "loggedInAs",defaultValue = "user") String loggedInAs,Model model){
        if (!loggedInAs.equals("admin") || !bookRepository.existsById(bookId)){ return "redirect:/"; }
        BookModel bookToEdit = bookRepository.findById(bookId).orElseThrow();
        bookToEdit.setTitle(title);
        bookToEdit.setAuthors(authors);
        bookToEdit.setGenres(genres);
        bookToEdit.setDescription(description);
        bookToEdit.setPrice(price);
        bookRepository.save(bookToEdit);
        return "redirect:/book?bookFullId=" + bookId;
    }
}
