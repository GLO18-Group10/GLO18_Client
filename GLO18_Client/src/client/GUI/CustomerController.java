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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author antonio
 */
public class CustomerController implements Initializable {
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private AnchorPane AnchorPane1;
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
    private MenuButton AccountsDropdown;
    @FXML
    private Label AccountBalanceLabel;
    @FXML
    private Button ProfileButton;
    
    GUIrun guiRun = GUIrun.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) throws IOException {
        if (event.getSource() == TransferButton) {
            clearPanes();
            AnchorPane2.toFront();
            AnchorPane2.setVisible(true);
        } else if (event.getSource() == AccountsButton) {
            clearPanes();
            AnchorPane1.toFront();
            AnchorPane1.setVisible(true);
        } else if (event.getSource() == OptionsButton) {
            clearPanes();
            AnchorPane3.toFront();
            AnchorPane3.setVisible(true);
        } else if (event.getSource() == ProfileButton) {
            //Get all the information and update the text fields
            EmailField.setEditable(false);
            EmailField.setText(guiRun.getCustomer().getEmail());
            AddressField.setEditable(false);
            AddressField.setText(guiRun.getCustomer().getAddress());
            PhoneNoField.setEditable(false);
            PhoneNoField.setText(guiRun.getCustomer().getPhoneNo());
            BirthdayField.setEditable(false);
            BirthdayField.setText(guiRun.getCustomer().getBirthday());
            NameField.setEditable(false);
            NameField.setText(guiRun.getCustomer().getName());
            //Clear current pane and display to the user
            clearPanes();
            ProfileAnchor.toFront();
            ProfileAnchor.setVisible(true);
        }else if(event.getSource() == LogoutButton){
            System.out.println("logout button");
            if(guiRun.logout().equalsIgnoreCase("true")){
                System.out.println("logout was true");
                try {
                        Parent nextView = FXMLLoader.load(getClass().getResource("login.fxml"));
                        Scene newScene = new Scene(nextView);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(newScene);
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println("logout error");
                        ex.printStackTrace();
                    }
            }else if(guiRun.logout().equalsIgnoreCase("false")){
                System.out.println("could not log out"); //this should bechanged to a label in the GUI
            }
        }
    }

    @FXML
    private void setAccountBalance(javafx.event.ActionEvent event) {
        AccountBalanceLabel.setText(guiRun.getAccountBalance(AccountsDropdown.getText())+" DKK");
    }
    
    private void clearPanes() {
        AnchorPane1.setVisible(false);
        AnchorPane2.setVisible(false);
        AnchorPane3.setVisible(false);
        ProfileAnchor.setVisible(false);
    }

 
}
