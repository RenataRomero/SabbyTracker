package com.example.muertedecuna.network.jsonBeans;

public class JsonLogin {
    String message;
    int httpStatus;

    public JsonLogin(String message, int httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
