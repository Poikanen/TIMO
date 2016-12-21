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
import javafx.scene.control.TextArea;
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
    private ComboBox<Item> cbItem;
    @FXML
    private ComboBox<Package> cbPackage;
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
    @FXML
    private TextArea textInfoBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wvMap.getEngine().load(getClass().getResource("index.html").toExternalForm());
        
        db = new DataBuilder();
        storage = Storage.getInstance();
        
        cbSmartPost.getItems().addAll(db.getAllSmartPosts());
        cbStartCity.getItems().addAll(db.getCities());
        cbDestinationCity.getItems().addAll(db.getCities());
        
        cbPackage.getItems().add(new PackageFirstCategory(new Item(), new SmartPost(), new SmartPost()));
        cbPackage.getItems().add(new PackageSecondCategory(new Item(), new SmartPost(), new SmartPost()));
        cbPackage.getItems().add(new PackageThirdCategory(new Item(), new SmartPost(), new SmartPost()));
        
        cbItem.getItems().add(new Item());
        cbItem.getItems().add(new Sofa());
        cbItem.getItems().add(new Laptop());
        cbItem.getItems().add(new Teacup());
        cbItem.getItems().add(new Plushie());
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
        textInfoBox.setText("SmartPost lisätty.\n");
    }

    @FXML
    private void handleCreatePacket(ActionEvent event) {
        Package tmpPkg = cbPackage.getValue().getCopy();
        tmpPkg.setItem(new Item(cbItem.getValue()));
        tmpPkg.setStart(new SmartPost(cbStartSmartPost.getValue()));
        tmpPkg.setDestination(new SmartPost(cbDestinationSmartPost.getValue()));
        
        storage.addPackage(tmpPkg);
        textInfoBox.setText("Paketti luotu.\n");
    }

    @FXML
    private void handleSendPackets(ActionEvent event) {
        ArrayList<Package> tmpToSend = storage.sendPackages();
        textInfoBox.setText("");
        for(int i = 0; i < tmpToSend.size(); i++){
            String script = "document.createPath(";
            //Get coordinates as String-array
            script += "[\"" + tmpToSend.get(i).getStart().getGp().getLat() + "\", ";
            script += "\"" + tmpToSend.get(i).getStart().getGp().getLon() + "\", ";
            script += "\"" + tmpToSend.get(i).getDestination().getGp().getLat() + "\", ";
            script += "\"" + tmpToSend.get(i).getDestination().getGp().getLon() + "\"], ";
            script += "\"" + tmpToSend.get(i).getColor() + "\", ";
            script += tmpToSend.get(i).getCategory() + ")";
            wvMap.getEngine().executeScript(script);
            textInfoBox.setText(tmpToSend.get(i).getSendMessage() + textInfoBox.getText());
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

    @FXML
    private void displayClassInfo(ActionEvent event) {
        if(cbPackage.getValue().getCategory().equals("1")){
            textInfoBox.setText("1. luokan paketit kulkevat nopeasti alle 150km päähän.\n");
        }else if(cbPackage.getValue().getCategory().equals("2")){
            textInfoBox.setText("2. luokan paketit kulkevat luotettavasti kaikkialle Suomeen.\n");
        }else if(cbPackage.getValue().getCategory().equals("3")){
            textInfoBox.setText("3. luokan paketteja käytetään TIMOjen stressinpurkuun joten sisäsltö saattaa hajota matkalla.\n");
        }
    }

    @FXML
    private void displayLog(ActionEvent event) {
        textInfoBox.setText(storage.getLog());
    }
}
