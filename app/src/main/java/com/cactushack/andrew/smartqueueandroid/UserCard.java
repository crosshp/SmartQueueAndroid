package com.cactushack.andrew.smartqueueandroid;

/**
 * Created by Andrew on 14.05.2016.
 */
public class UserCard {
    private String name;
    private String dateLess;

    public UserCard() {
    }

    public UserCard(String name, String dateLess) {
        this.name = name;
        this.dateLess = dateLess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateLess() {
        return dateLess;
    }

    public void setDateLess(String dateLess) {
        this.dateLess = dateLess;
    }
}
