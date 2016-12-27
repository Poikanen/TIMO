/*
 * Authors: 	Toivo Mattila
 *		Tommi Wäänänen
 *
 * Last modified: 2016-12-27
 */
package timo;

public class Laptop extends Item{
    
    public Laptop() {
        super(false, "Laptop", 40.0d, 3.0d, 30.0d, 1500.0d);
    }
    
    public Laptop(boolean broken) {
        super(false, "Laptop", broken, 40.0d, 3.0d, 30.0d, 1500.0d);
    }
    
}
