package org.mtdev.regataiades.dao.interfaces;

import org.mtdev.regataiades.model.Team;
import org.mtdev.regataiades.model.User;

public interface TeamDao extends EntityDao<Team> {

	public Team findTeamByParams(String pName);
	
	public Team findTeamByUser(User pUser);
}


