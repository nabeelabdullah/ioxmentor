package com.ioxmentor.dto;

import com.ioxmentor.enums.LoginStatus;

/**
 * Created by nabeelabdullah on 09/10/17.
 */
public class LoginDTO {

    private String token;

    private LoginStatus loginMGS;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginStatus getLoginMGS() {
        return loginMGS;
    }

    public void setLoginMGS(LoginStatus loginMGS) {
        this.loginMGS = loginMGS;
    }
}
