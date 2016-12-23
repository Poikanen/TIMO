/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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
    @FXML
    private RadioButton rbFirstClass;
    @FXML
    private RadioButton rbThirdClass;
    @FXML
    private RadioButton rbSecondsClass;
    @FXML
    private ComboBox<?> cbPacket;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wvMap.getEngine().load(getClass().getResource("index.html").toExternalForm());
        
        db = new DataBuilder();
        storage = Storage.getInstance();
        
        cbSmartPost.getItems().addAll(db.getAllSmartPosts());
        cbStartCity.getItems().addAll(db.getCities());
        cbDestinationCity.getItems().addAll(db.getCities());
        
        //cbItem.getItems().add(new Item());
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

    private void handleSendPacket(ActionEvent event) {
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
    private void displayLog(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogWindow.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene((Pane)loader.load()));
            loader.<LogWindowController>getController().initData(storage.getLog());
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleOpenAddItemWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddItemWindow.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene((Pane)loader.load()));
            loader.<AddItemWindowController>getController().initData(cbItem);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void handleClassChoise(ActionEvent event) {
    }

    @FXML
    private void handleStorePacket(ActionEvent event) {
    }

    @FXML
    private void handleSendPacketFromStorage(ActionEvent event) {
    }

    @FXML
    private void handleSendAllPackets(ActionEvent event) {
    }
}
