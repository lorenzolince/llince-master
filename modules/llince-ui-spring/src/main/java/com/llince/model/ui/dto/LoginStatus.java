/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.ui.dto;

/**
 *
 * @author loren
 */
public class LoginStatus implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private boolean loggedIn;
    private String username;
    private String token;
    private UserDto userDetail;

    public LoginStatus() {

    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDto userDetail) {
        this.userDetail = userDetail;
    }


}
