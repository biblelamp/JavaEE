package de.parksinformatik.blog.security.windowsauth;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * Active Directory Authentication.
 *
 * @author StefanKowski
 */
public class ActiveDirectoryAuthentication {

	/**
	 * JAAS configuration.
	 */
	public static class StaticConfiguration extends Configuration {

		final AppConfigurationEntry staticConfigEntry;

		public StaticConfiguration(String loginModuleName) {

			Map<String, ?> options = new HashMap<>();
			staticConfigEntry = new AppConfigurationEntry(loginModuleName,
					AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, options);
		}

		@Override
		public AppConfigurationEntry[] getAppConfigurationEntry(String name) {

			return new AppConfigurationEntry[] { staticConfigEntry };
		}
	}

	/**
	 * JAAS callback handler.
	 */
	public static class StaticCallbackHandler implements CallbackHandler {

		/**
		 * Constructor.
		 *
		 * @param username
		 *            Windows user name
		 * @param password
		 *            Windows password
		 */
		public StaticCallbackHandler(String username, String password) {

			this.username = username;
			this.password = password;
		}

		@Override
		public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

			for (int i = 0; i < callbacks.length; i++) {
				if (callbacks[i] instanceof TextOutputCallback) {

					// unused

				} else if (callbacks[i] instanceof NameCallback) {

					NameCallback nc = NameCallback.class.cast(callbacks[i]);
					nc.setName(username);

				} else if (callbacks[i] instanceof PasswordCallback) {

					PasswordCallback pc = PasswordCallback.class.cast(callbacks[i]);
					pc.setPassword(password.toCharArray());

				} else {

					throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
				}
			}
		}

		/** User name. */
		private String username;

		/** Password. */
		private String password;
	}

	/**
	 * Constructor.
	 *
	 * @param domainName
	 *            domain name (e.g. "mydomain.local")
	 */
	public ActiveDirectoryAuthentication(String domainName) {

		this.domainName = domainName;
	}

	/**
	 * Get Active Directory domain controllers.
	 *
	 * Shell example: nslookup -type=SRV _ldap._tcp.dc._msdcs.mydomain.local
	 *
	 * @param domain
	 *            Domain name (e.g. "mydomain.local")
	 * @return Domain controllers (list may be empty)
	 * @throws NamingException
	 */
	private static Collection<InetSocketAddress> getDomainControllers(String domain) throws NamingException {

		final String typeSRV = "SRV";
		final String[] types = new String[] { typeSRV };

		DirContext ctx = new InitialDirContext();

		Attributes attributes = ctx.getAttributes("dns:/_ldap._tcp.dc._msdcs." + domain, types);
		if (attributes.get(typeSRV) == null) {
			return Collections.emptyList();
		}

		NamingEnumeration<?> e = attributes.get(typeSRV).getAll();
		TreeMap<Integer, InetSocketAddress> result = new TreeMap<>();

		while (e.hasMoreElements()) {

			String line = (String) e.nextElement();

			// The line is priority weight port host
			String[] parts = line.split("\\s+");

			int prio = Integer.parseInt(parts[0]);
			int port = Integer.parseInt(parts[2]);
			String host = parts[3];

			result.put(prio, new InetSocketAddress(host, port));
		}

		return result.values();
	}

	/**
	 * Authenticate user.
	 *
	 * @param username
	 *            Windows user name
	 * @param password
	 *            Windows password
	 * @throws ValidationException
	 */
	public void authenticate(String username, String password) throws ValidationException {

		LoginContext lc;
		try {

			// get domain controller for login
			Collection<InetSocketAddress> result = getDomainControllers(domainName);
			if (result.isEmpty()) {
				throw new ValidationException("No domain controllers found for domain " + domainName);
			}
			String loginServer = result.iterator().next().getHostString();
			System.setProperty("java.security.krb5.realm", domainName.toUpperCase());
			System.setProperty("java.security.krb5.kdc", loginServer);

			// perform login
			lc = new LoginContext("", null, new StaticCallbackHandler(username, password),
					new StaticConfiguration("com.sun.security.auth.module.Krb5LoginModule"));
			lc.login();

			// logout (we want to check the password only)
			lc.logout();

		} catch (LoginException le) {

			// error
			throw new ValidationException("Authentication failed: " + le.getMessage(), le);

		} catch (SecurityException se) {

			// error
			throw new ValidationException("Authentication failed: " + se.getMessage(), se);

		} catch (NamingException ne) {

			// error
			throw new ValidationException("Authentication failed: " + ne.getMessage(), ne);
		}
	}

	/** Windows domain name. */
	private String domainName;
}