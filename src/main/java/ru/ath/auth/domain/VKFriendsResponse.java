package ru.ath.auth.domain;

import java.io.Serializable;
import java.util.List;

public class VKFriendsResponse {
    VKFriendsResponseIn response;

    public VKFriendsResponse(VKFriendsResponseIn response) {
        this.response = response;
    }

    public VKFriendsResponseIn getResponse() {
        return response;
    }
}
