package elte.client.operations;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.qpid.client.AMQAnyDestination;
import org.apache.qpid.url.URLSyntaxException;

import elte.client.model.ShoppingCart;
import elte.client.view.ReceiverOptionParse;

public class ConsumeClient {
		
	private List<ShoppingCart> listOrders;
	ReceiverOptionParse option;
	private Instant start;
	
	
	public ConsumeClient(ReceiverOptionParse option){
		listOrders = new ArrayList<>();	
		this.option=option;	
		start = Instant.now();
			
	}
	
	public void receive(){
		if(option.isForever()){
			receiveMsg();
		}
		else{
			consumeAll(option.getTimeout());
		}
		Instant end = Instant.now();
		System.out.println("Time taken: "+Duration.between(start, end));
		
	}
	

    private void consumeAll(long timeout) {
        
        Session session = null;
        Connection connection = null;
        MessageConsumer consumer = null;
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("message.properties"));
            Context context = new InitialContext(properties);
            
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
            connection = connectionFactory.createConnection();
            connection.start();
            
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Destination queue =  new AMQAnyDestination("responseQueue; {create: always}");
        Destination queue =  new AMQAnyDestination("responseQueue");
//            Queue queue = (Queue) context.lookup("responseQueue");
            consumer = session.createConsumer(queue);
            
            ObjectMessage message;
            while ((message = (ObjectMessage) consumer.receive(timeout)) != null) {
                
                ObjectMessage objMes = (ObjectMessage) message;
                System.out.println("\n------------- Msg -------------");
                System.out.println(message);
                System.out.println("-------------------------------\n");
                
                
                ShoppingCart clientCart = (ShoppingCart) objMes.getObject();
                listOrders.add(clientCart);
            }
           
        } catch (IOException | NamingException | JMSException | URISyntaxException ex ) {
            Logger.getLogger(ConsumeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                consumer.close();
                session.close();
                connection.close();
            } catch (JMSException ex) {
                Logger.getLogger(ConsumeClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    private void receiveMsg() {
        Connection connection = null;
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("message.properties"));
            Context context = new InitialContext(properties);
            
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
            connection = connectionFactory.createConnection();
            connection.start();
            
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination queue = new AMQAnyDestination("responseQueue");
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(new MessageListener() {
                
                @Override
                public void onMessage(Message message) {
                    try {
                        Thread.sleep(2000);
                        ObjectMessage objMes=(ObjectMessage) message;
                        System.out.println("============================================");
                        ShoppingCart clientCart=(ShoppingCart)objMes;
                        Thread.sleep(2000);
                        System.out.println(clientCart);
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ConsumeClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } );
        } catch (URLSyntaxException | JMSException ex) {
            Logger.getLogger(ConsumeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ConsumeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NamingException ex) {
            Logger.getLogger(ConsumeClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                connection.close();
            } catch (JMSException ex) {
                Logger.getLogger(ConsumeClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

	public List<ShoppingCart> getListOrders() {
		return listOrders;
	}


	public void setListOrders(List<ShoppingCart> listOrders) {
		this.listOrders = listOrders;
	}
}
