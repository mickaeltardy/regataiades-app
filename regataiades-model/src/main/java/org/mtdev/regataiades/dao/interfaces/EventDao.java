package org.mtdev.regataiades.dao.interfaces;

import java.util.Collection;

import org.mtdev.regataiades.model.Event;

public interface EventDao extends EntityDao<Event> {

	public Event findEventByParams(String pEventId, String pEventCategory,
			String pBoatCategory, String pTime);

	public Collection<Event> findAllEvents();

	public Collection<Event> findEventsByType(int pType);

	public Collection<Event> findEventsByBoatCategory(String pBoatCategory);

}
