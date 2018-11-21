package org.jms.example;

import javax.enterprise.context.*;
import javax.inject.*;
import javax.jms.*;
import javax.jms.Queue;
import javax.naming.*;
import java.util.*;
import java.util.logging.*;

@ApplicationScoped
public class Reader {
	private static final Logger LOG = Logger.getLogger(Receiver.class.getName());

	@Inject
	private JMSContext context;

	public List<String> readMessages(String qName) {
		List<String> list = new ArrayList();

		javax.jms.Queue queue = null;

		try {
			queue = (Queue) new InitialContext().lookup(qName);
			QueueBrowser browser = context.createBrowser(queue);

			Enumeration messageEnum = browser.getEnumeration();
			while (messageEnum.hasMoreElements()) {
				TextMessage message = (TextMessage) messageEnum.nextElement();
				list.add(message.getText());
			}
			browser.close();
		} catch (NamingException | JMSException ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return list;
	}
}
