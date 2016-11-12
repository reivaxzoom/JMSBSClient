package elte.client.model;

import java.io.Serializable;
import javax.money.NumberValue;

/**
 * Define the client request for creating JMS messages
 * @author Xavier
 */
public class Item implements Serializable{
     private static final long serialVersionUID = 1L;

    private String name;
    private int amount ;
    private NumberValue unityPrice;
    private String category;

    public Item(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public Item(String name, int amount, NumberValue unityPrice) {
        this.name = name;
        this.amount = amount;
        this.unityPrice = unityPrice;
    }
    
     public Item(String name, int amount, String category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }
    
    
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NumberValue getUnityPrice() {
        return unityPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
   @Override
    public String toString() {
        return "ClientRequest{" + "name=" + name + ", amount=" + amount + ", category=" + category + '}';
    }

    
    
    
    
}
