package com.capgemini.receiver;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ApplicationTwo {

	public static void main(String[] args) {

		ConnectionFactory connectionfactory = null;
	//	Connection connection = null;
		Queue queue = null;

		try {
			InitialContext intialContext = new InitialContext();
			queue = (Queue) intialContext.lookup("jmsPSQueue");
			connectionfactory = (ConnectionFactory) intialContext.lookup("jms/__defaultConnectionFactory");

			/*
			 * connection = connectionfactory.createConnection(); 
			 * Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			 * connection.start(); 
			 * MessageConsumer messageConsumer = session.createConsumer(queue); 
			 * TextMessage textMessage = (TextMessage) messageConsumer.receive();
			 * String body = textMessage.getText();
			 * System.out.println(body);
			 */

		} catch (NamingException e) {
			e.printStackTrace();
		}

		try (JMSContext context = connectionfactory.createContext()) {
			JMSConsumer consumer = context.createConsumer(queue);
			String body = consumer.receiveBody(String.class);
			System.out.println(body);
		}
	}

}
