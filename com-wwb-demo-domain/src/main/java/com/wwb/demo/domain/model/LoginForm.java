package com.wwb.demo.domain.model;

import java.io.Serializable;

/**
 * Created by Intery on 2016/5/15.
 */
public class LoginForm implements Serializable{
    private String username;
    private String password;
    private String validationCode;

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

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
}
