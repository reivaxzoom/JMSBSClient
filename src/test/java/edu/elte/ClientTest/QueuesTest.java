/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.ClientTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.qpid.client.AMQAnyDestination;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Xavier
 */
public class QueuesTest {

	int numberSampleMessages = 100;
	private final String wallmart = "category='cleaners' OR category='personal Care' OR category='meat' OR category= 'beverages' OR category= 'test' ";
	private final String bestbuySelector = "category='computer' OR category='software' OR category='hardware' OR category= 'test'";
	private final String addidasSelector = "category='shoes' OR category='sport' OR category= 'test'  ";

	public QueuesTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {

		try {
			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream(
					"message.properties"));
			Context context = new InitialContext(properties);

			ConnectionFactory connectionFactory = (ConnectionFactory) context
					.lookup("localConnectionFactory");
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(true,
					Session.SESSION_TRANSACTED);
			Topic requestTopic = session.createTopic("requestTopic");

			// MessageConsumer subscriber2 =
			// session.createDurableSubscriber(requestTopic, "addidas",
			// "category='shoes' OR category='sport' OR category= 'test'  ",true);
			// MessageConsumer subscriber1 =
			// session.createDurableSubscriber(requestTopic, "bestBuy",
			// "category='computer' OR category='software' OR category='hardware' OR category= 'test'",true);
			// MessageConsumer subscriber3 =
			// session.createDurableSubscriber(requestTopic, "wallmart",
			// "category='cleaners' OR category='personal care' OR category='meat' OR category= 'beverages' OR category= 'test' ",true);
			// MessageConsumer subscriber4 =
			// session.createDurableSubscriber(requestTopic, "supermarket");

			Destination responseQueue = null;
			responseQueue = new AMQAnyDestination(
					properties.get("queue.responseQueue")
							+ "; {create: always}");
			Session session1 = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			session1.createProducer(responseQueue);
			session.close();

		} catch (JMSException | IOException | NamingException
				| URISyntaxException ex) {
			Logger.getLogger(QueuesTest.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	// UNSUSCRIBE TOPICS FROM QPID
	// @After
	// public void tearDown() {
	// try {
	// Properties properties = new Properties();
	// properties.load(this.getClass().getClassLoader().getResourceAsStream("message.properties"));
	// Context context = new InitialContext(properties);
	//
	// ConnectionFactory connectionFactory = (ConnectionFactory)
	// context.lookup("localConnectionFactory");
	// Connection connection = connectionFactory.createConnection();
	// connection.start();
	//
	// Session session = connection.createSession(true,
	// Session.SESSION_TRANSACTED);
	// session.unsubscribe("tesco");
	// session.unsubscribe("auchan");
	// session.unsubscribe("supermaxi");
	// // session.("responseQueue");
	// session.close();
	// connection.close();
	// } catch (JMSException ex) {
	// Logger.getLogger(QueuesTest.class.getName()).log(Level.SEVERE, null, ex);
	// } catch (IOException ex) {
	// Logger.getLogger(QueuesTest.class.getName()).log(Level.SEVERE, null, ex);
	// } catch (NamingException ex) {
	// Logger.getLogger(QueuesTest.class.getName()).log(Level.SEVERE, null, ex);
	// }
	//
	// }

	@Test
	public void publish() throws Exception {
		Properties properties = new Properties();
		properties.load(getClass().getClassLoader().getResourceAsStream(
				"message.properties"));
		Context context = new InitialContext(properties);

		ConnectionFactory connectionFactory = (ConnectionFactory) context
				.lookup("localConnectionFactory");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(true,
				Session.SESSION_TRANSACTED);
		Topic requestTopic = session.createTopic("requestTopic");
		MessageProducer producer = session.createProducer(requestTopic);

		Duration duration = Duration.ofMinutes(5);
		Instant now = Instant.now();
		Instant expTime = now.plus(duration);

		for (int i = 0; i < numberSampleMessages; i++) {
			Message message = session.createMessage();
			message.setStringProperty("Test", "TestMessage");
			message.setIntProperty("number", i);
			message.setStringProperty("category", "test");
			message.setJMSExpiration(expTime.toEpochMilli());
			producer.send(message);
		}
		session.commit();

		connection.close();
		context.close();
	}

	@Test
	public void addidas() {

		Session session = null;
		Connection connection = null;
		try {
			Properties properties = new Properties();
			properties.load(this.getClass().getClassLoader()
					.getResourceAsStream("message.properties"));
			Context context = new InitialContext(properties);

			ConnectionFactory connectionFactory = (ConnectionFactory) context
					.lookup("localConnectionFactory");
			connection = connectionFactory.createConnection();
			connection.start();

			session = connection
					.createSession(true, Session.SESSION_TRANSACTED);
			Topic requestTopic = session.createTopic("requestTopic");
			// MessageConsumer subscriber1 =
			// session.createDurableSubscriber(requestTopic, "addidas");
			MessageConsumer subscriber1 = session.createDurableSubscriber(
					requestTopic, "addidas", addidasSelector, true);
			Message message;

			int receivedMsg = 0;
			while ((message = (Message) subscriber1.receive(3000)) != null) {
				System.out.println("\n------------- Msg -------------");
				System.out.println(message);
				System.out.println("-------------------------------\n");
				receivedMsg = receivedMsg + 1;
			}

			session.commit();

			// session.unsubscribe("tesco");
			session.close();
			connection.close();
			// Assert.assertEquals(numberSampleMessages,receivedMsg);

		} catch (JMSException ex) {
			Logger.getLogger(QueuesTest.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException | NamingException ex) {
			Logger.getLogger(QueuesTest.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	@Test
	public void bestBuy() {

		Session session = null;
		Connection connection = null;
		try {
			Properties properties = new Properties();
			properties.load(this.getClass().getClassLoader()
					.getResourceAsStream("message.properties"));
			Context context = new InitialContext(properties);

			ConnectionFactory connectionFactory = (ConnectionFactory) context
					.lookup("localConnectionFactory");
			connection = connectionFactory.createConnection();
			connection.start();

			session = connection
					.createSession(true, Session.SESSION_TRANSACTED);
			Topic requestTopic = session.createTopic("requestTopic");
			MessageConsumer subscriber1 = session.createDurableSubscriber(
					requestTopic, "bestBuy", bestbuySelector, true);
			// MessageConsumer subscriber1 =
			// session.createDurableSubscriber(requestTopic, "bestBuy");
			Message message;

			long timeout = 3000;
			int receivedMsg = 0;
			while ((message = (Message) subscriber1.receive(timeout)) != null) {
				System.out.println("\n------------- Msg -------------");
				System.out.println(message);
				System.out.println("-------------------------------\n");
				receivedMsg = receivedMsg + 1;
			}
			session.commit();

			// session.unsubscribe("tesco");
			session.close();
			connection.close();
			// Assert.assertEquals(numberSampleMessages,receivedMsg);

		} catch (JMSException ex) {
			Logger.getLogger(QueuesTest.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException | NamingException ex) {
			Logger.getLogger(QueuesTest.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	@Test
	public void wallmart() {

		Session session = null;
		Connection connection = null;
		try {
			Properties properties = new Properties();
			properties.load(this.getClass().getClassLoader()
					.getResourceAsStream("message.properties"));
			Context context = new InitialContext(properties);

			ConnectionFactory connectionFactory = (ConnectionFactory) context
					.lookup("localConnectionFactory");
			connection = connectionFactory.createConnection();
			connection.start();

			session = connection
					.createSession(true, Session.SESSION_TRANSACTED);
			Topic requestTopic = session.createTopic("requestTopic");
			MessageConsumer subscriber1 = session.createDurableSubscriber(
					requestTopic, "wallmart", wallmart, true);
			// MessageConsumer subscriber1 =
			// session.createDurableSubscriber(requestTopic, "wallmart");
			Message message;

			long timeout = 3000;
			int receivedMsg = 0;
			while ((message = (Message) subscriber1.receive(timeout)) != null) {
				System.out.println("\n------------- Msg -------------");
				System.out.println(message);
				System.out.println("-------------------------------\n");
				receivedMsg = receivedMsg + 1;
				session.commit();
			}

			// session.unsubscribe("tesco");
			session.close();
			connection.close();
			// Assert.assertEquals(numberSampleMessages,receivedMsg);

		} catch (JMSException ex) {
			Logger.getLogger(QueuesTest.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException | NamingException ex) {
			Logger.getLogger(QueuesTest.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	@Test
	public void supermarket() {

		Session session = null;
		Connection connection = null;
		try {
			Properties properties = new Properties();
			properties.load(this.getClass().getClassLoader()
					.getResourceAsStream("message.properties"));
			Context context = new InitialContext(properties);

			ConnectionFactory connectionFactory = (ConnectionFactory) context
					.lookup("localConnectionFactory");
			connection = connectionFactory.createConnection();
			connection.start();

			session = connection
					.createSession(true, Session.SESSION_TRANSACTED);
			Topic requestTopic = session.createTopic("requestTopic");
			MessageConsumer subscriber1 = session.createDurableSubscriber(
					requestTopic, "supermarket");
			Message message;

			long timeout = -1;
			int receivedMsg = 0;
			while ((message = (Message) subscriber1.receive(timeout)) != null) {
				System.out.println("\n------------- Msg -------------");
				System.out.println(message);
				System.out.println("-------------------------------\n");
				receivedMsg = receivedMsg + 1;
				session.commit();
			}

			// session.unsubscribe("tesco");
			session.close();
			connection.close();
			// Assert.assertEquals(numberSampleMessages,receivedMsg);

		} catch (JMSException ex) {
			Logger.getLogger(QueuesTest.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException | NamingException ex) {
			Logger.getLogger(QueuesTest.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

}
