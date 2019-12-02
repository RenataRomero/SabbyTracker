package com.example.muertedecuna.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Temp implements Parcelable {
    private String variable;
    private String day;
    private String month;
    private String year;


    public Temp() {
    }

    @Override
    public String toString() {
        return "User{" +
                "variable='" + variable + '\'' +
                ", day='" + day + '\'' +
                ", month='" + month +
                ", year='"+ year + '\'' +
                '}';
    }

    protected Temp(Parcel in) {
        variable = in.readString();
        day = in.readString();
        month = in.readString();
        year = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(variable);
        dest.writeString(day);
        dest.writeString(month);
        dest.writeString(year);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Temp> CREATOR = new Parcelable.Creator<Temp>() {
        @Override
        public Temp createFromParcel(Parcel in) {
            return new Temp(in);
        }

        @Override
        public Temp[] newArray(int size) {
            return new Temp[size];
        }
    };

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