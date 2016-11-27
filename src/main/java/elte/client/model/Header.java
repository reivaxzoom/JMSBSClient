package elte.client.model;

import java.io.Serializable;
import java.util.Date;

/**
 *Represents the client data to be labeled into the object message 
 *
 * @author Xavier
 */
public class Header implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private int id;
    private String ClientName;
    private String ClientAddress;
    private String phone;
    private String country;
    private String contactName;
    private String contactPhone;
    private String prio;
    private Date expDelivery;
    private String comments;
    private String deliverAddress;
    private String category;
    private Boolean frecuent;

    public Boolean getFrecuent() {
        return frecuent;
    }

    public void setFrecuent(Boolean frecuent) {
        this.frecuent = frecuent;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String ClientName) {
        this.ClientName = ClientName;
    }

    public String getClientAddress() {
        return ClientAddress;
    }

    public void setClientAddress(String ClientAddress) {
        this.ClientAddress = ClientAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    public Date getExpDelivery() {
        return expDelivery;
    }

    public void setExpDelivery(Date expDelivery) {
        this.expDelivery = expDelivery;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
    
}
