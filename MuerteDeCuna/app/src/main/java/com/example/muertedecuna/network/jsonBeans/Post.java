package com.example.muertedecuna.network.jsonBeans;

public class Post {

    private String email;
    private String password;

    private int idUser;
    private String title;
    private String body;

    public Post(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Post(int idUser, String title, String body) {
        this.idUser = idUser;
        this.title = title;
        this.body = body;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
