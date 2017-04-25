package org.mtdev.regataiades.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.mtdev.regataiades.business.interfaces.ResultsManager;
import org.mtdev.regataiades.dao.interfaces.EventDao;
import org.mtdev.regataiades.model.Event;
import org.mtdev.regataiades.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ResultsManagerImpl implements ResultsManager {

	@Autowired
	protected EventDao mEventDao;
	

	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public boolean updateEvents(Collection<Event> pEvents) {

		if (pEvents != null) {
			for (Event lEvent : pEvents) {
				Event lStoredEvent = mEventDao.findEventByParams(
						lEvent.getEventId(), lEvent.getEventCategory(),
						lEvent.getBoatCategory(), lEvent.getTime(),
						lEvent.getRaceType());
				if (lStoredEvent != null) {
					lStoredEvent = this.updateEvent(lStoredEvent, lEvent);

					mEventDao.update(lStoredEvent);
				} else {
					mEventDao.create(lEvent);
				}
			}
		}

		return false;
	}

	protected Event updateEvent(Event pDestEvent, Event pSourceEvent) {
		pDestEvent.setStatus(pSourceEvent.getStatus());
		pDestEvent.setSort(pSourceEvent.getSort());

		if (pSourceEvent.getResults() != null) {
			// List<Result> lMergedResults = this.mergeResults(
			// pDestEvent.getResults(), pSourceEvent.getResults());
			pDestEvent.setResults(pSourceEvent.getResults());

		}
		return pDestEvent;

	}

	protected List<Result> mergeResults(List<Result> pDestResults,
			List<Result> pSourceResults) {

		if (pDestResults != null && pSourceResults != null) {
			List<Result> lMergedResults = new ArrayList<Result>();
			for (Result lDestResult : pDestResults) {
				Result lMergedResult = lDestResult;
				for (Result lSourceResult : pSourceResults) {
					if (this.isResultsEqual(lSourceResult, lDestResult)) {
						lMergedResult = this.mergeResults(lDestResult,
								lSourceResult);
					}
				}
				lMergedResults.add(lMergedResult);
			}
			for (Result lSourceResult : pSourceResults) {
				boolean lContained = false;
				for (Result lDestResult : pDestResults) {
					if (this.isResultsEqual(lSourceResult, lDestResult)) {
						lContained = true;
						break;
					}

				}
				if (!lContained)
					lMergedResults.add(lSourceResult);
			}
			return lMergedResults;
		}

		return null;
	}

	private Result mergeResults(Result pDestResult, Result pSourceResult) {
		if (pDestResult != null && pSourceResult != null) {
			pDestResult.setLine(pSourceResult.getLine());
			pDestResult.setTime(pSourceResult.getTime());
			pDestResult.setTimeDiff(pSourceResult.getTimeDiff());
			pDestResult.setRank(pSourceResult.getRank());

		}
		return pDestResult;
	}

	protected boolean isResultsEqual(Result pRefResult, Result pCompResult) {
		return (pRefResult != null && pCompResult != null);

	}

	@Override
	public boolean cleanUp(String pRaceType) {
		

		Query query = sessionFactory.getCurrentSession().createSQLQuery("delete from results where fk_event_id in (select id from events where raceType = '"+pRaceType+"')");
		
		
		query.executeUpdate();

		query = sessionFactory.getCurrentSession().createSQLQuery("delete from events where raceType = '"+pRaceType+"'");
		
		query.executeUpdate();
		
		return true;
	}
}
