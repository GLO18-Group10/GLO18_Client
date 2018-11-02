/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;


import java.awt.event.ActionEvent;
import client.Logic.LogicFacade;
import client.Logic.LogicFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Jeppe Enevold
 */
public class GUIController implements Initializable {
    GUIrun guiRun;
    @FXML
    private Button test;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(guiRun.getInstance().getAccountBalance("7331"));
        
    }    
   
}
