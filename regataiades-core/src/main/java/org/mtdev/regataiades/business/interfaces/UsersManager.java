package org.mtdev.regataiades.business.interfaces;

import org.mtdev.regataiades.model.User;

public interface UsersManager {

	public boolean checkUserExistence(String pEmail);

	public User createUser(String pLogin, String pPassword, String pName,
			String pSurname, String pTelephone);

	public User createUser(Object pData);

}
