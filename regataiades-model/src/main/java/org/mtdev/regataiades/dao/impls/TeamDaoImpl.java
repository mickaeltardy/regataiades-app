package org.mtdev.regataiades.dao.impls;

import org.mtdev.regataiades.model.Team;
import org.mtdev.regataiades.dao.interfaces.TeamDao;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDaoImpl extends EntityDaoImpl<Team> implements TeamDao{

	@Override
	public Team findTeamByParams(String pName) {
		
		return null;
	}

}
