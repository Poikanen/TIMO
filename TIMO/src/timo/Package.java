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
    protected double maxWidth,maxHeight,maxDepth;

    public Package(Item item, SmartPost start, SmartPost destination) {
        this.item = item;
        this.start = start;
        this.destination = destination;
        this.sent = false;
        this.sendMessage = "";
        this.status = "storage";
        maxWidth = 60d;
        maxHeight =36d;
        maxDepth =11d;
    }

    public Package(Item item, SmartPost start, SmartPost destination, boolean sent) {
        this.item = item;
        this.start = start;
        this.destination = destination;
        this.sent = sent;
        this.sendMessage = "";
    }

    protected Package(Package copy) {
        //new **** to avoid problems with copying only references
        this.item = new Item(copy.getItem());
        this.start = new SmartPost(copy.getStart());
        this.destination = new SmartPost(copy.getDestination());
        this.sent = copy.isSent();
        this.status = copy.getStatus();
        this.maxWidth = copy.maxWidth;
        this.maxHeight = copy.maxHeight;
        this.maxDepth = copy.maxDepth;
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
    
    public boolean doesFit(Item item){
        if ((item.width <= maxWidth && item.height <= maxHeight && item.depth <= maxDepth)||
            (item.width <= maxWidth && item.depth <= maxHeight && item.height <= maxDepth)||
            (item.height <= maxWidth && item.width <= maxHeight && item.depth <= maxDepth)||
            (item.height <= maxWidth && item.depth <= maxHeight && item.width <= maxDepth)||
            (item.depth <= maxWidth && item.height <= maxHeight && item.width <= maxDepth)||
            (item.depth <= maxWidth && item.width <= maxHeight && item.height <= maxDepth)){
        return true;
        }      
        this.sendMessage = "Tavara ei mahdu pakettiin!";
        return false;
    }
    
    abstract public Package getCopy();
    
    abstract public boolean send();
    
    abstract public String getCategory();
    
    abstract public String getColor();
    
    //@Override
    //abstract public String toString();
}
