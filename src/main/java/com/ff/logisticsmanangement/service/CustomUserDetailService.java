package com.ff.logisticsmanangement.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ff.logisticsmanangement.dao.UserRepository;
import com.ff.logisticsmanangement.entity.User;
import com.ff.logisticsmanangement.util.Role;
/*
 * customer UserDetailService class to implement the inbuilt methods
 * 
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	/*
	 * the username is taken from basic auth and is verified in the database
	 * 
	 * @returns by calling buildUserDetails()
	 * 
	 * @see buildUserDetails()
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		return buildUserDetails(user);
	}
	
	/*
	 * here role based Authorities is assigned 
	 * 
	 * @returns UserDetails object by providing userName, encoded password and role
	 */
	public UserDetails buildUserDetails(User user) {
		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		Role role= user.getUserRole();
		
		authorities.add(new SimpleGrantedAuthority(role.toString()));
		return new org.springframework.security.core.userdetails.User(user.getUserName(), new BCryptPasswordEncoder().encode(user.getPassword()), authorities);
	}
}
