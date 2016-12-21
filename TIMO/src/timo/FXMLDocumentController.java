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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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
    private void handleOpenAddPackageWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPackageWindow.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene((Pane)loader.load()));
            loader.<AddPackageWindowController>getController().initData(db);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
