package com.infoshareacademy;

public class User {
    private String name;
    private String surname;
    private Integer id;
    private Credentials credentials;

    public User(String name, String surname, Integer id, Credentials credentials) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.credentials = credentials;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getId() {
        return id;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
