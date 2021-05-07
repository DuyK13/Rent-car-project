package com.iuh.rencar_project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iuh.rencar_project.entity.User;
import com.iuh.rencar_project.repository.UserRepository;

/**
 * @author Trần Thế Duy
 * @datetime May 1, 2021 3:50:23 PM
 * @version 0.1
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
		return UserDetailsImpl.build(user);
	}

}
