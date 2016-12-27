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
public class Teapot extends Item{
    
    public Teapot() {
        super(false, "Teapot", 15.d, 40.d, 20.d, 270.d);
    }
    
    public Teapot(boolean broken) {
        super(false, "Teapot", broken, 15.d, 40.d, 20.d, 270.d);
    }
    
}
