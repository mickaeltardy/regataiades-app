package org.mtdev.regataiades.dao.interfaces;

import org.mtdev.regataiades.model.Team;

public interface TeamDao extends EntityDao<Team> {

	public Team findTeamByParams(String pName);
}
