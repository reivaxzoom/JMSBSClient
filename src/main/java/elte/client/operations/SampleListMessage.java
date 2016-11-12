package elte.client.operations;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

import elte.client.model.Item;
import elte.client.model.ShoppingCart;
import javax.jms.DeliveryMode;

/**
 * ensemble client request JMS object message and send it
 * @author Xavier
 *
 */
public class SampleListMessage {

     Supplier<Item> supplyClientRequest = PredifinedDataStore.supplyClientRequest;
     Supplier<Map<String, String>> supplyClientData = PredifinedDataStore::getRandomSampleRequestData;
     Supplier<Integer> rndBudget = SampleListMessage::rndBudget;
     Supplier<Integer> rndId = SampleListMessage::rndId;
     SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
     private List<ShoppingCart> listCars;
     static int  minBudget = 300, maxBudget = 600;
     static int defaultNumbCart=1, defaultItemPerCart=5;
     static int durationMsg=40;
    
     public SampleListMessage() {
    	 this(defaultNumbCart,defaultItemPerCart);
    	 
	}
   
     public SampleListMessage(int shoppingCars, int itemsPerCar) {
    	 ensambleRequest(shoppingCars, itemsPerCar);
 	}
     
     /**
      * Generated random set of shopping cars at once, 
      * @param shoppingCars number of orders 
      * @param itemsPerCar number of items per order
      */
     private void ensambleRequest(int shoppingCars, int itemsPerCar){
    	 listCars = new ArrayList<>();
   	  for (int i = 0; i < shoppingCars; i++) {
             ShoppingCart singleCar = createSingleSampleCar(itemsPerCar);
             List<ShoppingCart> sampleShopCars = breakingIntoCategories(singleCar);
             List<ShoppingCart> labeledShopCars = labelOrder(sampleShopCars);
              listCars.addAll(labeledShopCars);
         }
     }
     
     
     /**
      * Send the list of messages in listCart
      */
    public void sendSample() {
    	listCars.stream().forEach(car -> sendMessages(car));
    }

    
    /**
     * Create a Shoping cart a single shopping cart with random items
     * @param itemsPerCar number of random items 
     * @return 
     */
    private ShoppingCart createSingleSampleCar(int itemsPerCar) {
        return createCarList(1, itemsPerCar).get(0);
    }

    
    /**
     * Generate and apppned random client details to every shopping cart  
     * @param sampleShopCars a list of shoppling carts 
     * @return list of shopping carts 
     */
    private List<ShoppingCart> labelOrder(List<ShoppingCart> sampleShopCars) {
        int subOrd=1;
        List<ShoppingCart> reqTot= new ArrayList<>();
        String id = String.valueOf(rndId());
        Calendar today = Calendar.getInstance();

        for (ShoppingCart shopCar : sampleShopCars) {
            String date = dateFormat.format(today.getTime());
            String budget = String.valueOf(rndBudget.get());
            int itemsNumber = shopCar.size();
            Map<String, String> reqCliData = supplyClientData.get();
            reqCliData.put("id", id);
            reqCliData.put("subOrd", subOrd+"/"+sampleShopCars.size());
            reqCliData.put("date", date);
            reqCliData.put("budget", budget);
            reqCliData.put("itemsNumber", String.valueOf(itemsNumber));
            shopCar.getReqDataObj().putAll(reqCliData);
            reqTot.add(shopCar);
            subOrd=subOrd+1;
        }
        return reqTot;
    }

    /**
     * Split incoming car into different categories and group it in a different
     * shopping carts
     *
     * @param singleCar
     * @return
     */
    private  List<ShoppingCart> breakingIntoCategories(ShoppingCart singleCar) {
        Set<String> categories = new HashSet<>();

        //filtering categories
        singleCar.stream().forEach(e -> categories.add(e.getCategory()));
//        categories.stream().forEach(System.out::println);

        ShoppingCart selItbyCat;
        List<ShoppingCart> reqTot = new ArrayList<>();

        for (String cl : categories) {
            selItbyCat = new ShoppingCart();
            selItbyCat.setReqDataObj(new HashMap<>());
            selItbyCat.addAll(singleCar.stream().filter(p -> p.getCategory().equals(cl)).collect(Collectors.toList()));
            selItbyCat.getReqDataObj().put("category", cl);
            reqTot.add(selItbyCat);
        }
        return reqTot;
    }

    private List<ShoppingCart> createCarList(int shoppingCars, int itemsPerCar) {
        List<ShoppingCart> listCarsTMP = new LinkedList<>();
        for (int j = 0; j < shoppingCars; j++) {
            ShoppingCart cart = new ShoppingCart();
            for (int i = 0; i < itemsPerCar; i++) {
                cart.add(supplyClientRequest.get());
            }
            listCarsTMP.add(cart);
        }
        return listCarsTMP;
    }

    private void sendMessages(ShoppingCart cart) {
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
            producer.setPriority(Priority.values().length);
            Duration duration = Duration.ofMinutes(durationMsg);
            Instant now = Instant.now();
            Instant expTime = now.plus(duration);

            ObjectMessage m = session.createObjectMessage(cart);
            
            Iterator it = cart.getReqDataObj().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                m.setStringProperty(pair.getKey().toString(), pair.getValue().toString());
            }
            producer.send((Message) m);
            producer.send(m, DeliveryMode.PERSISTENT, getRandomPrio(), expTime.toEpochMilli());
            System.out.println("Sent: " + m);
            session.commit();

            connection.close();
        } catch (JMSException | NamingException | IOException ex) {
            Logger.getLogger(SampleListMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static int rndBudget(){
        return rndNumber(minBudget, maxBudget);
    }
    
    
    private static int rndId(){
        int min = 1, max = 100;
        return rndNumber(min,max);
    }

    private static int rndNumber(int min, int max ){
        return new Random().nextInt(max - min + 1) + min;
    }
    
    
    static enum Priority {
        ZERO(1), MAYBE(2), LOW(3), NORMAL(4), MEDIUM(5), HIGH(6), EXTREME(7);

        private final int level;
        private Priority(int level) {
            this.level=level;
        }

        public int getNumber() {
            return level;
        }
     }

    private static int getRandomPrio() {
        Random rnd = new Random();
        int rndValue = rnd.nextInt(Priority.values().length);
        return Priority.values()[rndValue].getNumber();
    }
    
}
