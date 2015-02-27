package org.mtdev.regataiades.services;

import org.mtdev.regataiades.business.interfaces.DataProcessor;
import org.mtdev.regataiades.business.interfaces.NotificationManager;
import org.mtdev.regataiades.business.interfaces.RegistrationManager;
import org.mtdev.regataiades.business.interfaces.UsersManager;
import org.mtdev.regataiades.dao.interfaces.TeamDao;
import org.mtdev.regataiades.dao.interfaces.UserDao;
import org.mtdev.regataiades.model.Team;
import org.mtdev.regataiades.model.User;
import org.mtdev.regataiades.security.AccessTool;
import org.mtdev.regataiades.tools.Toolbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/registration")
@Transactional
public class RegistrationService {

	@Autowired
	protected UsersManager mUserManager;

	@Autowired
	protected UserDao mUserDao;

	@Autowired
	protected TeamDao mTeamDao;

	@Autowired
	protected RegistrationManager mRegistrationManager;

	@Autowired
	private DataProcessor mDataProcessor;

	@Autowired
	protected NotificationManager mNotificationManager;

	@Autowired
	protected AccessTool mAccessTool;

	@RequestMapping("/registrate")
	public @ResponseBody Object registrate(@RequestBody Object pRequest,
			@RequestParam(value = "lang", defaultValue = "fr") String pLang) {

		if ((Boolean) verify(pRequest)) {

			User lUser = mUserManager.createUser(pRequest);

			if (lUser != null) {
				Team lTeam = mRegistrationManager.performRegistration(lUser,
						pRequest);
				if (lTeam != null) {
					lUser = mDataProcessor.retrieveObject(pRequest, "user",
							User.class);

					mNotificationManager.setLanguage(pLang);
					mNotificationManager.notifyAccountCreation(lUser, lTeam);
					mNotificationManager.notifyFirstRegistration(lTeam);

					return Toolbox.generateResult("status", "success",
							"notification", "successfulRegistration");
				} else {
					return Toolbox.generateResult("status", "failure", "error",
							"teamCreationFalure");
				}
			} else {
				return Toolbox.generateResult("status", "failure", "error",
						"userCreationFalure");
			}
		} else {
			return Toolbox.generateResult("status", "failure", "error",
					"userAlreadyExists");
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

	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
	public @ResponseBody Object options() {
		return "Ok";
	}

	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	@PreAuthorize("@AccessTool.isAuthenticated()")
	public @ResponseBody Object retrieveData() {

		String lUsername = mAccessTool.getUsername();
		if (!StringUtils.isEmpty(lUsername)) {
			User lUser = mUserManager.getUser(lUsername);
			if (lUser != null) {
				Team lTeam = mRegistrationManager.getUserTeam(lUser);
				lTeam.getCoaches().size();
				lTeam.getCrews().size();

				return Toolbox.generateResult("user", lUser, "team", lTeam);
			}
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@PreAuthorize("@AccessTool.isAuthenticated()")
	@Transactional(rollbackFor = Exception.class)
	public @ResponseBody Object updateRegistration(
			@RequestBody Object pRequest,
			@RequestParam(value = "lang", defaultValue = "fr") String pLang) {
		String lUsername = mAccessTool.getUsername();
		if (!StringUtils.isEmpty(lUsername)) {
			User lUser = mUserManager.getUser(lUsername);
			User lSubmitUser = mDataProcessor.retrieveObject(pRequest, "user",
					User.class);
			if (lSubmitUser.getLogin().compareTo(lUser.getLogin()) != 0) {
				if ((Boolean) verify(pRequest)) {
					lUser.setLogin(lSubmitUser.getLogin());
					mUserDao.update(lUser);
				}
			}
			Team lSubmitTeam = mDataProcessor.retrieveObject(pRequest, "team",
					Team.class);
			mTeamDao.update(lSubmitTeam);
			mNotificationManager.setLanguage(pLang);
			mNotificationManager.notifyRegistrationUpdate(lSubmitTeam);
			

			return Toolbox.generateResult("status", "success",
					"notification", "successfulRegistration");
		}
		return null;
	}

	@RequestMapping("/validate")
	public @ResponseBody Object validate(@RequestBody Object pRequest) {
		return null;
	}

}
