/* TIMO, LUT Olio-ohjelmointi 2016
 * TimoGameController.java
 * Authors: 	Toivo Mattila
 *		Tommi Wäänänen
 *
 * Last modified: 2016-12-27
 */
package timo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

public class TimoGameController implements Initializable {

    @FXML
    private WebView wvMap;
    @FXML
    private TextArea packageInfoField;
    @FXML
    private ListView<Package> packageListView;

    
    private DataBuilder db;
    private Storage storage;
    private ArrayList<Package> packagesToSend;
    private ArrayList<Item> items;

    private Package lastPackage;
    private double totalDistance;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        wvMap.getEngine().load(getClass().getResource("index.html").toExternalForm());

        db = new DataBuilder();
        storage = Storage.getInstance();
        totalDistance = 0.0d;
        items = new ArrayList<>();
        items.add(new Laptop());
        items.add(new Plushie());
        items.add(new Teacup());
        items.add(new Teapot());
        
        packagesToSend = createPackages();
        packageListView.getItems().addAll(packagesToSend);

    }
    
    private double drawPath(Package toDraw){
        String script = "document.createPath(";
        //Get coordinates as String-array
        script += "[\"" + toDraw.getStart().getGp().getLat() + "\", ";
        script += "\"" + toDraw.getStart().getGp().getLon() + "\", ";
        script += "\"" + toDraw.getDestination().getGp().getLat() + "\", ";
        script += "\"" + toDraw.getDestination().getGp().getLon() + "\"], ";
        script += "\"" + toDraw.getColor() + "\", ";
        script += toDraw.getCategory() + ")";
        return (double) wvMap.getEngine().executeScript(script);
    }
    
    private double relocateTimo(Package whereTo){
        if(lastPackage == null){
            lastPackage = whereTo;
            return 0.0d;
        }
        String script = "document.createPath(";
        //Get coordinates as String-array
        script += "[\"" + lastPackage.getDestination().getGp().getLat() + "\", ";
        script += "\"" + lastPackage.getDestination().getGp().getLon() + "\", ";
        script += "\"" + whereTo.getStart().getGp().getLat() + "\", ";
        script += "\"" + whereTo.getStart().getGp().getLon() + "\"], ";
        script += "\"" + lastPackage.getColor() + "\", ";
        script += lastPackage.getCategory() + ")";
        return (double) wvMap.getEngine().executeScript(script);
    }
    
    @FXML
    private void sendTimo(ActionEvent event) {
        if(packageListView.getItems().isEmpty()){
            return;
        }
        //Go to packages start location
        totalDistance += relocateTimo(packageListView.getSelectionModel().getSelectedItem());
        //Send package
        totalDistance += drawPath(packageListView.getSelectionModel().getSelectedItem());
        lastPackage = packageListView.getSelectionModel().getSelectedItem();
        packageListView.getItems().remove(lastPackage);
        if(packageListView.getItems().isEmpty()){
            packageInfoField.setText("Kuljetit kaikki paketit, kiitos.\n" +
                    "Timo käveli yhteensä " + String.valueOf(totalDistance) + "km.");
        }
    }
    
    private ArrayList<Package> createPackages(){
        ArrayList<Package> createdPackages = new ArrayList();
        Package pac;
        Random rand = new Random();
        for (int i=0; i<20; i++){
            pac = new PackageSecondCategory(items.get(rand.nextInt(4)),
                    db.getAllSmartPosts().get(rand.nextInt(db.getAllSmartPosts().size())),
                    db.getAllSmartPosts().get(rand.nextInt(db.getAllSmartPosts().size())));
            createdPackages.add(pac);
        }
        return createdPackages;
    }

    @FXML
    private void updateInfoBox(MouseEvent event) {
        packageInfoField.setText(packageListView.getSelectionModel().getSelectedItem().toString() + "\n");
    }
}
