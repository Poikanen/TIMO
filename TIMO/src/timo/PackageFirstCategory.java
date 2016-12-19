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
public class PackageFirstCategory extends Package{
    
    public PackageFirstCategory(Item item, SmartPost start, SmartPost destination) {
        super(item, start, destination);
    }
    
    private PackageFirstCategory(Package copy){
        super(copy);
    }
    
    @Override
    public Package getCopy() {
        return new PackageFirstCategory(this);
    }

    @Override
    public String getCategory() {
        return "1";
    }

    @Override
    public String toString() {
        return "1";
    }

    @Override
    public String send() {
        this.sent = true;
        return "Paketti lähetetty.\n";
    }

    @Override
    public String getColor() {
        return "green";
    }
}
