package org.mtdev.regataiades.services;

import org.mtdev.regataiades.business.interfaces.BookingManager;
import org.mtdev.regataiades.business.interfaces.NotificationManager;
import org.mtdev.regataiades.business.interfaces.RegistrationManager;
import org.mtdev.regataiades.business.interfaces.UsersManager;
import org.mtdev.regataiades.model.MealBooking;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/booking")
@Transactional
public class BookingService {

	@Autowired
	public BookingManager mBookingManager;

	@Autowired
	protected AccessTool mAccessTool;

	@Autowired
	protected UsersManager mUserManager;

	@Autowired
	protected NotificationManager mNotificationManager;	

	@Autowired
	protected RegistrationManager mRegistrationManager;

	@RequestMapping("/book")
	@PreAuthorize("@AccessTool.isAuthenticated()")
	public @ResponseBody Object bookMeal(@RequestBody Object pRequest,
			@RequestParam(value = "lang", defaultValue = "fr") String pLang) {
		String lUsername = mAccessTool.getUsername();
		if (!StringUtils.isEmpty(lUsername)) {
			User lUser = mUserManager.getUser(lUsername);

			MealBooking lMealBooking = mBookingManager
					.bookMeal(lUser, pRequest);
			if(lMealBooking != null){
				Team lTeam = mRegistrationManager.getUserTeam(lUser);
				mNotificationManager.setLanguage(pLang);
				mNotificationManager.notifyMealBooking(lTeam, lMealBooking);

				
				return Toolbox.generateResult("status", "success",
					"notification", "bookingDone");
			}

		}
		return Toolbox.generateResult("status", "failure", "error",
				"bookingFailure");
	}

	@RequestMapping("/retrieve")
	@PreAuthorize("@AccessTool.isAuthenticated()")
	public @ResponseBody Object retrieveMealBooking() {
		String lUsername = mAccessTool.getUsername();
		if (!StringUtils.isEmpty(lUsername)) {
			User lUser = mUserManager.getUser(lUsername);

			MealBooking lMealBooking = mBookingManager
					.retrieveMealBooking(lUser);

			return lMealBooking;
		}
		return null;
	}

}
