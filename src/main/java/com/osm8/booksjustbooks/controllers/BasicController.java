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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BasicController {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/")
    public String bookListPage(@CookieValue(value = "loggedInAs", defaultValue = "user") String loggedInAs,Model model){
        Iterable<BookModel> books = bookRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("list_title", "Our Books");
        if (loggedInAs.equals("admin")){
            model.addAttribute("admin" , true);
            model.addAttribute("list_title", "Admin Panel");
        }else{
            model.addAttribute("list_title", "Our Books");
        }
        return "booklist";
    }

    @GetMapping("/book")
    public String bookControl(@RequestParam(required = false) Long bookFullId, @RequestParam(required = false) Long bookDeleteId, @RequestParam(required = false) Long bookEditId, @CookieValue(value = "loggedInAs", defaultValue = "false") String loggedInAs, Model model){
        if (bookEditId != null && bookFullId != null && bookDeleteId != null){
            return "redirect:/";
        }else if(bookEditId !=null){
            if(bookRepository.existsById(bookEditId) && loggedInAs.equals("admin")) {
                model.addAttribute("book", bookRepository.findById(bookEditId).get());
                return "book_edit";
            }else {return "redirect:/";}
        }else if(bookFullId != null){
            if(bookRepository.existsById(bookFullId)) {
                model.addAttribute("book", bookRepository.findById(bookFullId).get());
                return "book_full";
            }else {return "redirect:/";}
        }else if(bookDeleteId != null){
            if(bookRepository.existsById(bookDeleteId) && loggedInAs.equals("admin")) {
                bookRepository.delete(bookRepository.findById(bookDeleteId).get());
                return "redirect:/";
            }else {return "redirect:/";}
        }
        return "redirect:/";
    }

    @GetMapping("/result")
    public String orderFailed(@RequestParam String result, Model model){

        switch (result){
            default:
                model.addAttribute("message", "This is a blank result message");
                return "successmessage";
            case "login_failure":
                model.addAttribute("message", "Incorrect username or password!");
                return "failuremessage";
            case "login_success":
                model.addAttribute("message", "Logged in succesfully!");
                return "successmessage";
            case "order_failure":
                model.addAttribute("message", "Your order has not been placed, please check your input");
                return "failuremessage";
            case "order_success":
                model.addAttribute("message", "Your order has been placed!");
                return "successmessage";
        }
    }

    @GetMapping("/login")
    public String loginPage(@CookieValue(value = "loggedInAs", defaultValue = "user") String loggedInAs,Model model){
        if (loggedInAs.equals("admin")){
            return "redirect:/";
        }
        return "login";
    }


    @PostMapping("/login")
    public String loginPage(@RequestParam String username, @RequestParam String password, HttpServletResponse response,Model model){
        if(username.equals("admin") && password.equals("admin")){
            Cookie cookie = new Cookie("loggedInAs", "admin");
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/result?result=login_success";

        } else{
            return "redirect:/result?result=login_failure";
        }
    }
}
