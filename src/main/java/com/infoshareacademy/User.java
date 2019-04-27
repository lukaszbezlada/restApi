package com.infoshareacademy;

public class User {

    private String name;

    private String surname;

    private int id;

    private Credentials credentials;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public User(String name, String surname, int id, Credentials credentials) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.credentials = credentials;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}