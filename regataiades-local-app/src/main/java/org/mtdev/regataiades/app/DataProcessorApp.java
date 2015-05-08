package org.mtdev.regataiades.app;

import org.mtdev.regataiades.app.business.CommandLineProcessor;
import org.mtdev.regataiades.app.business.DataCollector;
import org.mtdev.regataiades.app.business.DataUploader;
import org.mtdev.regataiades.app.business.DefaultCommandLineProcessor;

public class DataProcessorApp {

	public static CommandLineProcessor sCmdLineProcessor = new DefaultCommandLineProcessor();

	public static void main(String... pArgs) {
		try {
			if (pArgs != null && pArgs.length >= 2) {
				long lStandBy = (pArgs.length == 3) ? Long.parseLong(pArgs[2])
						: 60000 * 5;
				while (true) {

					DataCollector lDataCollector = new DataCollector(pArgs[0]);
					Object lEvents = lDataCollector.parseRacesSheet();

					DataUploader lDataUploader = new DataUploader();

					lDataUploader.sendData(lEvents, pArgs[1]);
					System.err.println("Request done. Go to sleep");
					Thread.sleep(lStandBy);
				}

			} else {
				System.err.println("Failed config");
			}
		} catch (Exception lE) {
			System.err.println("Shit happens");
			lE.printStackTrace();
		}
	}

}
