package com.osm8.booksjustbooks.controllers;

import com.osm8.booksjustbooks.models.OrderModel;
import com.osm8.booksjustbooks.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookOrderController {

    @Autowired
    OrderRepository orderRepository;

    @PostMapping("/book/order")
    public String orderBook(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String address, @RequestParam String quantity, Model model){
        if(firstName.equals("") || lastName.equals("") || address.equals("") || quantity.equals("")){
            return "redirect:/result?result=order_failure";
        }
        OrderModel order = new OrderModel(firstName, lastName, address, Integer.parseInt(quantity));
        orderRepository.save(order);
        return "redirect:/result?result=order_success";
    }
}
