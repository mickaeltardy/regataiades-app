package org.mtdev.regataiades.dao.impls;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.mtdev.regataiades.dao.interfaces.EventDao;
import org.mtdev.regataiades.model.Event;
import org.springframework.stereotype.Repository;

@Repository
public class EventDaoImpl extends EntityDaoImpl<Event> implements EventDao {

	@Override
	public Event findEventByParams(String pEventId, String pEventCategory,
			String pBoatCategory, String pTime, String pRaceType) {

		Criteria lCriteria = getGenericCriteria();
		if (pEventId == null)
			lCriteria.add(Restrictions.isNull("eventId"));
		else
			lCriteria.add(Restrictions.eq("eventId", pEventId));
		if (pEventCategory == null)
			lCriteria.add(Restrictions.isNull("eventCategory"));
		else
			lCriteria.add(Restrictions.eq("eventCategory", pEventCategory));
		if (pBoatCategory == null)
			lCriteria.add(Restrictions.isNull("boatCategory"));
		else
			lCriteria.add(Restrictions.eq("boatCategory", pBoatCategory));
		if (pTime == null)
			lCriteria.add(Restrictions.isNull("time"));
		else
			lCriteria.add(Restrictions.eq("time", pTime));
		if (pRaceType
				== null)
			lCriteria.add(Restrictions.isNull("raceType"));
		else
			lCriteria.add(Restrictions.eq("raceType", pRaceType));

		return this.findItemByCriteria(lCriteria);

	}

	@Override
	public Collection<Event> findAllEvents() {
		Criteria lCriteria = getGenericCriteria();
		lCriteria.add(Restrictions.isNotNull("id"));
		return this.findListByCriteria(lCriteria);
	}

	@Override
	public Collection<Event> findEventsByType(int pType) {
		Criteria lCriteria = getGenericCriteria();
		lCriteria.add(Restrictions.eq("type", pType));
		return this.findListByCriteria(lCriteria);
	}

	@Override
	public Collection<Event> findEventsByBoatCategory(String pBoatCategory) {
		Criteria lCriteria = getGenericCriteria();
		lCriteria.add(Restrictions.eq("boatCategory", pBoatCategory));
		return this.findListByCriteria(lCriteria);

	}

	@Override
	public Collection<Event> findEventsByTypeAndRaceType(int pType,
			String pRaceType) {
		Criteria lCriteria = getGenericCriteria();
		lCriteria.add(Restrictions.eq("type", pType));
		lCriteria.add(Restrictions.eq("raceType", pRaceType));
		return this.findListByCriteria(lCriteria);
	}

	@Override
	public Collection<Event> findEventsByBoatCategoryAndRaceType(
			String pBoatCategory, String pRaceType) {
		Criteria lCriteria = getGenericCriteria();
		lCriteria.add(Restrictions.eq("boatCategory", pBoatCategory));
		lCriteria.add(Restrictions.eq("raceType", pRaceType));
		return this.findListByCriteria(lCriteria);

	}
}
