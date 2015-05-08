package org.mtdev.regataiades.app.business;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

public interface CommandLineProcessor {

	void commandLineParser(String... pCmdLineArgs);

	Options setUpOptions();

	void processOptions(CommandLine pCmdLine);

}
