package com.example.fuadmaska.myfeature.Model;

import java.io.Serializable;

public class UserLogin implements Serializable {
    private String user;
    private String pass;

    public UserLogin() {
    }

    public UserLogin(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
