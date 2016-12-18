/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebView;

/**
 *
 * @author tommi
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private WebView wvMap;
    @FXML
    private ComboBox<SmartPost> cbSmartPost;
    @FXML
    private ComboBox<?> cbItem;
    @FXML
    private ComboBox<?> cbPacket;
    @FXML
    private ComboBox<String> cbStartCity;
    @FXML
    private ComboBox<SmartPost> cbStartSmartPost;
    @FXML
    private ComboBox<String> cbDestinationCity;
    @FXML
    private ComboBox<SmartPost> cbDestinationSmartPost;
    
    private DataBuilder db;
    private Storage storage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wvMap.getEngine().load(getClass().getResource("index.html").toExternalForm());
        db = new DataBuilder();
        storage = Storage.getInstance();
        cbSmartPost.getItems().addAll(db.getAllSmartPosts());
        cbStartCity.getItems().addAll(db.getCities());
        cbDestinationCity.getItems().addAll(db.getCities());
    }

    @FXML
    private void handleAddSmartPost(ActionEvent event) {
        SmartPost SP = cbSmartPost.getValue();
        //JS-script: document.goToLocation("<address>, <postnumber> <city>", "<additional info>", "<color>")
        //Construct the script
        String script = "document.goToLocation(";
        //First parameter, "<address>, 
        script += "\"" + SP.getAddress() + ", ";
        //<postnumber> 
        script += SP.getPostnumber() + " ";
        //<city>", 
        script += SP.getCity() + "\",";
        //Second parameter "<Additional info>", 
        script += " \"" + SP.getAvailability() + "\",";
        //Third parameter "<color>" and close with )
        script += "\"red\"" + ")";
        //Uncomment to see the syntax
        //System.out.println(script);
        wvMap.getEngine().executeScript(script);
    }

    @FXML
    private void handleCreatePacket(ActionEvent event) {
        
        storage.addPackage(new PackageFirstCategory(new Object(false), cbStartSmartPost.getValue(), cbDestinationSmartPost.getValue()));
    }

    @FXML
    private void handleSendPackets(ActionEvent event) {
        ArrayList<Package> tmpToSend = storage.sendPackages();
        for(int i = 0; i < tmpToSend.size(); i++){
            String script = "document.createPath(";
            //Get coordinates as String-array
            script += "[\"" + tmpToSend.get(i).getStart().getGp().getLat() + "\", ";
            script += "\"" + tmpToSend.get(i).getStart().getGp().getLon() + "\", ";
            script += "\"" + tmpToSend.get(i).getDestination().getGp().getLat() + "\", ";
            script += "\"" + tmpToSend.get(i).getDestination().getGp().getLon() + "\"], ";
            if(tmpToSend.get(i).getCategory().equals("1")){
                //Color
                script += "\"green\", ";
                //Class
                script += "1)";
            }else if(tmpToSend.get(i).getCategory().equals("2")){
                //Color
                script += "\"blue\", ";
                //Class
                script += "2)";
            
            }else if(tmpToSend.get(i).getCategory().equals("3")){
                //Color
                script += "\"red\", ";
                //Class
                script += "3)";
            
            }else{
                //Throw error or something
            }
            wvMap.getEngine().executeScript(script);
        }
    }

    @FXML
    private void handleEmptyPaths(ActionEvent event) {
        wvMap.getEngine().executeScript("document.deletePaths()");
    }

    @FXML
    private void handleStartCityChange(ActionEvent event) {
        cbStartSmartPost.getItems().clear();
        cbStartSmartPost.getItems().addAll(db.getCitysSmartPosts(cbStartCity.getValue()));
    }

    @FXML
    private void handleDestinationCityChange(ActionEvent event) {
        cbDestinationSmartPost.getItems().clear();
        cbDestinationSmartPost.getItems().addAll(db.getCitysSmartPosts(cbDestinationCity.getValue()));
    }
}
