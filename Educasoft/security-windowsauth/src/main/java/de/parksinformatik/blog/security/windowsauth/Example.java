package de.parksinformatik.blog.security.windowsauth;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Example: Active Directory authentication.
 * 
 * @author StefanKowski
 */
public class Example {

	/**
	 * Main method.
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String[] args) {

		//
		// Prerequisites...
		//

		final Options options = new Options();
		options.addOption("d", "domain", true, "Windows domain (e.g. 'mydomain.local')");
		options.addOption("u", "username", true, "Windows user name");
		options.addOption("p", "password", true, "Password");

		CommandLine cl = null;
		try {

			// parse command line
			cl = new DefaultParser().parse(options, args);

		} catch (ParseException e) {

			// error: command line parsing failed
			System.err.println("Command line parsing failed. Reason: " + e.getMessage());
			System.exit(1);
		}

		String domain = cl.getOptionValue("d");
		String username = cl.getOptionValue("u");
		String password = cl.getOptionValue("p");
		if (domain == null || username == null || password == null) {

			// missing arguments, generate a help statement
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("Example", options, true);

			return;
		}

		//
		// ...aaand: Action!
		//

		try {

			// authenticate user
			ActiveDirectoryAuthentication ada = new ActiveDirectoryAuthentication(domain);
			ada.authenticate(username, password);
			System.out.println("Authentication successful!");

		} catch (ValidationException e) {

			// error: authentication failed
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
