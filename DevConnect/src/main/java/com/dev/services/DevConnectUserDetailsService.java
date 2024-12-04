package com.dev.services;

import com.dev.Dao.UserDao;
import com.dev.Model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
		User user=userDAO.findById(username).orElse(null);
		if(user==null) {
			System.out.println("user not found");
			throw new UsernameNotFoundException("There is something wrong with your credential");
		}
		UserPrincipal userPrincipal=new UserPrincipal(user);
		return userPrincipal;
	}

}
