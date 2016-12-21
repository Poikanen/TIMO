/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timo;

import java.util.ArrayList;

/**
 *
 * @author Toivo
 */
public class Storage {
    
    private static Storage instance = null;
    private ArrayList<Package> allPackages;
    
    private Storage(){
        allPackages = new ArrayList<Package>();
    }

    public static Storage getInstance() {
        if(instance == null){
            instance = new Storage();
        }
        return instance;
    }
    
    public void addPackage(Package pkg){
        allPackages.add(pkg);
    }
    
    public ArrayList<Package> sendPackages(){
        ArrayList<Package> pkgToSend = new ArrayList<Package>();
        for(int i = 0; i < allPackages.size(); i++){
            if(allPackages.get(i).send()){
                pkgToSend.add(allPackages.get(i));
            }
        }
        return pkgToSend;
    }
}
