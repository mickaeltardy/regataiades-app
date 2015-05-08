package org.mtdev.regataiades.app.business;

import jxl.Cell;
import jxl.CellType;

import org.mtdev.regataiades.app.business.Constants.RowTypes;

public class RowProcessor {

	protected Cell[] mCells;

	protected int mRowType;

	public RowProcessor(Cell[] pCells) {
		mCells = pCells;
		mRowType = getRowType();
	}

	public int getRowType() {

		try {

			if (mCells[0].getType() == CellType.EMPTY
					&& mCells[1].getType() == CellType.EMPTY
					&& mCells[2].getType() == CellType.EMPTY) {
				return RowTypes.delimiterRow;
			}
		} catch (Exception lE) {
		}

		try {
			if (mCells[1].getType() != CellType.EMPTY
					&& mCells[2].getType() != CellType.EMPTY
					&& mCells[3].getType() != CellType.EMPTY
					&& mCells[4].getType() == CellType.EMPTY
					&& mCells[5].getType() == CellType.EMPTY
					&& mCells[2].getContents().compareTo("Equipage") != 0
					&& mCells[3].getContents().compareTo("Parcours") != 0) {
				return RowTypes.racesTitleRow;
			}
		} catch (Exception lE) {
		}

		try {
			if (mCells[0].getType() == CellType.LABEL
					&& mCells[1].getType() == CellType.EMPTY
					&& mCells[2].getType() == CellType.LABEL
					&& mCells[3].getType() != CellType.EMPTY) {
				return RowTypes.extraRaceTitleRow;
			}
		} catch (Exception lE) {
		}
		try {
			if (mCells[1].getType() != CellType.EMPTY) {

				try {
					if (mCells[0].getType() != CellType.EMPTY
							&& mCells[0].getContents().compareTo("Ligne") == 0
							&& mCells[2].getContents().compareTo("Equipage") == 0
							&& mCells[3].getContents().compareTo("Parcours") == 0) {
						return RowTypes.dataHeaderRow;
					}

				} catch (Exception lE) {
				}

				try {

					if (mCells[1].getType() == CellType.NUMBER_FORMULA
							&& mCells[6].getType() == CellType.NUMBER_FORMULA)
						return RowTypes.dataRow;
				} catch (Exception lE) {
				}

			}
		} catch (Exception lE) {
		}

		return RowTypes.untyped;
	}

	public String getCellValue(int pCellNum) {

		if (mCells != null) {
			return mCells[pCellNum].getContents();

		}
		return "";
	}

	public int getIntCellValue(int pCellNum) {

		if (mCells != null) {
			String lValue = this.getCellValue(pCellNum);

			try {
				return Integer.parseInt(lValue);
			} catch (Exception lE) {

			}

		}
		return 0;
	}

	public boolean isDataRow() {
		return mRowType == RowTypes.dataRow;
	}

	public boolean isDataHeaderRow() {
		return mRowType == RowTypes.dataHeaderRow;
	}

	public boolean isExtraRaceTitleRow() {
		return mRowType == RowTypes.extraRaceTitleRow;
	}

	public boolean isRaceTitleRow() {
		return mRowType == RowTypes.racesTitleRow;
	}

	public boolean isDelimiterRow() {
		return mRowType == RowTypes.delimiterRow;
	}

}
