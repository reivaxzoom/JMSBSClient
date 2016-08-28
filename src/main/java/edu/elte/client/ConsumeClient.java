package edu.elte.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.qpid.client.AMQAnyDestination;


public class ConsumeClient {


    public void consumeAll() {
        
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
            
            long timeout = -1;
            
            while ((message = (ObjectMessage) consumer.receive(timeout)) != null) {
                
                ObjectMessage objMes = (ObjectMessage) message;
                System.out.println("\n------------- Msg -------------");
                System.out.println(message);
                System.out.println("-------------------------------\n");
                
                
                ShoppingCart clientCart = (ShoppingCart) objMes.getObject();
                
            }
           
        } catch (IOException | NamingException | JMSException ex ) {
            Logger.getLogger(ConsumeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
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


    public static void main(String[] args) {

        ConsumeClient receiver=new ConsumeClient();
        receiver.consumeAll();
    }
}
