package org.jms.example;

import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;
import javax.jms.Message;
import javax.jms.JMSException;
import javax.jms.MessageListener;
 
@MessageDriven(name = "Receiver", activationConfig = {
  @ActivationConfigProperty(propertyName = "destinationLookup", 
                            propertyValue = "jms/queue/ExpiryQueue"),
                            //propertyValue = "java:/jms/queue/DLQ"),
  @ActivationConfigProperty(propertyName = "destinationType", 
                            propertyValue = "javax.jms.Queue"),
  @ActivationConfigProperty(propertyName = "acknowledgeMode", 
                            propertyValue = "Auto-acknowledge") })
public class Receiver implements MessageListener {
	private static final Logger LOG = Logger.getLogger(Receiver.class.getName());
	
	public static List<String> messages = new ArrayList<String>();
 
	public void onMessage(Message rcvMessage) {
		try {
			messages.add(rcvMessage.getBody(String.class));
		} catch (JMSException ex) {
			LOG.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}