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
    protected double width, depth, height, weight;

    public Item() {
        this.fragile = false;
        this.name = "General item";
        this.broken = false;
        this.width = 1.0d;
        this.depth = 1.0d;
        this.height = 1.0d;
        this.weight = 1.0d;
    }
    
    public Item(boolean fragile) {
        this.fragile = fragile;
        this.name = "General item";
        this.broken = false;
        this.width = 1.0d;
        this.depth = 1.0d;
        this.height = 1.0d;
        this.weight = 1.0d;
    }

    public Item(boolean fragile, String name, double width, double height, double depth, double weight) {
        this.fragile = fragile;
        this.name = name;
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.weight = weight;
        this.broken = false;
    }
    
    public Item(boolean fragile, String name, boolean broken, double width, double height, double depth, double weight) {
        this.fragile = fragile;
        this.name = name;
        this.broken = broken;
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.weight = weight;
    }
    
    public Item(Item copy){
        this.fragile = copy.fragile;
        this.name = copy.name;
        this.broken = copy.broken;
        this.width = copy.width;
        this.depth = copy.depth;
        this.height = copy.height;
        this.weight = copy.weight;
        
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

    public double getWidth() {
        return width;
    }

    public double getDepth() {
        return depth;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }
    
}
