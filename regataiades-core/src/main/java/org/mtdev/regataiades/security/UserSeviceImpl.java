package org.mtdev.regataiades.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import org.mtdev.regataiades.dao.interfaces.UserDao;
import org.mtdev.regataiades.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Component
@Transactional
public class UserSeviceImpl implements UserDetailsService {

	@Autowired
	protected UserDao mUserDao;

	public UserDetails loadUserByUsername(String pUsername)
			throws UsernameNotFoundException {

		UserDetails lUser = null;

		if (!StringUtils.isEmpty(pUsername)) {

			User lDbUser = mUserDao.findUserByLogin(pUsername);

			if (lDbUser != null) {
				List<GrantedAuthority> lAuthorities = new ArrayList<GrantedAuthority>();
				lAuthorities.add(new SimpleGrantedAuthority("user"));

				lAuthorities.add(new SimpleGrantedAuthority(pUsername));

				lUser = new org.springframework.security.core.userdetails.User(
						lDbUser.getLogin(), lDbUser.getPassword(), lAuthorities);

			}
		}

		if (lUser == null) {
			throw new UsernameNotFoundException("Cannot find the user object");
		}

		return lUser;
	}

}
