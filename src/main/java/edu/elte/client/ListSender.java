package edu.elte.client;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ListSender {

    static Supplier<ClientRequest> supplyClientRequest = ListSender::createClientRequest;
    private static final String[] categories= new String[]{"Beverages","Meat","Cleaners","PersonalCare","Other","shoes","sport","software","hardware"};

//    public static void main(String[] args) {
//
//        int shoppingCars = 5;
//        int itemsPerCar = 5;
//
//        ListSender listSender = new ListSender();
//        List<ShoppingCart> listCars = createCarList(shoppingCars, itemsPerCar);
//
//        for (ShoppingCart cart1 : listCars) {
//            listSender.sendMessages(cart1);
//        }
//    }
    
    
    
       public void sendSample(int shoppingCars,int itemsPerCar ) {


        ListSender listSender = new ListSender();
        List<ShoppingCart> listCars = createCarList(shoppingCars, itemsPerCar);

        listCars.stream().forEach((cart1) -> {
            listSender.sendMessages(cart1);
        });
    }
    
    

    public static List<ShoppingCart> createCarList(int shoppingCars, int itemsPerCar) {
        List<ShoppingCart> listCars = new LinkedList<>();
        for (int j = 0; j < shoppingCars; j++) {
            ShoppingCart cart = new ShoppingCart();
            for (int i = 0; i < itemsPerCar; i++) {
                cart.add(supplyClientRequest.get());
            }
            listCars.add(cart);
        }
        return listCars;
    }

    public void sendMessages(ShoppingCart cart) {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("message.properties"));
            Context context = new InitialContext(properties);

            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Topic requestTopic = session.createTopic("requestTopic");
            MessageProducer producer = session.createProducer(requestTopic);

            Duration duration = Duration.ofMinutes(40);
            Instant now = Instant.now();
            Instant expTime = now.plus(duration);

            Random rnd = new Random();
            ObjectMessage m = session.createObjectMessage(cart);
            m.setIntProperty("Id", 99);
            m.setStringProperty("name", "Xavier");
            m.setStringProperty("addres", "budapest");
            m.setDoubleProperty("number", 1);
            m.setStringProperty("category", categories[rnd.nextInt(categories.length-1)]);
            m.setJMSExpiration(expTime.toEpochMilli());
            producer.send((Message) m);
            System.out.println("Sent: " + m);
            session.commit();

            connection.close();
        } catch (JMSException | NamingException | IOException ex) {
            Logger.getLogger(ListSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ClientRequest createClientRequest() {
        List<ClientRequest> cliReq = new LinkedList<>();
        cliReq.add(new ClientRequest("Yoghurt", 1));
        cliReq.add(new ClientRequest("Mango", 2));
        cliReq.add(new ClientRequest("Pineapple", 8));
        cliReq.add(new ClientRequest("Oranges", 6));
        cliReq.add(new ClientRequest("Apples", 1));
        cliReq.add(new ClientRequest("Bananas", 1));
        cliReq.add(new ClientRequest("Lettuce", 4));
        cliReq.add(new ClientRequest("Tomatoes", 10));
        cliReq.add(new ClientRequest("Squash", 5));
        cliReq.add(new ClientRequest("Celery", 7));
        cliReq.add(new ClientRequest("Cucumber", 7));
        cliReq.add(new ClientRequest("Mushrooms", 7));
        cliReq.add(new ClientRequest("Milk ", 9));
        cliReq.add(new ClientRequest("Cheese", 8));
        cliReq.add(new ClientRequest("Eggs", 8));
        cliReq.add(new ClientRequest("Cottage cheese", 7));
        cliReq.add(new ClientRequest("Sour cream", 1));
        cliReq.add(new ClientRequest("Yogurt", 7));
        cliReq.add(new ClientRequest("Beef", 10));
        cliReq.add(new ClientRequest("Poultry", 9));
        cliReq.add(new ClientRequest("Ham", 10));
        cliReq.add(new ClientRequest("Seafood", 4));
        cliReq.add(new ClientRequest("Lunch meat", 6));
        cliReq.add(new ClientRequest("Soda", 3));
        cliReq.add(new ClientRequest("Juice", 1));
        cliReq.add(new ClientRequest("Coffee", 4));
        cliReq.add(new ClientRequest("Tea", 4));
        cliReq.add(new ClientRequest("Water", 6));
        cliReq.add(new ClientRequest("Beer", 6));
        cliReq.add(new ClientRequest("Noodles", 3));
        cliReq.add(new ClientRequest("Rice", 1));
        cliReq.add(new ClientRequest("Canned", 2));
        cliReq.add(new ClientRequest("Dry mix", 5));
        cliReq.add(new ClientRequest("Bread", 7));
        cliReq.add(new ClientRequest("Bagels", 8));
        cliReq.add(new ClientRequest("Muffins", 3));
        cliReq.add(new ClientRequest("Cake", 3));
        cliReq.add(new ClientRequest("Potato chips", 3));
        cliReq.add(new ClientRequest("Pretzels", 7));
        cliReq.add(new ClientRequest("Ice cream", 6));
        cliReq.add(new ClientRequest("Cookies", 5));
        cliReq.add(new ClientRequest("Paper plates", 10));
        cliReq.add(new ClientRequest("Napkins", 8));
        cliReq.add(new ClientRequest("Garbage bags", 7));
        cliReq.add(new ClientRequest("Detergent", 2));
        Collections.shuffle(cliReq);
        return cliReq.get(0);

    }
}
