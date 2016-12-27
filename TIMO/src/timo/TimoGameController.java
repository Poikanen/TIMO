/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Toivo
 */
public class TimoGameController implements Initializable {

    @FXML
    private WebView wvMap;
    @FXML
    private TextArea packageInfoField;
    @FXML
    private ListView<Package> packageListView;

    
    private DataBuilder db;
    private Storage storage;
    private ArrayList<Package> smartPosts;
    private ArrayList<Item> items;
    
    private String timo;
    private Package lastPackage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        wvMap.getEngine().load(getClass().getResource("index.html").toExternalForm());
        db = new DataBuilder();
        storage = Storage.getInstance();
        timo = "1";
        smartPosts = new ArrayList();
        //smartPosts.addAll(db.getAllSmartPosts());
        smartPosts.add(new PackageFirstCategory(new Item(), db.getAllSmartPosts().get(10),
                db.getAllSmartPosts().get(50)));
        smartPosts.add(new PackageSecondCategory(new Item(), db.getAllSmartPosts().get(60),
                db.getAllSmartPosts().get(70)));
        smartPosts.add(new PackageThirdCategory(new Item(), db.getAllSmartPosts().get(80),
                db.getAllSmartPosts().get(90)));
        packageListView.getItems().addAll(smartPosts);
        
        //Add packages
        //Start clock
    }
    
    private void drawPath(Package toDraw){
        String script = "document.createPath(";
        //Get coordinates as String-array
        script += "[\"" + toDraw.getStart().getGp().getLat() + "\", ";
        script += "\"" + toDraw.getStart().getGp().getLon() + "\", ";
        script += "\"" + toDraw.getDestination().getGp().getLat() + "\", ";
        script += "\"" + toDraw.getDestination().getGp().getLon() + "\"], ";
        script += "\"" + toDraw.getColor() + "\", ";
        script += toDraw.getCategory() + ")";
        wvMap.getEngine().executeScript(script);
    }
    
    private void relocateTimo(Package whereTo){
        if(lastPackage == null){
            lastPackage = whereTo;
            return;
        }
        String script = "document.createPath(";
        //Get coordinates as String-array
        script += "[\"" + lastPackage.getDestination().getGp().getLat() + "\", ";
        script += "\"" + lastPackage.getDestination().getGp().getLon() + "\", ";
        script += "\"" + whereTo.getStart().getGp().getLat() + "\", ";
        script += "\"" + whereTo.getStart().getGp().getLon() + "\"], ";
        script += "\"" + lastPackage.getColor() + "\", ";
        script += lastPackage.getCategory() + ")";
        wvMap.getEngine().executeScript(script);
    }

    private void changeTimo(){
        if(this.timo.equals("1")){
            timo = "2";
        }else if(this.timo.equals("2")){
            timo = "3";
        }else if(this.timo.equals("3")){
            timo = "1";
        }
    }
    
    @FXML
    private void sendTimo(ActionEvent event) {
        if(!timo.equals(packageListView.getSelectionModel().getSelectedItem().getCategory())){
            packageInfoField.setText("Valitse Timolle sopiva paketti.\n" + 
                    packageInfoField.getText());
            return;
        }
        //Go to packages start location
        relocateTimo(packageListView.getSelectionModel().getSelectedItem());
        //Send package
        drawPath(packageListView.getSelectionModel().getSelectedItem());
        //Wait
        //If broken, penalty
        //Change TIMO
        changeTimo();
    }
    
    private Collection createPackages(){
        ArrayList<Package> createdPackages = new ArrayList();
        Package pac;
        Random rand = new Random();
        for (int i=0; i<50; i++){
            pac = new PackageFirstCategory(items.get(rand.nextInt(4)),
                    db.getAllSmartPosts().get(rand.nextInt(smartPosts.size())),
                    db.getAllSmartPosts().get(rand.nextInt(smartPosts.size())));
            createdPackages.add(pac);
        }
        return createdPackages;
    }

    @FXML
    private void updateInfoBox(MouseEvent event) {
        packageInfoField.setText("TIMO: " + timo + "\n" + 
                packageListView.getSelectionModel().getSelectedItem().toString() + "\n" +
                "Paketin luokka: " + packageListView.getSelectionModel().getSelectedItem().getCategory());
    }
}
