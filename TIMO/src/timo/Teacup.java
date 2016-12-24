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
public class Teacup extends Item{
    
    public Teacup(){
        super(true, "Teacup", 5.0d, 7.0d, 7.0d, 250.0d);
    }
    
    public Teacup(boolean broken){
        super(true, "Teacup", broken, 5.0d, 7.0d, 7.0d, 250.0d);
    }
    
}
