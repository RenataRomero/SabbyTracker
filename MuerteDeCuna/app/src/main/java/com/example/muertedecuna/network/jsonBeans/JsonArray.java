package com.example.muertedecuna.network.jsonBeans;

import java.util.List;

public class JsonArray {
    List<JsonValuesArray> data;

    public JsonArray(List<JsonValuesArray> data) {
        this.data = data;
    }

    public List<JsonValuesArray> getData() {
        return data;
    }

    public void setData(List<JsonValuesArray> data) {
        this.data = data;
    }
}
