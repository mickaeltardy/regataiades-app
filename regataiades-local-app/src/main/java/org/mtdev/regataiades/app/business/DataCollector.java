package org.mtdev.regataiades.app.business;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.mtdev.regataiades.model.Event;
import org.mtdev.regataiades.model.Result;
import org.mtdev.regataiades.tools.Constants.EventStatus;
import org.mtdev.regataiades.tools.Constants.EventTypes;
import org.springframework.util.StringUtils;

public class DataCollector {

	protected String mFilePath;
	private Workbook mWorkbook;

	public DataCollector(String pFilePath) {
		mFilePath = pFilePath;
	}

	public Object parseRacesSheet() {
		return parseRacesSheet(mFilePath);
	}

	public Object parseRacesSheet(String pFilePath) {

		if (!StringUtils.isEmpty(pFilePath)) {
			Sheet lSheet = getSheetFromFile(pFilePath, "Courses");
			return getEventsList(lSheet);
		}

		return null;

	}

	public Sheet getSheetFromFile(String pFilePath, String pSheetName) {

		if (!StringUtils.isEmpty(pFilePath)) {

			try {
				WorkbookSettings lSettings = new WorkbookSettings();
				lSettings.setEncoding("ISO8859-1");
				mWorkbook = Workbook.getWorkbook(new File(pFilePath), lSettings);
				Sheet lSheet = mWorkbook.getSheet(pSheetName);

				return lSheet;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return null;

	}

	public Object getEventsList(Sheet pWorksheet) {

		if (pWorksheet != null) {

			LabelsConverter lLabelsConverter = new LabelsConverter();

			int lRowsCnt = pWorksheet.getRows();

			Collection<Event> lEvents = new ArrayList<Event>();
			Event lCurrentEvent = null;
			int lCnt = 0;
			for (int lRowNum = 0; lRowNum < lRowsCnt; lRowNum++) {
				lCnt++;
				Cell[] lRow = pWorksheet.getRow(lRowNum);
				RowProcessor lRowProcessor = new RowProcessor(lRow);
				if ((lRowProcessor.isExtraRaceTitleRow()
						|| lRowProcessor.isRaceTitleRow() || lRowProcessor
							.isDelimiterRow()) && lCurrentEvent != null) {
					lCurrentEvent.setStatus(this.getEventStatus(lCurrentEvent));
					lEvents.add(lCurrentEvent);

					lCurrentEvent = null;
				}
				if (lRowProcessor.isExtraRaceTitleRow()
						|| lRowProcessor.isRaceTitleRow()) {
					lCurrentEvent = new Event();
					if (lRowProcessor.isRaceTitleRow()) {

						lCurrentEvent
								.setBoatCategory(lLabelsConverter
										.convertCategory(lRowProcessor
												.getCellValue(2)));

						if (StringUtils.isEmpty(lRowProcessor.getCellValue(0))) {
							lCurrentEvent.setEventCategory(lLabelsConverter
									.convertEventCategory(lRowProcessor
											.getCellValue(1)));
						} else {
							lCurrentEvent.setEventCategory(lLabelsConverter
									.convertEventCategory(lRowProcessor
											.getCellValue(0)));
							lCurrentEvent.setEventId(lLabelsConverter
									.convertEventId(lRowProcessor
											.getCellValue(1)));
						}

						lCurrentEvent.setType(EventTypes.race);

					} else if (lRowProcessor.isExtraRaceTitleRow()) {
						lCurrentEvent.setEventCategory(lLabelsConverter
								.convertEventCategory(lRowProcessor
										.getCellValue(0)));

						lCurrentEvent.setBoatCategory(lRowProcessor
								.getCellValue(2));
						lCurrentEvent.setType(EventTypes.extra);

					}
					lCurrentEvent.setTime(lRowProcessor.getCellValue(3));
					lCurrentEvent.setStatus(EventStatus.undefined);
					lCurrentEvent.setSort(lCnt);
				}

				if (lRowProcessor.isDataRow() && lCurrentEvent != null) {
					if (lRowProcessor.getIntCellValue(1) > 0) {
						Result lResult = new Result();
						lResult.setLine(lRowProcessor.getIntCellValue(0));
						lResult.setCrewId(lRowProcessor.getIntCellValue(1));
						lResult.setCrewName(lRowProcessor.getCellValue(2));
						lResult.setTime(lRowProcessor.getCellValue(3));
						lResult.setTimeDiff(lRowProcessor.getCellValue(4));
						lResult.setRank(lRowProcessor.getIntCellValue(6));
						lResult.setSort(lCnt);

						List<Result> lResults = lCurrentEvent.getResults();
						if (lResults == null)
							lResults = new ArrayList<Result>();
						lResults.add(lResult);
						lCurrentEvent.setResults(lResults);

					}
				}

			}
			
			lCurrentEvent.setStatus(this.getEventStatus(lCurrentEvent));
			lEvents.add(lCurrentEvent);

			lCurrentEvent = null;
			return lEvents;
		}

		return null;
	}

	protected int getEventStatus(Event pCurrentEvent) {
		if (pCurrentEvent != null && pCurrentEvent.getResults() != null) {
			boolean lStarted = false, lNotFinished = true;
			for (Result lResult : pCurrentEvent.getResults()) {
				if (lResult.getTime().compareTo("00:00.000") != 0 && lResult.getTime().compareTo("") != 0) {
					lStarted = true;
				}
				if (lResult.getTime().compareTo("00:00.000") == 0) {
					lNotFinished = true;
				}
			}
			if (lStarted && lNotFinished)
				return EventStatus.started;
			if (lStarted && !lNotFinished)
				return EventStatus.completed;
			if (!lStarted && lNotFinished)
				return EventStatus.notstarted;

		}
		return EventStatus.undefined;

	}
	
	public boolean close(){

		mWorkbook.close();
		
		return true;
	}

}
