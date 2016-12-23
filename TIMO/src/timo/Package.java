/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timo;

/**
 *
 * @author Toivo
 */
abstract public class Package {
    protected Item item;
    protected SmartPost start, destination;
    protected boolean sent;
    protected String sendMessage;
    protected String status; // "storage", "delivery", "destination"

    public Package(Item item, SmartPost start, SmartPost destination) {
        this.item = item;
        this.start = start;
        this.destination = destination;
        this.sent = false;
        this.sendMessage = "";
        this.status = "storage";
    }

    protected Package(Package copy) {
        //new **** to avoid problems with copying only references
        this.item = new Item(copy.getItem());
        this.start = new SmartPost(copy.getStart());
        this.destination = new SmartPost(copy.getDestination());
        this.sent = copy.isSent();
        this.status = copy.getStatus();
    }
    
    public Package(){
        this.item = new Item();
        this.start = new SmartPost();
        this.destination = new SmartPost();
        this.sent = false;
        this.status = "storage";
    }

    public Item getItem() {
        return item;
    }

    public SmartPost getStart() {
        return start;
    }

    public SmartPost getDestination() {
        return destination;
    }
    
    public SmartPost getLocation(){
        if(sent){
            return destination;
        }
        return start;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    public boolean isSent() {
        return sent;
    }
    

    public void setItem(Item item) {
        this.item = item;
    }

    public void setStart(SmartPost start) {
        this.start = start;
    }

    public void setDestination(SmartPost destination) {
        this.destination = destination;
    }

    public String getSendMessage() {
        return sendMessage;
    }
    
    abstract public Package getCopy();
    
    abstract public boolean send();
    
    abstract public String getCategory();
    
    abstract public String getColor();
    
    //@Override
    //abstract public String toString();
}
