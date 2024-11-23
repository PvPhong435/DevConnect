package com.dev.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.Dao.UserDao;
import com.dev.Model.User;

@Service
public class DevConnectUserDetailsService implements UserDetailsService {
	private UserDao userDAO;

	public DevConnectUserDetailsService(UserDao userDAO) {
		super();
		this.userDAO = userDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userDAO.findByUsername(username).orElse(null);
		if(user==null) {
			System.out.println("user not found");
			throw new UsernameNotFoundException("There is something wrong with your credential");
		}
		UserPrincipal userPrincipal=new UserPrincipal(user);
		return userPrincipal;
	}

}
