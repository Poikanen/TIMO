/*
 * Authors: 	Toivo Mattila
 *		Tommi Wäänänen
 *
 * Last modified: 2016-12-27
 */
package timo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
 class Storage {
    
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
    
    public ArrayList<Package> getUnsentPackages(){
        ArrayList<Package> pkgToDraw = new ArrayList<Package>();
        for(int i = 0; i < allPackages.size(); i++){
            if(!allPackages.get(i).isSent()){
                pkgToDraw.add(allPackages.get(i));
            }
        }
        return pkgToDraw;
    }
    
    public String getLog(){
        
        //Pakettiluokka
        //Lähtö
        //Kohde
        //Lähetetty
        //(Missä nyt)
        //Esine:
        //  Nimi
        //      Rikkoutuva
        //  Rikki?
        
        String log = "";
        log += "Paketteja yhteensä: " + String.valueOf(allPackages.size()) + "\n";
        for(int i = 0; i < allPackages.size(); i++){
            //Pakettiluokka
            log += "Pakettiluokka: " + allPackages.get(i).getCategory() + "\n";
            //Lähtö
            log += "Lähtö: " + allPackages.get(i).getStart().toString() + "\n";
            //Kohde
            log += "Kohde: " + allPackages.get(i).getDestination().toString() + "\n";
            
            //Onko lähetetty
            if(allPackages.get(i).isSent()){
                log += "Lähetetty\n";
            }else{
                log += "Lähettämättä\n";
            }
            
            //Sisällä oleva esine
            //Nimi
            log += allPackages.get(i).item.getName() + "\n";
            //Rikkoutuva
            if(allPackages.get(i).getItem().isFragile()){
                log += "Rikkoutuvaa\n";
            }else{
                log += "Ei rikkoudu\n";
            }
            //Rikki?
            if(allPackages.get(i).getItem().isBroken()){
                log += "Rikki\n";
            }else{
                log += "Ehjä\n";
            }
            //  Syvyys
            log += "Syvyys: " + String.valueOf(allPackages.get(i).getItem().getDepth()) + "\n";
            //  Leveys
            log += "Leveys: " + String.valueOf(allPackages.get(i).getItem().getWidth()) + "\n";
            //  Korkeus
            log += "Korkeus: " + String.valueOf(allPackages.get(i).getItem().getHeight()) + "\n";
            //  Paino
            log += "Paino: " + String.valueOf(allPackages.get(i).getItem().getWeight()) + "\n";
            //Matkan pituus
            log += "Matkan pituus: " + String.valueOf(Math.round(allPackages.get(i).getStart().getGp().getDistanceTo(allPackages.get(i).getDestination().getGp())));
            log += "km\n\n";
        }
        return log;
    }
    
    public boolean writeLogToFile(){
        try (BufferedWriter write = new BufferedWriter(new FileWriter("istunto.log"))) {
            write.write(this.getLog());
        } catch (IOException ex) {
            Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean readLogFromFile(){
        System.out.println("Started to read log from file");
        BufferedReader reader;
        
        try {
            reader = new BufferedReader(new FileReader("istunto.log"));
        } catch (FileNotFoundException ex) {
            //File doesn't exist, create it
            //If creating it fails, return false and stop trying to read from log
            if(writeLogToFile() == false){
                return false;
            }
            //If creating file succeeded, call it self and try again and return result
            return readLogFromFile();
        }
        
        //Pakettiluokka
        //Lähtö
        //Kohde
        //Lähetetty
        //(Missä nyt)
        //Esine:
        //  Nimi
        //      Rikkoutuva
        //  Rikki?
        //  Syvyys
        //  Leveys
        //  Korkeus
        //  Paino
        //Read from the file
        DataBuilder db = new DataBuilder();
        String packageClass, smartStart, smartEnd, bSent, itemName, bFragile, bBroken, depth, width, height, weight;
        Package pkg;
        try {
            //First line is number of packages
            reader.readLine();
            while((packageClass = reader.readLine()) != null &&
                  (smartStart = reader.readLine()) != null &&
                  (smartEnd = reader.readLine()) != null &&
                  (bSent = reader.readLine()) != null &&
                  (itemName = reader.readLine()) != null &&
                  (bFragile = reader.readLine()) != null &&
                  (bBroken = reader.readLine()) != null &&
                  (depth = reader.readLine()) != null &&
                  (width = reader.readLine()) != null &&
                  (height = reader.readLine()) != null &&
                  (weight = reader.readLine()) != null){
                    //  Nimi
                    reader.readLine();
                    //Lähtö
                SmartPost start = db.getSmartPost(smartStart.substring(7));
                    //Kohde
                SmartPost end = db.getSmartPost(smartStart.substring(7));
                    //Lähetetty
                boolean sent = true;
                if(bSent.equals("Lähettämättä")){
                    sent = false;
                }
                    //  Rikkoutuva
                boolean fragile = false;
                if(bFragile.equals("Rikkoutuvaa")){
                    fragile = true;
                }
                    //  Rikki?
                boolean broken = false;
                if(bBroken.equals("Rikki")){
                    broken = true;
                }
                //  Syvyys
                double dDepth = Double.valueOf(depth.substring(8));
                //  Leveys
                double dWidth = Double.valueOf(width.substring(8));
                //  Korkeus
                double dHeight = Double.valueOf(height.substring(9));
                //  Paino
                double dWeight = Double.valueOf(weight.substring(7));
                    //  Esine (check the class, there's dimensions, gotta add them):
                Item item;
                switch (itemName) {
                    case "Laptop":
                        item = new Laptop(broken);
                        break;
                    case "Plushie":
                        item = new Plushie(broken);
                        break;
                    case "Sofa":
                        item = new Sofa(broken);
                        break;
                    case "Teacup":
                        item = new Teacup(broken);
                        break;
                    default:
                        item = new Item(fragile, itemName, broken, dWidth, dHeight, dDepth, dWeight);
                        break;
                }
                    //Pakettiluokka
                switch (packageClass) {
                    case "Pakettiluokka: 1":
                        pkg = new PackageFirstCategory(item, start, end, sent);
                        break;
                    case "Pakettiluokka: 2":
                        pkg = new PackageFirstCategory(item, start, end, sent);
                        break;
                    case "Pakettiluokka: 3":
                        pkg = new PackageFirstCategory(item, start, end, sent);
                        break;
                    default:
                        //Something wrong
                        System.out.println("Problem in creating package.");
                        return false;
                }
                this.addPackage(pkg);
                //Distance
                reader.readLine();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Close and return
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public ArrayList<Package> getAllPackages() {
        return allPackages;
    }
    
}
