/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elte.client.view;

import java.util.List;

import elte.client.operations.ConsumeClient;
import elte.client.operations.SampleListMessage;
import elte.client.model.ShoppingCart;


/**
 *Main class, show options for startup
 * @author Xavier
 */
public class Launcher {

    /**
     * 
     * @param args sample data options
     */
    public static void main(String[] args) throws Exception {
        if (args != null && args.length > 0) {
            String option = args[0];
            String[] args2 = new String[0];
            if (args.length > 1) {
                args2 = new String[args.length - 1];
                System.arraycopy(args, 1, args2, 0, args2.length);
            }

            switch (option) {
                case "sender":{
                        String usage = "Usage: [sender -c=2 -i=3]";
                        String descr = "Number of items per order";
                        SenderOptionParse so=new SenderOptionParse(args2, usage, descr);
                        SampleListMessage sampleList = new SampleListMessage(so.getShoppingCars(), so.getItemsPerCar());
                        sampleList.sendSample();
                        break;
                    }
                case "receiver":{
                        String u = "Usage: receiver [OPTIONS] 'ADDRESS'";
                        String d = "Receive messages from the specified address.";
                        ReceiverOptionParse ro=new ReceiverOptionParse(args2, u, d);
                        ConsumeClient co=new ConsumeClient(ro);
                        co.receive();
                        
                        List<ShoppingCart> listReceived =co.getListOrders();
                        listReceived.stream().forEach(System.out::println);
                        break;
                    }
//                case "receiverListener":{
//                        String u = "Usage: drain [OPTIONS] 'ADDRESS'";
//                        String d = "Drains messages from the specified address.";
//                        new ReceiverListener();
//                        break;
//                    }
                    default :{
                    	new DefaultOptionParse(args2, null, null).printHelp();;
                    	
                    }
            }
        }
    }
}