package io.methea.domain.configuration.user.dto;

import io.methea.domain.basebinder.BaseBinder;

public class UserLogin extends BaseBinder<UserLogin> {
    private static final long serialVersionUID = 6209391752160194710L;
    private String username;
    private String password;

    public UserLogin(){}

    public UserLogin(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
