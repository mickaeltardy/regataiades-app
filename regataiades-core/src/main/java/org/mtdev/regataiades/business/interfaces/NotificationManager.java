package org.mtdev.regataiades.business.interfaces;

import org.mtdev.regataiades.model.MealBooking;
import org.mtdev.regataiades.model.Team;
import org.mtdev.regataiades.model.User;

public interface NotificationManager {

	boolean setLanguage(String pLang);

	boolean notifyFirstRegistration(Team pTeam);

	boolean notifyAccountCreation(User pUser, Team pTeam);

	boolean notifyRegistrationUpdate(Team pTeam);

	boolean notifyMealBooking(Team pTeam, MealBooking pMealBooking);

}
