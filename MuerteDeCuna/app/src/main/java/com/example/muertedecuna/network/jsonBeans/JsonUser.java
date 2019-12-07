package com.example.muertedecuna.network.jsonBeans;

public class JsonUser {

    private String email;
    private String password;

    public JsonUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
