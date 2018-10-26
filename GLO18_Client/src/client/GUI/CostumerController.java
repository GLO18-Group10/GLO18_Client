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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author antonio
 */
public class CostumerController implements Initializable {
    @FXML private AnchorPane AnchorPane;
    @FXML private AnchorPane AnchorPane1;   
    @FXML private GridPane GridPane;    
    @FXML private VBox Vbox;
    @FXML private ListView Listview;  
    @FXML private Button Accounts;
    @FXML private TextArea TextArea;
    @FXML private Button TransferButton;
    @FXML private Button OptionsButton;
    @FXML private Button LogoutButton;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
