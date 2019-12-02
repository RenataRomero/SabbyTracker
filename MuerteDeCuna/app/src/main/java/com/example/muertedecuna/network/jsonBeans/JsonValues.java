package com.example.muertedecuna.network.jsonBeans;

import java.io.Serializable;

public class JsonValues implements Serializable {

    String _id, variable, day, month, year;

    public JsonValues(String _id, String variable, String day, String month, String year) {
        this._id = _id;
        this.variable = variable;
        this.day = day;
        this.month = month;
        this.year = year;
    }


    @Override
    public String toString() {
        return "JsonValues{" +
                "_id='" + _id + '\'' +
                ", variable='" + variable + '\'' +
                ", day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
