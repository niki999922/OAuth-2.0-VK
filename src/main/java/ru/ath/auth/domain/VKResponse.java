package ru.ath.auth.domain;

import java.io.Serializable;

public class VKResponse {
    final String access_token;
    final String expires_in;
    final String user_id;

    public VKResponse(String access_token, String expires_in, String user_id) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.user_id = user_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public String getUser_id() {
        return user_id;
    }
}
