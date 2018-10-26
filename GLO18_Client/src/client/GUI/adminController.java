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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author antonio
 */
public class adminController implements Initializable {
    @FXML AnchorPane adminOverview;
    @FXML ListView Listview;
    @FXML Pane PaneBar; 
    @FXML Button DeleteButton;
    @FXML TextField SearchField;
    @FXML Button LogoutButton;
    @FXML Button CreateButton;
    @FXML TextField FirstNameField;
    @FXML TextField LastnameField;
    @FXML TextField CPRField;
    @FXML TextField EmailField;
    @FXML TextField BirtdayField;
    @FXML TextField PhoneField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
