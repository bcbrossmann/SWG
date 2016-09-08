/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.service;

/**
 *
 * @author apprentice
 */
import com.seabee.snapdragon.dao.UserDao;
import com.seabee.snapdragon.dto.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;	

	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		
		com.seabee.snapdragon.model.User domainUser = userDao.getUser(login);
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new User(
				domainUser.getLogin(), 
				domainUser.getPassword(), 
				enabled, 
				accountNonExpired, 
				credentialsNonExpired, 
				accountNonLocked,
				getAuthorities(domainUser.getRole().getId())
		);
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	public List<String> getRoles(Integer role) {

		List<String> roles = new ArrayList<String>();

		if (role.intValue() == 1) {
                        roles.add("POSTER");
			roles.add("MODERATOR");
			roles.add("ADMIN");
		} else if (role.intValue() == 2) {
                        roles.add("POSTER");
                        roles.add("MODERATOR");
		} else if (role.intValue() == 3) {
                        roles.add("POSTER");
                }
		return roles;
	}
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities;
            authorities = new ArrayList<GrantedAuthority>();
		
            roles.stream().forEach((role) -> {
                authorities.add(new SimpleGrantedAuthority(role));
            });
		return authorities;
	}

}