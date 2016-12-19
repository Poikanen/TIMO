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
public class PackageSecondCategory extends Package{

    public PackageSecondCategory(Item item, SmartPost start, SmartPost destination) {
        super(item, start, destination);
    }
    
    private PackageSecondCategory(Package copy){
        super(copy);
    }
    
    @Override
    public Package getCopy() {
        return new PackageSecondCategory(this);
    }

    @Override
    public String getCategory() {
        return "2";
    }

    @Override
    public String toString() {
        return "2";
    }

    @Override
    public String send() {
        this.sent = true;
        return "Paketti lähetetty.\n";
    }

    @Override
    public String getColor() {
        return "blue";
    }
}
