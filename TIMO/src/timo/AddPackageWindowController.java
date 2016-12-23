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
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Toivo
 */
public class AddPackageWindowController implements Initializable {

    @FXML
    private ComboBox<Package> cbPackage;
    @FXML
    private TextArea textInfoBox;
    
    private DataBuilder db;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
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
    private void handleCreatePacket(ActionEvent event) {
        //Package tmpPkg = cbPackage.getValue().getCopy();
        //tmpPkg.setItem(new Item(cbItem.getValue()));
        //tmpPkg.setStart(new SmartPost(cbStartSmartPost.getValue()));
        //tmpPkg.setDestination(new SmartPost(cbDestinationSmartPost.getValue()));
        
        //Storage.getInstance().addPackage(tmpPkg);
        //textInfoBox.setText("Paketti luotu.\n");
    }
    
    public void initData(DataBuilder db){
        
        cbPackage.getItems().add(new PackageFirstCategory(new Item(), new SmartPost(), new SmartPost()));
        cbPackage.getItems().add(new PackageSecondCategory(new Item(), new SmartPost(), new SmartPost()));
        cbPackage.getItems().add(new PackageThirdCategory(new Item(), new SmartPost(), new SmartPost()));
    }
    
}
