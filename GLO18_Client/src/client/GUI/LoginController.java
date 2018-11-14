/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;



import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**

 *
 * @author antonio
 */

public class LoginController implements Initializable {
    private double xOffset;
    private double yOffset;
    GUIrun guiRun;
    
    @FXML 
    private Button ExitButton;
    @FXML 
    private Button LoginButton;
    @FXML 
    private TextField UsernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label alertField;
    @FXML
    private AnchorPane LoginParentPane;
    @FXML
    private AnchorPane LoginAnchorPane;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    

    @FXML
    private void Login(javafx.event.ActionEvent event) {
        String ID = UsernameField.getText();
        String password = passwordField.getText();
        System.out.println(ID);
        System.out.println(password);
        if (ID.trim().isEmpty() || password.trim().isEmpty()) {
            alertField.setText("Log in is wrong");
            //virker hertil
        }
        else{
           
             if (ID.startsWith("A")) {
                 System.out.println("test2");
                 
                String check = guiRun.getInstance().login(ID, password);
                 
                if (check.equalsIgnoreCase("true")) {
                    System.out.println(check);
                    try {
                        UsernameField.setText("");
                        passwordField.setText("");
                        Parent nextView = FXMLLoader.load(getClass().getResource("admin.fxml"));
                        Scene newScene = new Scene(nextView);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(newScene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (check.equalsIgnoreCase("false")) {
                    UsernameField.setText("");
                    passwordField.setText("");
                    alertField.setText("Log in is wrong");
                }
            }
                 
            else {
                       
                String correctedID = "C" + ID;
                
                String check = guiRun.getInstance().login(correctedID, password);
                 
                if (check.equalsIgnoreCase("true")) {
                    try {
                        UsernameField.setText("");
                        passwordField.setText("");
                        Parent nextView = FXMLLoader.load(getClass().getResource("Customer.fxml"));
                        Scene newScene = new Scene(nextView);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(newScene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (check.equalsIgnoreCase("false")) {
                    UsernameField.setText("");
                    passwordField.setText("");
                    alertField.setText("Log in is wrong");
                     
                }
            }
        }
    }
}

    
    


