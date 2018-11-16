/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author antonio
 */
public class CustomerController implements Initializable {
    GUIrun guiRun = GUIrun.getInstance();
    @FXML
    private HBox HBox;
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
    private TextField RegField;
    @FXML
    private TextArea MessageArea;
    @FXML
    private Button ProccedButton;
    @FXML
    private Button CleanButton;
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
    private ChoiceBox<String> AccountsDropdown;
    @FXML
    private Label AccountBalanceLabel;
    @FXML
    private Button ProfileButton;
    @FXML
    private Button EditInformation;
    @FXML
    private Button CancelEditButton;
    @FXML
    private AnchorPane CustomerParentPane;
    @FXML
    private AnchorPane NewTransferAnchorPane;
    @FXML
    private TextField AccountField;
    @FXML
    private Label AmountErrorLabel;
    @FXML
    private Label AccountErrorLabel;
    @FXML
    private Label MessageErrorLabel;
    @FXML
    private AnchorPane AccountsAnchorPane;
    @FXML
    private ChoiceBox<String> TransactionBankIDChoiceBox;
    @FXML
    private Button updateButton;
    @FXML
    private Button SaveButton;
    @FXML
    private Label TransactionOverallMessageLabel;
    @FXML
    private ListView<String> TransactionHistoryListView;
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
            
            if (TransactionBankIDChoiceBox.getItems().isEmpty()) {
                String bankid[] = guiRun.getBankIDs().split(";");
                for (int i = 0; i < bankid.length; i++) {
                    TransactionBankIDChoiceBox.getItems().add(bankid[i]);
                }
            }
        } 
        
        else if (event.getSource() == AccountsButton) {
            clearPanes();
            AccountsAnchorPane.toFront();
            AccountsAnchorPane.setVisible(true);
            
            if (AccountsDropdown.getItems().isEmpty()) {
                String bankid[] = guiRun.getBankIDs().split(";");
                for (int i = 0; i < bankid.length; i++) {
                    AccountsDropdown.getItems().add(bankid[i]);
                }
            }
        } 
        else if (event.getSource() == OptionsButton) {
            clearPanes();
            AnchorPane3.toFront();
            AnchorPane3.setVisible(true);
        } 
        else if (event.getSource() == ProfileButton) {
            //Get all the information and update the text fields
            EmailField.setText(guiRun.getCustomer().getEmail());
            AddressField.setText(guiRun.getCustomer().getAddress());
            PhoneNoField.setText(guiRun.getCustomer().getPhoneNo());
            BirthdayField.setText(guiRun.getCustomer().getBirthday());
            NameField.setText(guiRun.getCustomer().getName());

            //Clear current pane and display to the user
            clearPanes();
            ProfileAnchor.toFront();
            ProfileAnchor.setVisible(true);
        }else if(event.getSource() == LogoutButton){
            if(guiRun.logout().equalsIgnoreCase("true")){
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
        else if (event.getSource() == updateButton) {
            
            if (AccountsDropdown.getValue() == null) {
                System.out.println("ikke valgt account");
            }
            else{
            setAccountBalance();
            getTransactionHistory();
            }
        }
    }

    
    private void setAccountBalance() {
        AccountBalanceLabel.setText(guiRun.getAccountBalance(AccountsDropdown.getValue())+" DKK");
    }
    private void clearPanes() {
        NewTransferAnchorPane.setVisible(false);
        AccountsAnchorPane.setVisible(false);
        AnchorPane3.setVisible(false);
        ProfileAnchor.setVisible(false);
    }
    
    private String storeCustomerInfo(String name, String phoneNo, String address, String email){
        return guiRun.toProtocol03(name, phoneNo, address, email);
    }

    @FXML
    private void storeCustomerInfoButtonHandler(javafx.event.ActionEvent event) {
        String name = NameField.getText();
        String phoneNo = PhoneNoField.getText();
        String address = AddressField.getText();
        String email = EmailField.getText();
        System.out.println(name + phoneNo + address + email);
        System.out.println(storeCustomerInfo(name, phoneNo, address, email));
        guiRun.getCustomer().setName(name);
        guiRun.getCustomer().setPhoneNo(phoneNo);
        guiRun.getCustomer().setAddress(address);
        guiRun.getCustomer().setEmail(email);
        NameField.setDisable(true);
        PhoneNoField.setDisable(true);
        AddressField.setDisable(true);
        EmailField.setDisable(true);
        CancelEditButton.setVisible(false);
        SaveButton.setVisible(false);
    }

    @FXML
    private void EditEnableButtonHandler(javafx.event.ActionEvent event) {
        NameField.setDisable(false);
        PhoneNoField.setDisable(false);
        AddressField.setDisable(false);
        EmailField.setDisable(false);
        CancelEditButton.setVisible(true);
        SaveButton.setVisible(true);
    }

    @FXML
    private void CancelEditButtonHandler(javafx.event.ActionEvent event) {
        CancelEditButton.setVisible(false);
        SaveButton.setVisible(false);
        NameField.setDisable(true);
        PhoneNoField.setDisable(true);
        AddressField.setDisable(true);
        EmailField.setDisable(true);
        RestoreInfoInFields();
    }
    
    private void RestoreInfoInFields(){
        NameField.setText(guiRun.getCustomer().getName());
        PhoneNoField.setText(guiRun.getCustomer().getPhoneNo());
        AddressField.setText(guiRun.getCustomer().getAddress());
        EmailField.setText(guiRun.getCustomer().getEmail());
    }

    @FXML
    private void transfer(){
        String amount = AmountField.getText();
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
            TransactionOverallMessageLabel.setText(response);
        }
        else {
            TransactionOverallMessageLabel.setText(response);
        }
    }
    //Shows the transaction history
    
    private void getTransactionHistory(){
        String accountID = AccountsDropdown.getValue();
        String data[] = guiRun.getTransactionHistory(accountID).split(";");
        
        for (int i = 0; i < data.length; i++) {
           TransactionHistoryListView.getItems().add(data[i]);
            
            }
    }
    
    
}

