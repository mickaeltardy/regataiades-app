package org.mtdev.regataiades.business.interfaces;

import org.mtdev.regataiades.model.MealBooking;
import org.mtdev.regataiades.model.User;

public interface BookingManager {

	public MealBooking bookMeal(User pUser, Object pRequest);

	public MealBooking retrieveMealBooking(User pUser);

}
