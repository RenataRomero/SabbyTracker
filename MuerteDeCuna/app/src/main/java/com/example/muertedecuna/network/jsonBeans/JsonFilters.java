package com.example.muertedecuna.network.jsonBeans;

public class JsonFilters {
    String startDay, startMonth, startYear, endDay, endMonth, endYear;
    String type;

    @Override
    public String toString() {
        return "JsonFilters{" +
                "startDay='" + startDay + '\'' +
                ", startMonth='" + startMonth + '\'' +
                ", startYear='" + startYear + '\'' +
                ", endDay='" + endDay + '\'' +
                ", endMonth='" + endMonth + '\'' +
                ", endYear='" + endYear + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public JsonFilters(String startDay, String startMonth, String startYear, String endDay, String endMonth, String endYear, String type) {
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endDay = endDay;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.type = type;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
