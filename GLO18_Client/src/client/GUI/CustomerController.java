/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
public class CustomerController implements Initializable {
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
    private Button AccountsButton;
    @FXML
    private Button TransferButton;
    @FXML
    private Button OptionsButton;
    @FXML
    private Button LogoutButton;
    @FXML
    private TextField AmountField;
    @FXML
    private TextField DateField;
    @FXML
    private TextField RegField;
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
    @FXML
    private AnchorPane CustomerParentPane;
    @FXML
    private AnchorPane NewTransferAnchorPane;
    @FXML
    private TextField AccountField;
    @FXML
    private Label AmountErrorLabel;
    @FXML
    private Label DateErrorLabel;
    @FXML
    private Label AccountErrorLabel;
    @FXML
    private Label MessageErrorLabel;
    @FXML
    private AnchorPane AccountsAnchorPane;
    @FXML
    private TextField CPRField;
    
   
    @FXML
    private ChoiceBox<String> TransactionBankIDChoiceBox;
    public CustomerController() {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) throws IOException {
        if (event.getSource() == TransferButton) {
            clearPanes();
            NewTransferAnchorPane.toFront();
            NewTransferAnchorPane.setVisible(true);
            String bankid[] = guiRun.getBankIDs().split(";");
            
            
            if (TransactionBankIDChoiceBox.getItems().isEmpty()) {
                
            
            for (int i = 0; i < bankid.length; i++) {
                
               
                TransactionBankIDChoiceBox.getItems().add(bankid[i]);
               }
            
            
            }
            
        } else if (event.getSource() == AccountsButton) {
            clearPanes();
            AccountsAnchorPane.toFront();
            AccountsAnchorPane.setVisible(true);
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

            CPRField.setEditable(false);

            NameField.setText(guiRun.getCustomer().getName());

            //Clear current pane and display to the user
            clearPanes();
            ProfileAnchor.toFront();
            ProfileAnchor.setVisible(true);
        }
        
    }

    @FXML
    private void setAccountBalance(javafx.event.ActionEvent event) {
        AccountBalanceLabel.setText(guiRun.getAccountBalance(AccountsDropdown.getText())+" DKK");
    }
    
    private void clearPanes() {
        NewTransferAnchorPane.setVisible(false);
        AccountsAnchorPane.setVisible(false);
        AnchorPane3.setVisible(false);
        ProfileAnchor.setVisible(false);
    }
    @FXML
    private void transfer(){
        String amount = AmountField.getText();
        //String date = DateField.getText();
        String bankID = AccountField.getText() + RegField.getText();
        String message = MessageArea.getText();
        String senderBankID = TransactionBankIDChoiceBox.getValue();
                
        
        String response = guiRun.toProtocol05(senderBankID, amount, bankID, message);
        if (response.equalsIgnoreCase("Error; recipient not found.")) {
            AccountErrorLabel.setText("Error; recipient not found.");
        }
        else if (response.equalsIgnoreCase("Error; insufficient funds.")) {
            AmountErrorLabel.setText("Error; insufficient funds.");
        }
        else if (response.equalsIgnoreCase("complete")) {
            //transactionCompleteLabel.setText(test);
        }
        else {
            //overallErrorLabel.setText(test);
        }
        
    }
    //Shows the transaction history
    public void getTransactionHistory(){
        
    
    
    }
    
    
}