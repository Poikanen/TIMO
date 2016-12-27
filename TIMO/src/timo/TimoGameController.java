/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
    private ListView<?> packageListView;

    
    private DataBuilder db;
    private Storage storage;
    private ArrayList<SmartPost> smartPosts;
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
        packageListView.getItems().addAll((Collection)smartPosts);
        
    }    

    @FXML
    private void sendTimo(ActionEvent event) {
        
    }
    
}
