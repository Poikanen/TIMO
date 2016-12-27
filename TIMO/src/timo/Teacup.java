/* TIMO, LUT Olio-ohjelmointi 2016
 * Teacup.java
 * Authors: 	Toivo Mattila
 *		Tommi Wäänänen
 *
 * Last modified: 2016-12-27
 */
package timo;

public class Teacup extends Item{
    
    public Teacup(){
        super(true, "Teacup", 5.0d, 7.0d, 7.0d, 250.0d);
    }
    
    public Teacup(boolean broken){
        super(true, "Teacup", broken, 5.0d, 7.0d, 7.0d, 250.0d);
    }
    
}
