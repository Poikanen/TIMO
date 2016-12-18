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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

/**
 *
 * @author tommi
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private WebView wvMap;
    @FXML
    private ComboBox<?> cbSmartPost;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wvMap.getEngine().load(getClass().getResource("index.html").toExternalForm());
    }

    @FXML
    private void handleAddSmartPost(ActionEvent event) {
        //wvMap.getEngine().executeScript("document.goToLocation(/*Add parameters*/)");
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
