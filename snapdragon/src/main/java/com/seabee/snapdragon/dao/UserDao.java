/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dao;

import com.seabee.snapdragon.dto.User;

/**
 *
 * @author apprentice
 */
public interface UserDao {
    public void addUser(User user);
    public void removeUser(User user);
    public User getUser(String login);
    
}
