/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        ArrayList<Package> pkgToDraw = new ArrayList<Package>();
        for(int i = 0; i < allPackages.size(); i++){
            if(allPackages.get(i).send()){
                pkgToDraw.add(allPackages.get(i));
            }
        }
        return pkgToDraw;
    }
    
    public String getLog(){
        String log = "";
        log += "Paketteja yhteensä: " + String.valueOf(allPackages.size()) + "\n";
        for(int i = 0; i < allPackages.size(); i++){
            //Sisällä oleva esine
            log += "\n" + allPackages.get(i).item.getName() + "\n";
            //Onko lähetetty
            if(allPackages.get(i).isSent()){
                log += "Paketti on lähetetty.\n";
            }else{
                log += "Pakettia ei vielä ole lähetetty.\n";
            }
            //Rikki, ehjä, rikkoutuva
            if(allPackages.get(i).item.isBroken()){
                log += "Rikki\n";
            }else{
                if(allPackages.get(i).item.isFragile()){
                    log += "Särkyvää, ehjä\n";
                }
            }
            //Lähtö
            log += "Lähtö: " + allPackages.get(i).getStart().toString() + "\n";
            //Kohde
            log += "Kohde: " + allPackages.get(i).getDestination().toString() + "\n";
            //Sijainti nyt
            log += "Sijainti nyt: " + allPackages.get(i).getLocation().toString() + "\n";
            //Matkan pituus
            log += "Matkan pituus: " + String.valueOf(Math.round(allPackages.get(i).getStart().getGp().getDistanceTo(allPackages.get(i).getDestination().getGp())));
            log += "km\n";
        }
        return log;
    }
    
    public void writeLogToFile(){
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("istunto.log"));
            write.write(this.getLog());
            write.close();
        } catch (IOException ex) {
            Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
