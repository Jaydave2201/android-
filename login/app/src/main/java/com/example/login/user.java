package com.example.login;

public class user {

    private String name;
    private int id ;
    private String email;
    private String number;

    public user(String name, int id, String email, String number) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.number = number;
    }

    public user() {


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
