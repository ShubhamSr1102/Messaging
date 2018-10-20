package com.capgemini.application;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class ApplicationOne {

	public static void main(String[] args) {

		ConnectionFactory connectionFactory = null;
	//	Connection connection = null;
		Queue queue = null;
		try {
			InitialContext initialContext = new InitialContext();
			queue = (Queue) initialContext.lookup("jmsPSQueue");
			connectionFactory = (QueueConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");

			/*
			 * connection = connectionFactory.createConnection(); 
			 * Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			 * MessageProducer messageProducer = session.createProducer(queue); TextMessage
			 * textMessage = session.createTextMessage("Text Message");
			 * messageProducer.send(textMessage); System.out.println("Message Produced");
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (JMSContext context = connectionFactory.createContext()) {
			TextMessage message = context.createTextMessage("Shubham");
			context.createProducer().send(queue, message);
			System.out.println("Message Produced");
		}
	}
}
