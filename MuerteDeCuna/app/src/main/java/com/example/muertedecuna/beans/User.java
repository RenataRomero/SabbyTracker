package com.example.muertedecuna.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String email;
    private String password;
    private boolean isLogged;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isLogged=" + isLogged +
                '}';
    }

    protected User(Parcel in) {
        email = in.readString();
        password = in.readString();
        isLogged = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeByte((byte) (isLogged ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String user) {
        this.email = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}