package com.mmt.enterprise.support;

import java.util.ArrayList;
import java.util.List;

import com.mmt.enterprise.entity.EnterpriseUser;
import com.mmt.enterprise.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private EnterpriseService enterpriseService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("登录用户为："+ username);
		EnterpriseUser eUser = enterpriseService.getEnterpriseUserByName(username);
		if(null == eUser) {
			throw new UsernameNotFoundException(username);
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		System.out.println("登录用户为："+ eUser.getName());
		return new User(eUser.getName(), eUser.getPassword(), authorities);
	}
}
