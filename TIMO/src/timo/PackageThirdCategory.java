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
public class PackageThirdCategory extends Package{

    public PackageThirdCategory(Object item, SmartPost start, SmartPost destination) {
        super(item, start, destination);
    }
    
    private PackageThirdCategory(Package copy){
        super(copy);
    }
    
    @Override
    public Package getCopy() {
        return new PackageThirdCategory(this);
    }

    @Override
    public String getCategory() {
        return "3";
    }

    @Override
    public String toString() {
        return "3";
    }
    
}
