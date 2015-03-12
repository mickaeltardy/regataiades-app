package org.mtdev.regataiades.business;

import org.mtdev.regataiades.business.interfaces.BookingManager;
import org.mtdev.regataiades.business.interfaces.DataProcessor;
import org.mtdev.regataiades.dao.interfaces.MealBookingDao;
import org.mtdev.regataiades.model.MealBooking;
import org.mtdev.regataiades.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingManagerImpl implements BookingManager {

	@Autowired
	protected MealBookingDao mMealBookingDao;

	@Autowired
	protected DataProcessor mDataProcessor;

	@Override
	public MealBooking bookMeal(User pUser, Object pRequest) {

		if (pUser != null && pRequest != null) {

			MealBooking lSavedBooking = this.retrieveMealBooking(pUser);

			MealBooking lSubmittedBooking = mDataProcessor.retrieveObject(
					pRequest, "booking", MealBooking.class);

			if (lSavedBooking != null) {
				lSavedBooking.setSaturdayNight(lSubmittedBooking
						.getSaturdayNight());
				lSavedBooking.setSundayNoon(lSubmittedBooking.getSundayNoon());
				lSavedBooking.setSaturdayNoon(lSubmittedBooking
						.getSaturdayNoon());
				mMealBookingDao.update(lSavedBooking);
				return lSavedBooking;
			} else {
				lSubmittedBooking.setUser(pUser);
				mMealBookingDao.create(lSubmittedBooking);
				return lSubmittedBooking;
			}

		}

		return null;
	}

	@Override
	public MealBooking retrieveMealBooking(User pUser) {
		if (pUser != null)
			return mMealBookingDao.findMealBookingByUser(pUser);

		return null;
	}

}
