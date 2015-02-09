package org.mtdev.regataiades.services;

import org.mtdev.regataiades.business.interfaces.DataProcessor;
import org.mtdev.regataiades.business.interfaces.NotificationManager;
import org.mtdev.regataiades.business.interfaces.RegistrationManager;
import org.mtdev.regataiades.business.interfaces.UsersManager;
import org.mtdev.regataiades.model.Team;
import org.mtdev.regataiades.model.User;
import org.mtdev.regataiades.tools.Toolbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import antlr.build.Tool;

@Controller
@RequestMapping("/registration")
@Transactional
public class RegistrationService {

	@Autowired
	protected UsersManager mUserManager;

	@Autowired
	protected RegistrationManager mRegistrationManager;

	@Autowired
	private DataProcessor mDataProcessor;

	@Autowired
	protected NotificationManager mNotificationManager;

	@RequestMapping("/registrate")
	public @ResponseBody Object registrate(@RequestBody Object pRequest,
			@RequestParam(value = "lang", defaultValue = "fr") String pLang) {
		boolean lResult = false;
		if ((Boolean) verify(pRequest)) {

			User lUser = mUserManager.createUser(pRequest);

			if (lUser != null) {
				Team lTeam = mRegistrationManager.performRegistration(lUser,
						pRequest);
				if (lTeam != null) {
					
					mNotificationManager.setLanguage(pLang);
					mNotificationManager.notifyAccountCreation(lUser, lTeam);
					mNotificationManager.notifyFirstRegistration(lTeam);
					
					return Toolbox.generateResult("status", "success", "notification", "successfulRegistration");
				}else{
					return Toolbox.generateResult("status", "failure", "error", "teamCreationFalure");
				}
			}else{
				return Toolbox.generateResult("status", "failure", "error", "userCreationFalure");
			}
		}else{
			return Toolbox.generateResult("status", "failure", "error", "userAlreadyExists");
		}

	}

	@RequestMapping("/verify")
	public @ResponseBody Object verify(@RequestBody Object pRequest) {
		System.out.println(pRequest);
		User lUser = mDataProcessor
				.retrieveObject(pRequest, "user", User.class);
		String lEmail = lUser.getLogin();

		return !mUserManager.checkUserExistence(lEmail);

	}

	@RequestMapping("/validate")
	public @ResponseBody Object validate(@RequestBody Object pRequest) {
		return null;
	}

}
