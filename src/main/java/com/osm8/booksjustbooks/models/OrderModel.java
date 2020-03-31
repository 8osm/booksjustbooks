package com.osm8.booksjustbooks.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String first_name, last_name, address;
    private int quantity;

    public String getFirst_name() {
        return first_name;
    }

    public OrderModel() {
    }

    public OrderModel(String first_name, String last_name, String address, int quantity){
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.quantity = quantity;
    }

    public void setFirst_name(String firstName) {
        this.first_name = firstName;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String lastName) {
        this.last_name = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
