package org.mtdev.regataiades.dao.impls;

import org.hibernate.criterion.Restrictions;
import org.mtdev.regataiades.dao.interfaces.UserDao;
import org.mtdev.regataiades.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends EntityDaoImpl<User> implements UserDao {

	public User findUserByLogin(String pLogin) {
		return this.findItemByCriteria(this.getGenericCriteria().add(
				Restrictions.eq("login", pLogin)));
	}

}
