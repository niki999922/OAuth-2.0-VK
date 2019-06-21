package ru.ath.auth.domain;

import java.io.Serializable;
import java.util.List;

public class VKFriendsResponseIn {
    Long count;
    List<User> items;

    public VKFriendsResponseIn(Long count, List<User> items) {
        this.count = count;
        this.items = items;
    }

    public Long getCount() {
        return count;
    }

    public List<User> getItems() {
        return items;
    }
}
