/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import client.Acquaintance.IGUI;
import client.Acquaintance.ILogic;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
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

    IGUI gui;
    ILogic logic;
    @FXML
    private HBox HBox;
    @FXML
    private AnchorPane AnchorPane3;
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
    @FXML
    private Label alertLabel;
    @FXML
    private TextField LastNameField;
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField repeatPasswordField;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Button clearButtonPassord;

    public CustomerController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gui = GUIrun.getInstance();
        logic = GUIrun.getLogic();
    }

    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) throws IOException {

        if (event.getSource() == TransferButton) {
            clearPanes();
            NewTransferAnchorPane.toFront();
            NewTransferAnchorPane.setVisible(true);

            if (TransactionBankIDChoiceBox.getItems().isEmpty()) {
                String bankid[] = logic.getCustomer().getBankID().split(";");
                for (int i = 0; i < bankid.length; i++) {
                    TransactionBankIDChoiceBox.getItems().add(bankid[i]);
                }
            }
        } else if (event.getSource() == AccountsButton) {
            clearPanes();
            AccountsAnchorPane.toFront();
            AccountsAnchorPane.setVisible(true);

            if (AccountsDropdown.getItems().isEmpty()) {
                String bankid[] = logic.getCustomer().getBankID().split(";");
                for (int i = 0; i < bankid.length; i++) {
                    AccountsDropdown.getItems().add(bankid[i]);
                }
            }
        } else if (event.getSource() == OptionsButton) {
            clearPanes();
            passwordErrorLabel.setText("");
            AnchorPane3.toFront();
            AnchorPane3.setVisible(true);
        } else if (event.getSource() == ProfileButton) {
            //Get all the information and update the text fields
            EmailField.setText(logic.getCustomer().getEmail());
            AddressField.setText(logic.getCustomer().getAddress());
            PhoneNoField.setText(logic.getCustomer().getPhoneNo());
            BirthdayField.setText(logic.getCustomer().getBirthday());
            NameField.setText(logic.getCustomer().getName().split(" ")[0]);
            LastNameField.setText(logic.getCustomer().getName().split(" ")[1]);
            //Clear current pane and display to the user
            clearPanes();
            ProfileAnchor.toFront();
            ProfileAnchor.setVisible(true);
        } else if (event.getSource() == LogoutButton) {
            if (logic.logout().equalsIgnoreCase("true")) {
                try {
                    Parent nextView = FXMLLoader.load(getClass().getResource("login.fxml"));
                    Scene newScene = new Scene(nextView);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(newScene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println("Error; logoutButton(customer)");
                    ex.printStackTrace();
                }
            } else if (logic.logout().equalsIgnoreCase("false")) {
                System.out.println("could not log out"); //this should bechanged to a label in the GUI
            }
        } else if (event.getSource() == updateButton) {

            if (AccountsDropdown.getValue() != null) {
                setAccountBalance();
                getTransactionHistory();
            }
        }
    }

    private void setAccountBalance() {
        AccountBalanceLabel.setText(logic.getAccountBalance(AccountsDropdown.getValue()) + " DKK");
    }

    private void clearPanes() {
        NewTransferAnchorPane.setVisible(false);
        AccountsAnchorPane.setVisible(false);
        AnchorPane3.setVisible(false);
        ProfileAnchor.setVisible(false);
    }

    private String storeCustomerInfo(String name, String phoneNo, String address, String email) {
        return logic.toProtocol03(name, phoneNo, address, email);
    }

    @FXML
    private void storeCustomerInfoButtonHandler(javafx.event.ActionEvent event) {
        String firstName = NameField.getText();
        String lastName = LastNameField.getText();
        String fullname = firstName + " " + lastName;
        String phoneNo = PhoneNoField.getText();
        String address = AddressField.getText();
        String email = EmailField.getText();
        if (fullname.trim().isEmpty() || phoneNo.trim().isEmpty() || address.trim().isEmpty() || email.trim().isEmpty()) {
            alertLabel.setText("Please make sure you have input in all the fields");
        } else if (fullname.contains(";") || phoneNo.contains(";") || address.contains(";") || email.contains(";") || containsInvalidInput(phoneNo)) {
            alertLabel.setText("Please make sure your input is valid");
        } else if (!"true".equals(storeCustomerInfo(fullname, phoneNo, address, email))) {
            alertLabel.setText("Server ERROR - Please try again");
        } else {
            if (!storeCustomerInfo(fullname, phoneNo, address, email).equals("true")) {
                alertLabel.setText("Error - Server fail");
            }
            logic.getCustomer().setName(fullname);
            logic.getCustomer().setPhoneNo(phoneNo);
            logic.getCustomer().setAddress(address);
            logic.getCustomer().setEmail(email);
            NameField.setDisable(true);
            LastNameField.setDisable(true);
            PhoneNoField.setDisable(true);
            AddressField.setDisable(true);
            EmailField.setDisable(true);
            CancelEditButton.setVisible(false);
            SaveButton.setVisible(false);
        }
    }

    @FXML
    private void EditEnableButtonHandler(javafx.event.ActionEvent event) {
        NameField.setDisable(false);
        LastNameField.setDisable(false);
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
        LastNameField.setDisable(true);
        PhoneNoField.setDisable(true);
        AddressField.setDisable(true);
        EmailField.setDisable(true);
        RestoreInfoInFields();
    }

    private void RestoreInfoInFields() {
        NameField.setText(logic.getCustomer().getName().split(" ")[0]);
        LastNameField.setText(logic.getCustomer().getName().split(" ")[1]);
        PhoneNoField.setText(logic.getCustomer().getPhoneNo());
        AddressField.setText(logic.getCustomer().getAddress());
        EmailField.setText(logic.getCustomer().getEmail());
    }

    @FXML
    private void transfer() {
        AmountErrorLabel.setText("");
        AccountErrorLabel.setText("");
        MessageErrorLabel.setText("");
        String amount = AmountField.getText();
        String bankID = AccountField.getText() + RegField.getText();
        String message = MessageArea.getText();
        String senderBankID = TransactionBankIDChoiceBox.getValue();
        if (amount.trim().isEmpty()) {
            AmountErrorLabel.setText("Please enter an amount in the amount textfield");
        } else if (AccountField.getText().trim().isEmpty() || RegField.getText().trim().isEmpty()) {
            AccountErrorLabel.setText("Please enter both the account number and regnumber");
        } else if (containsInvalidInput(amount)) {
            AmountErrorLabel.setText("Please only input numbers");
        } else if (containsInvalidInput(bankID)) {
            AccountErrorLabel.setText("Please only input numbers");
        } else if (message.contains(";")) {
            MessageErrorLabel.setText("Error - Invalid input.");
        } else {
            String response = logic.toProtocol05(senderBankID, amount, bankID, message);
            if (response.equalsIgnoreCase("Error; recipient not found.")) {
                AccountErrorLabel.setText("Error; recipient not found.");
            } else if (response.equalsIgnoreCase("Error; insufficient funds.")) {
                AmountErrorLabel.setText("Error; insufficient funds.");
            } else if (response.equalsIgnoreCase("complete")) {
                TransactionOverallMessageLabel.setText(response);
            } else {
                TransactionOverallMessageLabel.setText(response);
            }
        }
    }
    //Shows the transaction history

    private void getTransactionHistory() {
        String accountID = AccountsDropdown.getValue();
        String data[] = logic.getTransactionHistory(accountID).split(";");

        for (int i = data.length - 1; i >= 0; i--) {
            TransactionHistoryListView.getItems().add(data[i]);

        }
    }

    private boolean containsInvalidInput(String stringToCheck) {
        char[] charArrayToCheck = stringToCheck.toCharArray();
        for (char c : charArrayToCheck) {
            if (!Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void cleanInputFields(ActionEvent event) {
        AccountField.clear();
        AmountField.clear();
        MessageArea.clear();
        RegField.clear();
        AccountErrorLabel.setText("");
        AmountErrorLabel.setText("");
        MessageErrorLabel.setText("");
        TransactionOverallMessageLabel.setText("");
    }

    @FXML
    private void changePassword() {
        //Check for illegal characters and compare the two passwords
        if (checkPassword()) {
            if (newPasswordField.getText().equals(repeatPasswordField.getText())) {
                if (newPasswordField.getText().length() > 7) {
                    String answer = logic.updatePassword(logic.getCustomer().getID(), oldPasswordField.getText(), newPasswordField.getText());
                    if (answer.equals("true")) {
                        clearPasswordFields();
                        passwordErrorLabel.setText("Password has been updated");
                    } else if (answer.equals("Incorrect password")) {
                        clearPasswordFields();
                        passwordErrorLabel.setText("Your current password is incorrect");
                    } else {
                        clearPasswordFields();
                        passwordErrorLabel.setText("Unexpected error: " + answer);
                    }
                } else {
                    passwordErrorLabel.setText("The password has to be a minimum of 8 characters");
                }
            } else {
                passwordErrorLabel.setText("The entered passwords are not identical");
            }
        } else {
            passwordErrorLabel.setText("Password cannot contain ; or \"");
        }
    }

    /**
     * Checks the password fields for illegal characters
     *
     * @return true for no illegal characters and false for illegal characters
     */
    private boolean checkPassword() {
        if (oldPasswordField.getText().contains(";") || oldPasswordField.getText().contains("\"")) {
            return false;
        } else if (newPasswordField.getText().contains(";") || newPasswordField.getText().contains("\"")) {
            return false;
        } else if (repeatPasswordField.getText().contains(";") || repeatPasswordField.getText().contains("\"")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Clears the password fields under options
     */
    @FXML
    private void clearPasswordFields() {
        oldPasswordField.clear();
        newPasswordField.clear();
        repeatPasswordField.clear();
    }
}
