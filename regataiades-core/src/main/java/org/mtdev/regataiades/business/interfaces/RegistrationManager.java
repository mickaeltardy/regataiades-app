package org.mtdev.regataiades.business.interfaces;

import org.mtdev.regataiades.model.Athlete;
import org.mtdev.regataiades.model.Coach;
import org.mtdev.regataiades.model.Crew;
import org.mtdev.regataiades.model.Team;
import org.mtdev.regataiades.model.User;

public interface RegistrationManager {

	public Team addTeam(User pUser, Object pData);

	public Athlete addAthlete(Crew pCrew, Object pData);

	public Coach addCoach(Team pTeam, Object pData);

	public Crew addCrew(Team pTeam, Object pData);

	public Team performRegistration(User pUser, Object pRequest);
	
	public Team getUserTeam(User pUser);

}
