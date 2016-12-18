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
    private Object item;
    private SmartPost start, destination;
    private boolean sent;

    public Package(Object item, SmartPost start, SmartPost destination) {
        this.item = item;
        this.start = start;
        this.destination = destination;
        this.sent = false;
    }

    protected Package(Package copy) {
        this.item = copy.getItem();
        this.start = copy.getStart();
        this.destination = copy.getDestination();
        this.sent = copy.isSent();
    }
    
    abstract public Package getCopy();

    public Object getItem() {
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

    public boolean isSent() {
        return sent;
    }
    
    public void send(){
        sent = true;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public void setStart(SmartPost start) {
        this.start = start;
    }

    public void setDestination(SmartPost destination) {
        this.destination = destination;
    }
    
    abstract public String getCategory();
    
    @Override
    abstract public String toString();
}
