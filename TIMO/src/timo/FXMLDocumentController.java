/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timo;

import java.net.URL;
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
    private ComboBox<?> cbStartCity;
    @FXML
    private ComboBox<?> cbDestinationCIty;
    @FXML
    private ComboBox<?> cbStartSmartPost;
    @FXML
    private ComboBox<?> cbDestinationSmartPost;
    
    private DataBuilder db;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wvMap.getEngine().load(getClass().getResource("index.html").toExternalForm());
        db = new DataBuilder();
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
    }

    @FXML
    private void handleCreatePacket(ActionEvent event) {
        //TODO
    }

    @FXML
    private void handleSendPackets(ActionEvent event) {
        //wvMap.getEngine().executeScript("document.createPath(/*Add parameters*/)");
    }

    @FXML
    private void handleEmptyPaths(ActionEvent event) {
        wvMap.getEngine().executeScript("document.deletePaths()");
    }
}
