/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.service;

import com.seabee.snapdragon.model.User;

/**
 *
 * @author apprentice
 */
public interface UserService {
	
	public User getUser(String login);

}
