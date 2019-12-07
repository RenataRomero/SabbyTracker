package com.example.muertedecuna.network.jsonBeans;

public class JsonTemp {

    private String variable;
    private String date;
    private String time;

    public JsonTemp(String variable, String date, String time) {
        this.variable = variable;
        this.date = date;
        this.time = time;
    }

    public String getVariable() {
        return variable;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
