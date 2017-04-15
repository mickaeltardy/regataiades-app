package org.mtdev.regataiades.business.interfaces;

import java.util.Collection;

import org.mtdev.regataiades.model.Event;

public interface ResultsManager {

	boolean updateEvents(Collection<Event> pData);

	boolean cleanUp(String pRaceType);

	
	
}
