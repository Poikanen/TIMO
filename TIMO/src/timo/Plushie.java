/* TIMO, LUT Olio-ohjelmointi 2016
 * Plushie.java
 * Authors: 	Toivo Mattila
 *		Tommi Wäänänen
 *
 * Last modified: 2016-12-27
 */
package timo;

public class Plushie extends Item{
    
    public Plushie(){
        super(false, "Plushie",10.0d, 7.0d, 7.0d, 150.0d);
    }
    
    public Plushie(boolean broken){
        super(false, "Plushie", broken, 10.0d, 7.0d, 7.0d, 150.0d);
    }
}
