package org.mtdev.regataiades.business;

import org.mtdev.regataiades.business.interfaces.DataProcessor;
import org.mtdev.regataiades.business.interfaces.UsersManager;
import org.mtdev.regataiades.dao.interfaces.UserDao;
import org.mtdev.regataiades.model.User;
import org.mtdev.regataiades.tools.Toolbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Component
@Transactional
public class UsersManagerImpl implements UsersManager {

	@Autowired
	protected UserDao mUserDao;

	@Autowired
	protected DataProcessor mDataProcessor;

	@Override
	public boolean checkUserExistence(String pLogin) {
		boolean lResult = false;

		try {
			if (!StringUtils.isEmpty(pLogin)) {
				User lUser = mUserDao.findUserByLogin(pLogin);

				lResult = (lUser != null);
			}
		} catch (Exception lE) {
			lE.printStackTrace();
		}
		return lResult;
	}

	@Override
	public User createUser(String pLogin, String pPassword, String pName,
			String pSurname, String pTelephone) {
		boolean lResult = false;
		if (!StringUtils.isEmpty(pLogin) && StringUtils.isEmpty(pPassword)) {
			User lUser = new User();
			lUser.setLogin(pLogin);
			lUser.setPassword(Toolbox.md5Spring(pPassword));
			lUser.setName(pName);
			lUser.setSurname(pSurname);
			lUser.setTelephone(pTelephone);

			lResult = mUserDao.create(lUser);

			if (lResult)
				return lUser;
		}
		return null;
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public User createUser(Object pData) {
		boolean lResult = false;
		User lUser = mDataProcessor.retrieveObject(pData, "user", User.class);
		if (lUser != null && !StringUtils.isEmpty(lUser.getPassword())
				&& !StringUtils.isEmpty(lUser.getLogin())) {
			lUser.setPassword(Toolbox.md5Spring(lUser.getPassword()));
			lResult = mUserDao.create(lUser);
			if (lResult) {
				lUser = mUserDao.findUserByLogin(lUser.getLogin());
				return lUser;
			}
		}

		return null;
	}
}
