package com.grupo3.confido.model;

public class User {

    /*======== Variables ========*/
    public String firstName;
    public String lastName;
    public String email;

    /*======== Empty Constructor  ========*/
    public User() {}

    /*======== Constructor ========*/
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}