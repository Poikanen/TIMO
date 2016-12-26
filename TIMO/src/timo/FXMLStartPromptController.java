/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * FXML Controller class
 *
 * @author tommi
 */
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
}
