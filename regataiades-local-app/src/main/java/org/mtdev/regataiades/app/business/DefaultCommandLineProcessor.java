package org.mtdev.regataiades.app.business;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class DefaultCommandLineProcessor implements CommandLineProcessor {

	/**
	 * 
	 * @param mCmdLineArgs
	 */
	@Override
	public void commandLineParser(String... pCmdLineArgs) {
		final CommandLineParser lCmdLineParser = new PosixParser();
		final Options lOptions = setUpOptions();
		CommandLine lCmdLine;
		try {
			lCmdLine = lCmdLineParser.parse(lOptions, pCmdLineArgs);
			this.processOptions(lCmdLine);
		} catch (ParseException parseException) {
			parseException.printStackTrace();
		}

	}

	@Override
	public void processOptions(CommandLine pCmdLine) {
/*
 * if (pCmdLine.hasOption("datadir")) {
			System.setProperty(Constants.PROPERTY_SERVER_CONF_DIR,
					pCmdLine.getOptionValue("datadir"));

		}

		if (pCmdLine.hasOption("config")) {
			System.setProperty(Constants.PROPERTY_CONF_LOCATION,
					pCmdLine.getOptionValue("config"));
		}
		if (pCmdLine.hasOption("appcode")) {
			System.setProperty(Constants.PROPERTY_APP_CODE,
					pCmdLine.getOptionValue("appcode"));
		}
		
 */
	}

	@Override
	public Options setUpOptions() {
		final Options posixOptions = new Options();
		posixOptions.addOption("d", "datadir", true, "Specific data dir");
		posixOptions.addOption("a", "appcode", true, "Specific app code");
		posixOptions.addOption("c", "config", true,
				"Specific configuration file location");
		return posixOptions;
	}

}