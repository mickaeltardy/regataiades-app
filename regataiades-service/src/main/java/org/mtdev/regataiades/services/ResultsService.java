package org.mtdev.regataiades.services;

import java.util.Collection;

import org.mtdev.regataiades.business.interfaces.ResultsManager;
import org.mtdev.regataiades.dao.interfaces.EventDao;
import org.mtdev.regataiades.dao.interfaces.UserDao;
import org.mtdev.regataiades.model.Event;
import org.mtdev.regataiades.view.ResponseView;
import org.mtdev.regataiades.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/results")
@Transactional
public class ResultsService {

	@Autowired
	protected ResultsManager mResultsManager;

	@Autowired
	protected EventDao mEventDao;

	@Autowired
	protected UserDao mUserDao;

	@RequestMapping("/update")
	public @ResponseBody Object updateResults(
			@RequestBody Collection<Event> pData) {
		mResultsManager.updateEvents(pData);
		return null;
	}

	@RequestMapping("/events")
	@ResponseView(Views.Public.class)
	public @ResponseBody Object retrieveEventsList() {
		Collection<Event> lList = mEventDao.findEventsByType(1);

		return lList;
	}

	@RequestMapping("/full")
	public @ResponseBody Object retrieveFullEvents() {
		return null;
	}

	@RequestMapping("/category/{category}")
	@ResponseView(Views.Internal.class)
	public @ResponseBody Object retrieveCategoryResults(
			@PathVariable(value = "category") String pBoatCategory) {
		return mEventDao.findEventsByBoatCategory(pBoatCategory);
	}

	@RequestMapping("/category/")
	@ResponseView(Views.Internal.class)
	public @ResponseBody Object retrieveAllCategoriesResults() {

		return mEventDao.findEventsByType(1);
	}

	@RequestMapping("/eventResults/{event}")
	public @ResponseBody Object retrieveEventResults(
			@PathVariable("event") int pEventId) {
		return null;
	}

	@RequestMapping("/races")
	public @ResponseBody Object getRaces() {
		return null;
	}

	@RequestMapping("/categories")
	public @ResponseBody Object getCategories() {
		return null;
	}

	@RequestMapping("/times")
	public @ResponseBody Object getTimes() {
		return null;
	}

}
