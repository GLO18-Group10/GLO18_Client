/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) throws IOException {
        Parent nextView = FXMLLoader.load(getClass().getResource("Customer.fxml"));
            Scene newScene = new Scene(nextView);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.show();
            nextView.setOnMousePressed((javafx.scene.input.MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });
        nextView.setOnMouseDragged((javafx.scene.input.MouseEvent event1) -> {
            stage.setX(event1.getScreenX() - xOffset);
            stage.setY(event1.getScreenY() - yOffset);
        });
            
    }}
