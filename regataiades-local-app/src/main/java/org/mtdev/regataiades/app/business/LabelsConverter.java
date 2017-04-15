package org.mtdev.regataiades.app.business;

import org.mtdev.regataiades.tools.Constants.AgeCats;
import org.mtdev.regataiades.tools.Constants.BoatCats;
import org.mtdev.regataiades.tools.Constants.EventCats;
import org.mtdev.regataiades.tools.Constants.SexCats;
import org.springframework.util.StringUtils;

public class LabelsConverter {

	public String convertCategory(String pCategory) {

		StringBuilder lOutput = new StringBuilder();

		if (!StringUtils.isEmpty(pCategory)) {

			if (pCategory.toLowerCase().contains("master")) {
				lOutput.append(AgeCats.master);
			}
			
			if (pCategory.toLowerCase().contains("hommes")) {
				lOutput.append(SexCats.men);
			} else if (pCategory.toLowerCase().contains("femmes")) {
				lOutput.append(SexCats.women);
			} else if (pCategory.toLowerCase().contains("mixte")) {
				lOutput.append(SexCats.mix);
			}

			if (pCategory.contains("8+")) {
				lOutput.append(BoatCats.eight);
			} else if (pCategory.toLowerCase().contains("4x")) {
				lOutput.append(BoatCats.quad);
			} else if (pCategory.toLowerCase().contains("4+")) {
				lOutput.append(BoatCats.four);
			}
		}

		return lOutput.toString();

	}

	public String convertEventCategory(String pEventCategory) {
		String lOutput = "";

		if (!StringUtils.isEmpty(pEventCategory)) {
			if (pEventCategory.toLowerCase().contains("bateau")) {
				lOutput = EventCats.boat;
			} else if (pEventCategory.toLowerCase().contains("final")) {
				lOutput = EventCats.finals;
			} else if (pEventCategory.toLowerCase().contains("rie")) {
				lOutput = EventCats.series;
			} else if (pEventCategory.toLowerCase().contains("tdr")) {
				lOutput = EventCats.qualification;
			} else if (pEventCategory.toLowerCase().contains("demi")) {
				lOutput = EventCats.semi;
			} else if (pEventCategory.toLowerCase().contains("repechage")) {
				lOutput = EventCats.repechage;
			}

		}

		return lOutput;
	}

	public String convertEventId(String pEventId) {
		String lOutput = "";
		
		if (!StringUtils.isEmpty(pEventId)) {
			
				lOutput = pEventId.replace("à", "-");
			
		}

		return lOutput;
	}

}
