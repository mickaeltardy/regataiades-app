package org.mtdev.regataiades.dao.impls;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.mtdev.regataiades.dao.interfaces.MealBookingDao;
import org.mtdev.regataiades.model.MealBooking;
import org.mtdev.regataiades.model.User;
import org.springframework.stereotype.Component;

@Component
public class MealBookingDaoImpl extends EntityDaoImpl<MealBooking>  implements MealBookingDao {

	@Override
	public MealBooking findMealBookingByUser(User pUser) {
		
		Criteria lCriteria = getGenericCriteria().add(
				Restrictions.eq("user.id", pUser.getId()));

		return this.findItemByCriteria(lCriteria);
	}

}
