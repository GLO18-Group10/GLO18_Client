/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author antonio
 */
public class CostumerController implements Initializable {
   @FXML
    private AnchorPane AnchorPane;
    @FXML
    private AnchorPane AnchorPane1;
    @FXML
    private MenuButton MenuButton1;
    @FXML
    private HBox HBox;
    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private AnchorPane AnchorPane3;
    @FXML
    private GridPane GridPane;
    @FXML
    private VBox VBox;
    @FXML
    private Pane Pane;
    @FXML
    private TextArea TextArea;
    @FXML
    private Button AccountsButton;
    @FXML
    private Button TransferButton;
    @FXML
    private Button OptionsButton;
    @FXML
    private Button LogoutButton;
    @FXML
    private AnchorPane AnchorPane2;
    @FXML
    private TextField TransferField;
    @FXML
    private TextField AmountField;
    @FXML
    private TextField DateField;
    @FXML
    private TextField RegField;
    @FXML
    private TextField ContoField;
    @FXML
    private TextField TextRecipientField;
    @FXML
    private TextArea MessageArea;
    @FXML
    private Button ProccedButton;
    @FXML
    private Button CleanButton;
    @FXML
    private MenuButton MenuButtonAccounts;
    @FXML
    private AnchorPane ProfileAnchor;
    @FXML
    private TextField NameField;
    @FXML
    private TextField BirthdayField;
    @FXML
    private TextField PhoneNoField;
    @FXML
    private TextField AddressField;
    @FXML
    private TextField EmailField;
    @FXML
    private Button ProfileButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) throws IOException {
    if (event.getSource() == TransferButton) {
            AnchorPane2.toFront();
            AnchorPane2.setVisible(true);
            
        } else if (event.getSource() == AccountsButton) {
            AnchorPane3.toFront();
            AnchorPane3.setVisible(true);
        } else if (event.getSource() == OptionsButton) {
            AnchorPane1.toFront();
            AnchorPane1.setVisible(true);
        } else if (event.getSource() == ProfileButton) {
            EmailField.setEditable(false);
            EmailField.setText(GUIrun.getInstance().getName());
            AddressField.setEditable(false);
            AddressField.setText(GUIrun.getInstance().getName());
            PhoneNoField.setEditable(false);
            PhoneNoField.setText(GUIrun.getInstance().getName());
            BirthdayField.setEditable(false);
            BirthdayField.setText(GUIrun.getInstance().getName());
            NameField.setEditable(false);
            NameField.setText(GUIrun.getInstance().getName());
            ProfileAnchor.toFront();
            ProfileAnchor.setVisible(true);    
            
        } 
    }
}