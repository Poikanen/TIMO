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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Toivo
 */
public class AddItemWindowController implements Initializable {

    @FXML
    private TextField itemTextField;
    @FXML
    private CheckBox fragileChoice;
    
    ComboBox<Item> cbItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    


    public void initData(ComboBox<Item> cbI){
        cbItem = cbI;
    }

    @FXML
    private void handleCreateItem(ActionEvent event) {
        cbItem.getItems().add(new Item(fragileChoice.isSelected(), itemTextField.getText()));
        Stage stage = (Stage) itemTextField.getScene().getWindow();
        stage.close();
    }
    
}
