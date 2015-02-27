package org.mtdev.regataiades.business;

import java.util.Map;

import org.mtdev.regataiades.business.interfaces.DataProcessor;
import org.mtdev.regataiades.business.interfaces.RegistrationManager;
import org.mtdev.regataiades.dao.interfaces.EntityDao;
import org.mtdev.regataiades.dao.interfaces.TeamDao;
import org.mtdev.regataiades.model.Athlete;
import org.mtdev.regataiades.model.Coach;
import org.mtdev.regataiades.model.Crew;
import org.mtdev.regataiades.model.Team;
import org.mtdev.regataiades.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class RegistrationManagerImpl implements RegistrationManager {

	@Autowired
	@Qualifier("teamDaoImpl")
	protected TeamDao mTeamDao;

	@Autowired
	@Qualifier("coachDaoImpl")
	protected EntityDao<Coach> mCoachDao;

	@Autowired
	@Qualifier("crewDaoImpl")
	protected EntityDao<Crew> mCrewDao;

	@Autowired
	@Qualifier("athleteDaoImpl")
	protected EntityDao<Athlete> mAthleteDao;

	@Autowired
	protected DataProcessor mDataProcessor;

	@SuppressWarnings("unchecked")
	@Override
	public Team addTeam(User pUser, Object pData) {

		Map<Object, Object> lData = (Map<Object, Object>) pData;

		if (pUser != null && validateTeamData(lData)) {
			Team lTeam = new Team();

			mTeamDao.create(lTeam);
		}

		return null;
	}

	@Override
	public Athlete addAthlete(Crew pCrew, Object pData) {

		if (pCrew != null && validateAthleteData(pData)) {
			Athlete lAthlete = new Athlete();
			// lAthlete.setCrew(pCrew);
			boolean lResult = mAthleteDao.create(lAthlete);

			if (lResult)
				return lAthlete;
		}
		return null;
	}

	@Override
	public Coach addCoach(Team pTeam, Object pData) {

		if (pTeam != null && validateCoachData(pData)) {

			Coach lCoach = new Coach();
			// lCoach.setTeam(pTeam);

			boolean lResult = mCoachDao.create(lCoach);
			if (lResult)
				return lCoach;

		}
		return null;
	}

	@Override
	public Crew addCrew(Team pTeam, Object pData) {

		if (pTeam != null && validateCrewData(pData)) {
			Crew lCrew = new Crew();
			// lCrew.setTeam(pTeam);

			boolean lResult = mCrewDao.create(lCrew);
			if (lResult)
				return lCrew;
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Team performRegistration(User pUser, Object pRequest) {
		boolean lResult = false;
		Team lTeam = mDataProcessor
				.retrieveObject(pRequest, "team", Team.class);
		if (lTeam != null) {
			lTeam.setUser(pUser);

			lResult = mTeamDao.create(lTeam);
			if (lResult)
				return lTeam;

		}
		return null;
	}

	@Override
	public Team getUserTeam(User pUser) {

		return mTeamDao.findTeamByUser(pUser);
	}

	protected boolean validateTeamData(Map<Object, Object> pData) {
		return false;
	}

	protected boolean validateAthleteData(Object pData) {
		return false;
	}

	protected boolean validateCrewData(Object pData) {
		return false;
	}

	protected boolean validateCoachData(Object pData) {
		return false;
	}
}
