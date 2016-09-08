/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dto;

/**
 *
 * @author apprentice
 */
public class User {
    private int userId;
    private String userName;
    private String password;
    private String email;
    private int PermissionsId;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPermissionsId() {
        return PermissionsId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setPermissionsId(int PermissionsId) {
        this.PermissionsId = PermissionsId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
