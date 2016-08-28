package edu.elte.view;

import edu.elte.client.ShoppingCart;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.qpid.client.AMQAnyDestination;
import org.apache.qpid.client.AMQConnection;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.qpid.QpidException;
import org.apache.qpid.url.URLSyntaxException;

public class ReceiverListener {

    public static void main(String[] args) {
        ReceiverListener receiver = new ReceiverListener();
        receiver.receiveMsg();
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
                        Logger.getLogger(ReceiverListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } );
        } catch (URLSyntaxException | JMSException ex) {
            Logger.getLogger(ReceiverListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReceiverListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NamingException ex) {
            Logger.getLogger(ReceiverListener.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                connection.close();
            } catch (JMSException ex) {
                Logger.getLogger(ReceiverListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
   
}
