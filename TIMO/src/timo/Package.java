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
    
    abstract public String getCategory();
}
