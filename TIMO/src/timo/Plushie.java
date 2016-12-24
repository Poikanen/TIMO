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
public class Plushie extends Item{
    
    public Plushie(){
        super(false, "Plushie",10.0d, 7.0d, 7.0d, 150.0d);
    }
    
    public Plushie(boolean broken){
        super(false, "Plushie", broken, 10.0d, 7.0d, 7.0d, 150.0d);
    }
}
