package ru.ath.auth.domain;

import java.io.Serializable;
import java.util.List;

public class User {
    String id;
    String first_name;
    String last_name;
    String photo_100;
    String online;

    public User(String id, String first_name, String last_name, String photo_100, String online) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.photo_100 = photo_100;
        this.online = online;
    }

    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhoto_100() {
        return photo_100;
    }

    public String getOnline() {
        return online;
    }
}