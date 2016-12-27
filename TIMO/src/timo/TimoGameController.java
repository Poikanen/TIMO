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
    private ArrayList<SmartPost> smartPosts;
    private ArrayList<Item> items;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        wvMap.getEngine().load(getClass().getResource("index.html").toExternalForm());
        db = new DataBuilder();
        storage = Storage.getInstance();
        smartPosts = new ArrayList();
        smartPosts.addAll(db.getAllSmartPosts());
        
        
        items = new ArrayList();
        items.add(new Teapot());
        items.add(new Laptop());
        items.add(new Teacup());
        items.add(new Plushie());
        
        packageListView.getItems().addAll(createPackages());
    }    

    @FXML
    private void sendTimo(ActionEvent event) {
        packageInfoField.setText(packageListView.getSelectionModel().getSelectedItem().toString());
    }
    
    private Collection createPackages(){
        ArrayList createdPackages = new ArrayList();
        Package pac;
        Random rand = new Random();
        for (int i=0; i<50; i++){
            pac = new PackageFirstCategory(items.get(rand.nextInt(4)),smartPosts.get(rand.nextInt(smartPosts.size())),smartPosts.get(rand.nextInt(smartPosts.size())));
            createdPackages.add(pac);
        }
        return createdPackages;
    }

    @FXML
    private void updateInfoBox(MouseEvent event) {
        packageInfoField.setText(packageListView.getSelectionModel().getSelectedItem().toString());
    }
}
