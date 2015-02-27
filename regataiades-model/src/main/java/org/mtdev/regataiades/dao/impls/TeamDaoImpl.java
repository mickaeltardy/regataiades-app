package org.mtdev.regataiades.dao.impls;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.mtdev.regataiades.dao.interfaces.TeamDao;
import org.mtdev.regataiades.model.Team;
import org.mtdev.regataiades.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDaoImpl extends EntityDaoImpl<Team> implements TeamDao {

	@Override
	public Team findTeamByParams(String pName) {

		return null;
	}

	@Override
	public Team findTeamByUser(User pUser) {

		Criteria lCriteria = getGenericCriteria().add(
				Restrictions.eq("user.id", pUser.getId()));

		return this.findItemByCriteria(lCriteria);
	}

}
