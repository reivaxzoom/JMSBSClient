/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.view;

import java.util.Scanner;

/**
 *
 * @author Xavier
 */
public class Launcher {

    /**
     * @param args
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
                case "sender":
                    {
                        String u = "Usage: spout [OPTIONS] 'ADDRESS'";
                        String d = "Send messages to the specified address.";
                        new Sender(args2, u, d);
                        break;
                    }
                case "receiver":
                    {
                        String u = "Usage: drain [OPTIONS] 'ADDRESS'";
                        String d = "Drains messages from the specified address.";
                        new Receiver(args2, u, d);
                        break;
                    }
                case "receiverListener":
                    {
                        String u = "Usage: drain [OPTIONS] 'ADDRESS'";
                        String d = "Drains messages from the specified address.";
                        new ReceiverListener();
                        break;
                    }
            }
        }
    }
}