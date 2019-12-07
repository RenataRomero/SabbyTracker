package com.example.muertedecuna.network.jsonBeans;

public class JsonFilters {
    String dd, MM, yy;

    @Override
    public String toString() {
        return "JsonFilters{" +
                "dd='" + dd + '\'' +
                ", MM='" + MM + '\'' +
                ", yy='" + yy + '\'' +
                '}';
    }

    public JsonFilters(String dd, String MM, String yy) {
        this.dd = dd;
        this.MM = MM;
        this.yy = yy;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getMM() {
        return MM;
    }

    public void setMM(String MM) {
        this.MM = MM;
    }

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }
}
