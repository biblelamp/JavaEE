package org.jms.example;

import javax.enterprise.context.*;
import javax.jms.*;
import javax.jms.Queue;
import javax.naming.*;
import java.util.*;
import java.util.logging.*;

@ApplicationScoped
public class Resender {

	public List<String> resendMessages(String strQueue) {
		List<String> list = new LinkedList<>();
		try {
			Connection connection = null;
			Session session = null;
			MessageConsumer consumer = null;

			ConnectionFactory cf = null;
			try {
				cf = (ConnectionFactory) new InitialContext().lookup("java:/ConnectionFactory");
			} catch (NamingException ex) {
				Logger.getLogger(Resender.class.getName()).log(Level.SEVERE, null, ex);
			}

			connection = cf.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Queue queueConsume = null;
			try {
				queueConsume = (Queue) new InitialContext().lookup(strQueue);
			} catch (NamingException ex) {
				Logger.getLogger(Resender.class.getName()).log(Level.SEVERE, null, ex);
			}

			consumer = session.createConsumer(queueConsume);
			connection.start();

			Message msg;

			do {
				msg = consumer.receiveNoWait();

				Logger.getLogger(Resender.class.getName()).log(Level.INFO, "Received from " + queueConsume.getQueueName() + ": " + msg, "");

				if (msg != null) {
					list.add(msg.getBody(String.class));
				}
			} while (msg != null);

			connection.close();

		} catch (JMSException ex) {
			Logger.getLogger(Resender.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;
	}
}
