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

    @Override
    public String getCategory() {
        return "3";
    }
    
}