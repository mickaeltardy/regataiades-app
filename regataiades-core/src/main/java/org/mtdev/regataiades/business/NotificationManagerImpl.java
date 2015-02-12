package org.mtdev.regataiades.business;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.mtdev.regataiades.business.interfaces.DataRenderer;
import org.mtdev.regataiades.business.interfaces.MailManager;
import org.mtdev.regataiades.business.interfaces.NotificationManager;
import org.mtdev.regataiades.model.Crew;
import org.mtdev.regataiades.model.Team;
import org.mtdev.regataiades.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationManagerImpl implements NotificationManager {

	private static final int sPricePerAthlete = 10;

	protected String mLang = "fr";

	@Autowired
	protected MailManager mMailManager;

	@Autowired
	protected DataRenderer mDataRenderer;

	@Override
	public boolean setLanguage(String pLang) {
		mLang = pLang;
		return false;
	}

	@Override
	public boolean notifyAccountCreation(User pUser, Team pTeam) {
		String lUsername = pUser.getLogin();
		String lPassword = pUser.getPassword();
		Map<Object, Object> lContext = new HashMap<Object, Object>();
		lContext.put("password", lPassword);
		lContext.put("username", lUsername);
		if (pTeam != null)
			lContext.put("invited", pTeam.isInvited());

		Writer lOutput = mDataRenderer.renderData(lContext,
				"/templates/mail/mailAccountCreation." + mLang + ".html");

		String lSubject = getSubject("account");
		mMailManager.sendMail(pUser.getLogin(), "[Regataiades] " + lSubject,
				lOutput.toString());

		return true;
	}

	/**
	 * FIXME Put price calc elsewhere
	 */
	@Override
	public boolean notifyFirstRegistration(Team pTeam) {

		Map<Object, Object> lContext = new HashMap<Object, Object>();
		lContext.put("payementLabel", "Regataiades Equipe " + pTeam.getName());
		lContext.put("invited", pTeam.isInvited());
		lContext.put("name", pTeam.getName());
		lContext.put("contactName", pTeam.getContactName());
		lContext.put("contactSurname", pTeam.getContactSurname());
		lContext.put("contactEmail", pTeam.getContactEmail());

		lContext.put("cost", pTeam.getAthletesNum() * sPricePerAthlete);

		Map<String, Integer> lCrewsCnt = new HashMap<String, Integer>();
		for (Crew lCrew : pTeam.getCrews()) {
			String lCat = lCrew.getCategory();
			Integer lCnt = 1;
			if (lCrewsCnt.containsKey(lCat)) {
				lCnt = lCrewsCnt.get(lCat) + 1;
			}
			lCrewsCnt.put(lCat, lCnt);
		}

		lContext.put("crewsCnt", lCrewsCnt);

		Writer lOutput = mDataRenderer.renderData(lContext,
				"/templates/mail/mailNotification." + mLang + ".html");
		Writer lSystemOutput = mDataRenderer.renderData(lContext,
				"/templates/mail/mailSystemNotification.html");

		String lSubject = getSubject("firstRegistration");
		mMailManager.sendMail(pTeam.getContactEmail(), "[Regataiades] "
				+ lSubject, lOutput.toString());

		mMailManager.sendMail("mishgunn@gmail.com",
				"[Regataiades] Nouvelle inscription", lSystemOutput.toString());
		
		mMailManager.sendMail("inscriptions@regataiades.fr",
				"[Regataiades] Nouvelle inscription", lSystemOutput.toString());

		return true;
	}

	/*
	 * FIXME Extremely dirty. Do refactor
	 */
	protected String getSubject(String pLabel) {
		if (pLabel.compareTo("account") == 0) {
			if (isFrench()) {
				return "Votre compte utilisateur";
			} else {
				return "Your user account";
			}
		} else if (pLabel.compareTo("firstRegistration") == 0) {
			if (isFrench()) {
				return "Votre inscription à la régate";
			} else {
				return "Your registration to the regatta";
			}
		}
		return null;
	}

	protected boolean isFrench() {
		return mLang.compareTo("fr") == 0;
	}

}
