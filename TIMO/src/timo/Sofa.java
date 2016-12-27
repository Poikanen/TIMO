/*
 * Authors: 	Toivo Mattila
 *		Tommi Wäänänen
 *
 * Last modified: 2016-12-27
 */
package timo;

public class Sofa extends Item{
    
    public Sofa() {
        super(false, "Sofa", 320.0d, 90.0d, 90d, 60000.0d);
    }
    
    public Sofa(boolean broken) {
        super(false, "Sofa", broken, 320.0d, 90.0d, 90d, 60000.0d);
    }
    
}
