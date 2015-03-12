package org.mtdev.regataiades.dao.interfaces;

import org.mtdev.regataiades.model.MealBooking;
import org.mtdev.regataiades.model.User;

public interface MealBookingDao extends EntityDao<MealBooking> {

	public MealBooking findMealBookingByUser(User pUser);
}
