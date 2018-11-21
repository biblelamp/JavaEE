package org.jms.example;

import javax.annotation.Resource;

import javax.inject.Inject;

import javax.jms.Queue;
import javax.jms.JMSContext;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Sender {

	@Resource(mappedName = "jms/queue/ExpiryQueue")
	private Queue queue;

	@Resource(mappedName = "jms/queue/DLQ")
	private Queue dlq;

	@Inject
	private JMSContext context;

	public void sendMessage(String txt) {
		context.createProducer().send(queue, txt);
	}
}
