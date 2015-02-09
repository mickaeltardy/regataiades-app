package org.mtdev.regataiades.services;

import org.mtdev.regataiades.business.interfaces.UsersManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserService {

	@Autowired
	protected UsersManager mUserManager;

	@RequestMapping("/check")
	public @ResponseBody Object checkLogin(
			@RequestParam(value = "login", required = true) String pLogin) {

		return mUserManager.checkUserExistence(pLogin);
	}

	@RequestMapping("/create")
	public @ResponseBody Object createUser(@RequestBody Object pData) {

		return mUserManager.createUser(pData);
	}

}
