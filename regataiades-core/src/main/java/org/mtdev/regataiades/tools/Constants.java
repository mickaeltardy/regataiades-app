package org.mtdev.regataiades.tools;

public abstract class Constants {

	public class EventTypes {
		public static final int race = 1;
		public static final int extra = 2;
	}

	public class SexCats {
		public static final String men = "M";
		public static final String women = "W";
		public static final String mix = "O";
	}

	public class BoatCats {

		public static final String four = "4x";
		public static final String eight = "8+";
	}

	public class EventCats {

		public static final String series = "series";
		public static final String qualification = "qualification";
		public static final String repechage = "repechage";
		public static final String finals = "final";
		public static final String boat = "boat";
		public static final String semi = "demi";
	}
	public class EventStatus{
		public static final int undefined = 0;
		public static final int notstarted = 1;
		public static final int started = 2;
		public static final int notfinished = 3;
		public static final int completed = 4;
	}

}
