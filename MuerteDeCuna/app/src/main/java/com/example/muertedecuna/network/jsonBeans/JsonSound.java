package com.example.muertedecuna.network.jsonBeans;

public class JsonSound {

    private String variable;
    private String date;
    private String time;

    public JsonSound(String variable, String date, String time) {
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
