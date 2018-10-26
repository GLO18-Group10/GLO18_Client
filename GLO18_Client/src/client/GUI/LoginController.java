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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**

 *
 * @author antonio
 */

public class LoginController implements Initializable {
    @FXML 
    private AnchorPane Anchorpane;    
    @FXML 
    private Button ExitButton;
    @FXML 
    private Button LoginButton;
    @FXML 
    private TextField UsernameField;
    @FXML
    private AnchorPane anchorpane1;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField alertField;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
