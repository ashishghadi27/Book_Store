package com.example.bookstore.Model;

public class UserReg {

    private String name, email;

    public UserReg(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserReg(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
