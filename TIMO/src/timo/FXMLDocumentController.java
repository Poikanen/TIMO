/* TIMO, LUT Olio-ohjelmointi 2016
 * FXMLDocumentController.java
 * Authors: 	Toivo Mattila
 *		Tommi Wäänänen
 *
 * Last modified: 2016-12-27
 */
package timo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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
    private RadioButton rbSecondClass;
    
    @FXML
    private ComboBox<Package> cbPackage;
    private ToggleGroup tg;
    @FXML
    private TextArea logTextArea;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wvMap.getEngine().load(getClass().getResource("index.html").toExternalForm());
        
        db = new DataBuilder();
        storage = Storage.getInstance();
        tg = new ToggleGroup();
        rbFirstClass.setToggleGroup(tg);
        rbSecondClass.setToggleGroup(tg);
        rbThirdClass.setToggleGroup(tg);
        tg.selectToggle(rbFirstClass);
        
        cbSmartPost.getItems().addAll(db.getAllSmartPosts());
        cbStartCity.getItems().addAll(db.getCities());
        cbDestinationCity.getItems().addAll(db.getCities());
        
        //cbItem.getItems().add(new Item());
        cbItem.getItems().add(new Teapot());
        cbItem.getItems().add(new Laptop());
        cbItem.getItems().add(new Teacup());
        cbItem.getItems().add(new Plushie());
        
        
        Tooltip rbTip1 = new Tooltip();
        Tooltip rbTip2 = new Tooltip();
        Tooltip rbTip3 = new Tooltip();
        
        rbTip1.setText("Paketti kiidätetään pikaisesti\n"
                + "enintään 150km päähän.");
        rbTip2.setText("Paketti kuljetetaan turvallisesti\n"
                + "minne tahansa Suomessa.");
        rbTip3.setText("Paketti paiskotaan perille.");
        
        rbFirstClass.setTooltip(rbTip1);
        rbSecondClass.setTooltip(rbTip2);
        rbThirdClass.setTooltip(rbTip3);
    }

    @FXML
    private void handleAddSmartPost(ActionEvent event) {
        if(cbSmartPost.getValue() != null){
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
        }else{
            textInfoBox.setText("Valitse lisättävä SmartPost.\n");
        }
    }

    @FXML
    private void handleSendPacket(ActionEvent event) {
        Package toSend = createNewPackage();
        textInfoBox.setText("");
        if (toSend!=null)
        {
            textInfoBox.setText("");
            sendPackage(toSend);
            textInfoBox.setText("Paketti lähetetty.\n");
        }
        
    }
    
    @FXML
    private void handleSendPacketFromStorage(ActionEvent event) {
        if (cbPackage.getValue()!=null){
            Package toSend = cbPackage.getValue();
            textInfoBox.setText("");
            sendPackage(toSend);
            textInfoBox.setText("Paketti lähetetty.\n");
        }else{
            textInfoBox.setText("Valitse lähetettävä paketti.\n");
        }
    }

    @FXML
    private void handleSendAllPackets(ActionEvent event) {
        ArrayList<Package> tmpToSend = storage.getUnsentPackages();
        textInfoBox.setText("");
        for(int i = 0; i < tmpToSend.size(); i++){
            if (tmpToSend.get(i).send()){
                sendPackage(tmpToSend.get(i));
            }
            textInfoBox.setText(tmpToSend.get(i).getSendMessage() +"\n" + textInfoBox.getText());
        }
        textInfoBox.setText("Kaikki paketit lähetetty.\n");
    }

    @FXML
    private void handleEmptyPaths(ActionEvent event) {
        wvMap.getEngine().executeScript("document.deletePaths()");
        textInfoBox.setText("Reitit tyhjennetty.\n");
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
    private void updatePackageCombo(){
        cbPackage.getItems().clear();
        cbPackage.getItems().addAll(storage.getUnsentPackages());
    }
    
    private Package createNewPackage(){
        
        if (cbItem.getValue()!=null && cbDestinationSmartPost.getValue()!=null &&
                cbStartSmartPost.getValue()!=null && !getPacketClass().equals("0")) {
            Package newPackage = new PackageFirstCategory();
            switch (Integer.parseInt(getPacketClass())){
                case 1: newPackage = new PackageFirstCategory(cbItem.getValue(), cbStartSmartPost.getValue(), cbDestinationSmartPost.getValue());
                        break;
                case 2: newPackage = new PackageSecondCategory(cbItem.getValue(), cbStartSmartPost.getValue(), cbDestinationSmartPost.getValue());
                        break;
                case 3: newPackage = new PackageThirdCategory(cbItem.getValue(), cbStartSmartPost.getValue(), cbDestinationSmartPost.getValue());
                        break;
            }
            if (!newPackage.doesFit(cbItem.getValue())){
                textInfoBox.setText("Paketti on liian suuri.\n");
                return null;
            }
            
            storage.addPackage(newPackage);
            updatePackageCombo();
            textInfoBox.setText("Paketti luotu.\n");
            return newPackage;
        }
        textInfoBox.setText("Anna kaikki tarvittavat tiedot.\n");
        return null;
    }
    
    private String getPacketClass(){
        String tmp = "0";
        if (rbFirstClass.isSelected())
            tmp = "1";
        if (rbSecondClass.isSelected())
            tmp = "2";
        if (rbThirdClass.isSelected())
            tmp = "3";
        return tmp;
    }

    @FXML
    private void handleStorePacket(ActionEvent event) {
        if(createNewPackage() != null)
            textInfoBox.setText("Paketti varastoitu.");
    }

    
    private void sendPackage(Package toSend){
        String script = "document.createPath(";
            //Get coordinates as String-array
            script += "[\"" + toSend.getStart().getGp().getLat() + "\", ";
            script += "\"" + toSend.getStart().getGp().getLon() + "\", ";
            script += "\"" + toSend.getDestination().getGp().getLat() + "\", ";
            script += "\"" + toSend.getDestination().getGp().getLon() + "\"], ";
            script += "\"" + toSend.getColor() + "\", ";
            script += toSend.getCategory() + ")";
            wvMap.getEngine().executeScript(script);
            toSend.send();
            //textInfoBox.setText(toSend.getSendMessage() + textInfoBox.getText());
            updatePackageCombo();
    }

    @FXML
    private void updateLog(Event event) {
        logTextArea.setText(storage.getLog());
    }
}
