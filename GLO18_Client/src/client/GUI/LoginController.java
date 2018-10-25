/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

/**

 *
 * @author antonio
 */

public class LoginController implements Initializable {
    @FXML 
    private AnchorPane Anchorpane;    
    @FXML 
    private PasswordField passwordfield;
    @FXML 
    private Button ExitButton;
    @FXML 
    private Button LoginButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
