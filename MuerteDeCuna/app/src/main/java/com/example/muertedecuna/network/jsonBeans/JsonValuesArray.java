package com.example.muertedecuna.network.jsonBeans;

import java.util.List;

public class JsonValuesArray {

    List<JsonValues> data;

    @Override
    public String toString() {
        return "JsonValuesArray{" +
                "data=" + data +
                '}';
    }

    public JsonValuesArray(List<JsonValues> data) {
        this.data = data;
    }

    public List<JsonValues> getData() {
        return data;
    }

    public void setData(List<JsonValues> data) {
        this.data = data;
    }


}
