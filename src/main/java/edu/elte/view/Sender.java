package edu.elte.view;

/**
 *
 * @author Xavier
 */
import edu.elte.client.ListSender;
import static edu.elte.client.ListSender.createCarList;
import edu.elte.client.ShoppingCart;
import java.util.List;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.qpid.client.AMQAnyDestination;
import static edu.elte.view.OptionParser.addOption;


/**
 * 
 * @author Xavier
 */
public class Sender extends OptionParser
{
        
    static final Option COUNT = new Option("c",
            "count",
            "stop after count messages have been sent, zero disables",
            "COUNT",
            "1",
            Integer.class);
     
    static final Option OBJMSG = new Option("o",
            "objects",
            "Number of objects inside the message, five disables",
            "[int]",
            "5",
            Integer.class);
        
    static final Option ID = new Option("i",
            "id",
            "use the supplied id instead of generating one",
            null,
            null,
            Boolean.class);
    
    static final Option CONTENT = new Option(null,
            "content",
            "specify textual content",
            "TEXT",
            null,
            Boolean.class);
    
    static final Option MSG_PROPERTY = new Option("P",
            "property",
            "specify message property",
            "NAME=VALUE",
            null,
            Boolean.class);    
    
    static final Option MAP_ENTRY = new Option("M",
            "map",
            "specify entry for map content",
            "KEY=VALUE",
            null,
            Boolean.class); 
    
    
    static 
    {   
        addOption(CONFIG);
        addOption(BROKER);
        addOption(HELP);
        addOption(TIMEOUT);
        addOption(COUNT);
        addOption(OBJMSG);
        addOption(MSG_PROPERTY);
        addOption(MAP_ENTRY);
        addOption(CONTENT);
        addOption(CON_OPTIONS);
        addOption(BROKER_OPTIONS);
    }
    
    public Sender(String[] args, String usage, String desc) throws Exception
    {   
        super(args, usage, desc); 
        
        int shoppingCars = Integer.parseInt(getOp(COUNT));
        int itemsPerCar = Integer.parseInt(getOp(OBJMSG));
        
        ListSender sender = new ListSender();
        sender.sendSample(shoppingCars, itemsPerCar);
    }
   
   
    public static void main(String[] args) throws Exception
    {
        String u = "Usage: spout [OPTIONS] 'ADDRESS'";
        String d = "Send messages to the specified address."; 
            
        new Sender(args,u,d);        
    }
}

