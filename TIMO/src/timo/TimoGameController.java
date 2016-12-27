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
    private ListView<Package> packageListView;

    
    private DataBuilder db;
    private Storage storage;
    private ArrayList<Package> smartPosts;
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
        //smartPosts.addAll(db.getAllSmartPosts());
        smartPosts.add(new PackageFirstCategory(new Item(), db.getAllSmartPosts().get(10),
                db.getAllSmartPosts().get(24)));
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

    @FXML
    private void sendTimo(ActionEvent event) {
        //Go to packages start location
        //Send package
        drawPath(packageListView.getSelectionModel().getSelectedItem());
        //Wait
        //If broken, penalty
        //Change TIMO
    }
    
}
