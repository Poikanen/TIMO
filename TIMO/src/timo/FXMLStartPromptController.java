/* TIMO, LUT Olio-ohjelmointi 2016
 * FXMLStartPromptController.java
 * Authors: 	Toivo Mattila
 *		Tommi Wäänänen
 *
 * Last modified: 2016-12-27
 */
package timo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FXMLStartPromptController implements Initializable {

    @FXML
    private Button buttonNewTimo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void newTimo() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene((Pane)loader.load()));
            stage.show();
            //Writes log to file when the new window is closed
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent we) {
                    Storage.getInstance().writeLogToFile();
                }
            });
            stage = (Stage)buttonNewTimo.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void loadTimo() throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene((Pane)loader.load()));
            stage.show();
            //Writes log to file when the new window is closed
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent we) {
                    Storage.getInstance().writeLogToFile();
                }
            });
            stage = (Stage)buttonNewTimo.getScene().getWindow();
            stage.close();
            Storage.getInstance().readLogFromFile();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void timoGame(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TimoGame.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene((Pane)loader.load()));
            stage.show();
            stage = (Stage)buttonNewTimo.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
