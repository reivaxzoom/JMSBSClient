package edu.elte.view;

import edu.elte.client.ConsumeClient;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.qpid.client.AMQAnyDestination;

public class Receiver extends OptionParser
{
       
    static final Option FOREVER = new Option("f",
            "forever",
            "ignore timeout and wait forever",
            null,
            null,
            Boolean.class);

    static final Option COUNT = new Option ("c",
            "count",
            "read c messages, then exit",
            "COUNT",
            "0",
            Integer.class);
                                                

    static 
    {        
        addOption(BROKER);
        addOption(HELP);
        addOption(TIMEOUT);
        addOption(FOREVER);
        addOption(COUNT);
        addOption(CON_OPTIONS);
        addOption(BROKER_OPTIONS);
    }
    
    public Receiver(String[] args, String usage, String desc) throws Exception
    {   
        super(args, usage, desc);        
        
        
        ConsumeClient consume = new ConsumeClient();
        consume.consumeAll();
    }
   
    public static void main(String[] args) throws Exception
    {
        String u = "Usage: drain [OPTIONS] 'ADDRESS'";
        String d = "Drains messages from the specified address."; 
         args=new String[]{"requestQueue"};
         
        new Receiver(args,u,d);        
    }
}
