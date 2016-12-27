/* TIMO, LUT Olio-ohjelmointi 2016
 * AddItemWindowController.java
 * Authors: 	Toivo Mattila
 *		Tommi Wäänänen
 *
 * Last modified: 2016-12-27
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

public class AddItemWindowController implements Initializable {

    @FXML
    private TextField itemTextField;
    @FXML
    private CheckBox fragileChoice;
    
    ComboBox<Item> cbItem;
    @FXML
    private TextField itemHeightField;
    @FXML
    private TextField itemWidthField;
    @FXML
    private TextField itemDepthField;
    @FXML
    private TextField itemWeightField;

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
        if (!itemTextField.getText().isEmpty() &&
            !itemHeightField.getText().isEmpty() &&
            !itemWidthField.getText().isEmpty() &&
            !itemDepthField.getText().isEmpty() &&
            !itemWeightField.getText().isEmpty()) {
        cbItem.getItems().add(new Item(fragileChoice.isSelected(),
                itemTextField.getText(), Double.parseDouble(itemWidthField.getText()),
                Double.parseDouble(itemHeightField.getText()), Double.parseDouble(itemDepthField.getText()),
                Double.parseDouble(itemWeightField.getText())));
        Stage stage = (Stage) itemTextField.getScene().getWindow();
        stage.close();
        }
    }
    
}
