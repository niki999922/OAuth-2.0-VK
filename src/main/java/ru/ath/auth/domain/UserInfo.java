package ru.ath.auth.domain;

import java.util.List;

public class UserInfo {
    List<User> response;

    public UserInfo(List<User> response) {
        this.response = response;
    }

    public List<User> getResponse() {
        return response;
    }
}

