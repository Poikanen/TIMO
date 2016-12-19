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
public class Item {
    
    protected boolean fragile, broken;
    protected String name;

    public Item() {
        this.fragile = false;
        this.name = "General item";
        this.broken = false;
    }
    
    public Item(boolean fragile) {
        this.fragile = fragile;
        this.name = "General item";
        this.broken = false;
    }

    public Item(boolean fragile, String name) {
        this.fragile = fragile;
        this.name = name;
    }
    
    public Item(Item copy){
        this.broken = copy.broken;
        this.name = copy.name;
        this.fragile = copy.fragile;
    }

    public boolean isFragile() {
        return fragile;
    }

    public boolean isBroken() {
        return broken;
    }

    public String getName() {
        return name;
    }
    
    public void throwAround(){
        if (this.fragile){
            this.broken = true;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
