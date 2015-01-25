package org.mtdev.regataiades.dao.interfaces;

import org.mtdev.regataiades.model.User;

public interface UserDao extends EntityDao<User> {

	public User findUserByLogin(String pLogin);
}
