/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import client.Acquaintance.IGUI;
import client.Acquaintance.ILogic;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.input.MouseEvent;
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
    private Stage stage;
    @FXML
    private HBox HBox;
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
    @FXML
    private Button ConfirmTransactionButton;
    @FXML
    private Button CancelTransactionButton;
    @FXML
    private PasswordField ConfirmPasswordField;
    @FXML

    private AnchorPane OptionAnchorPane;

    @FXML
    private Button ContactButton;
    @FXML
    private AnchorPane ContactAnchor;
    @FXML
    private Label ContactErrorLabel;
    @FXML
    private Button SendBankMail;
    @FXML
    private Button CancelBankMail;
    @FXML
    private TextField ContactSubjectField;
    @FXML
    private TextArea ContactTextArea;

    @FXML
    private Button CreateBankAccountButton;
    @FXML
    private Label CreateBankAccountSucceslabel;
    @FXML
    private Label CustomerWatchLabel;

    @FXML
    private AnchorPane WelcomeAnchorPane;
    @FXML
    private Label welcomeNameLabel;
    @FXML
    private Label lastLoginLabel;

    public CustomerController() {
    }
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gui = GUIrun.getInstance();
        logic = GUIrun.getLogic();
        welcomeNameLabel.setText(logic.getCustomer().getName().split(" ")[0] + "!");
        lastLoginLabel.setText("Your last login was: " + logic.lastLogin());

        if (logic.getimeRemaining() == 0) {
            gui.logout();

        }

        watch.setDaemon(true);
        watch.start();
        movewatch.setDaemon(true);
        movewatch.start();

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        logic.updateTimer();
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
            getBankIDs();
        } else if (event.getSource() == OptionsButton) {
            clearPanes();
            passwordErrorLabel.setText("");
            OptionAnchorPane.toFront();
            OptionAnchorPane.setVisible(true);
        } else if (event.getSource() == ProfileButton) {
            //Get all the information and update the text fields
            AddressField.setText(logic.getCustomer().getAddress());
            PhoneNoField.setText(logic.getCustomer().getPhoneNo());
            BirthdayField.setText(logic.getCustomer().getBirthday());
            EmailField.setText(logic.getCustomer().getEmail());
            displayName();
            //Clear current pane and display to the user
            clearPanes();
            ProfileAnchor.toFront();
            ProfileAnchor.setVisible(true);
        } else if (event.getSource() == ContactButton) {
            clearPanes();
            ContactAnchor.toFront();
            ContactAnchor.setVisible(true);
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
            String accountID = AccountsDropdown.getValue();
            if (accountID != null) {
                if (!accountID.equals("No bankIDs")) {
                    setAccountBalance();
                    getTransactionHistory();
                }
            }

        }
    }

    private void setAccountBalance() {
        AccountBalanceLabel.setText(logic.getAccountBalance(AccountsDropdown.getValue()) + " DKK");
    }

    private void clearPanes() {
        NewTransferAnchorPane.setVisible(false);
        AccountsAnchorPane.setVisible(false);
        OptionAnchorPane.setVisible(false);
        ProfileAnchor.setVisible(false);
        clearContact();
        ContactAnchor.setVisible(false);
        lastLoginLabel.setVisible(false);
        WelcomeAnchorPane.setVisible(false);
    }

    private void clearContact() {
        ContactSubjectField.clear();
        ContactTextArea.clear();
        ContactErrorLabel.setText("");
        CreateBankAccountSucceslabel.setText("");

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
    private void proceedTransfer() {
        TransactionOverallMessageLabel.setText("");
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
        } else if (bankID.equals(senderBankID)) {
            AccountErrorLabel.setText("You cannot send money to the same account");
        } else if (checkAmount(amount)) {
            AmountErrorLabel.setText("Please input a number");
        } else if (containsInvalidInput(bankID)) {
            AccountErrorLabel.setText("Please only input numbers");
        } else if (message.contains(";")) {
            MessageErrorLabel.setText("Error - Invalid input.");
        } else {
            amount = makeInt(amount);
            if (amount.equals("Only two decimals allowed")) {
                AmountErrorLabel.setText("Only two decimals allowed");
            } else if (amount.equals("Enter a number before the comma")) {
                AmountErrorLabel.setText("Enter a number before the comma");
            } else if (amount.equals("Amount is too large. Please contact bank")) {
                AmountErrorLabel.setText("Amount is too large. Please contact bank");
            } else {
                ConfirmTransactionButton.setVisible(true);
                CancelTransactionButton.setVisible(true);
                ConfirmPasswordField.setVisible(true);
                freezeInputTransaction();
            }
        }
    }

    @FXML
    private void CancelTransfer(ActionEvent event
    ) {
        ConfirmTransactionButton.setVisible(false);
        CancelTransactionButton.setVisible(false);
        ConfirmPasswordField.setVisible(false);
        defreezeInputTransaction();
    }

    @FXML
    private void transfer(ActionEvent event) {
        AmountErrorLabel.setText("");
        AccountErrorLabel.setText("");
        MessageErrorLabel.setText("");
        String amount = makeInt(AmountField.getText());
        String bankID = AccountField.getText() + RegField.getText();
        String message = MessageArea.getText();
        String senderBankID = TransactionBankIDChoiceBox.getValue();
        if (ConfirmPasswordField.getText().equalsIgnoreCase("")) {
            TransactionOverallMessageLabel.setText("Please enter your password before confirming transaction");
        } else if (ConfirmPasswordField.getText().contains(";") || ConfirmPasswordField.getText().contains("\"")) {
            TransactionOverallMessageLabel.setText("Dont use ; or \"");
        } else if (logic.checkPassword(logic.getCustomer().getID(), ConfirmPasswordField.getText()).equalsIgnoreCase("true")) {
            String response = logic.toProtocol05(senderBankID, amount, bankID, message);
            if (response.equalsIgnoreCase("Error; recipient not found.")) {
                AccountErrorLabel.setText("Error; recipient not found.");
                ConfirmTransactionButton.setVisible(false);
                CancelTransactionButton.setVisible(false);
                ConfirmPasswordField.setVisible(false);
            } else if (response.equalsIgnoreCase("Error; insufficient funds.")) {
                AmountErrorLabel.setText("Error; insufficient funds.");
                ConfirmTransactionButton.setVisible(false);
                CancelTransactionButton.setVisible(false);
                ConfirmPasswordField.setVisible(false);
            } else if (response.equalsIgnoreCase("complete")) {
                cleanInputFields(event);
                TransactionOverallMessageLabel.setText(response);
                ConfirmTransactionButton.setVisible(false);
                CancelTransactionButton.setVisible(false);
                ConfirmPasswordField.setVisible(false);
            } else {
                TransactionOverallMessageLabel.setText(response);
            }
        } else {
            TransactionOverallMessageLabel.setText("Incorrect Password");
        }
        defreezeInputTransaction();
    }

    //Shows the transaction history
    private void getTransactionHistory() {
        String accountID = AccountsDropdown.getValue();
        TransactionHistoryListView.getItems().clear();
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
        ConfirmPasswordField.setText("");
        CreateBankAccountSucceslabel.setText("");
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

    @FXML
    private void sendBankMail() {
        if (ContactSubjectField.getText().equalsIgnoreCase("")) {
            ContactErrorLabel.setText("Please Insert Subject");
        } else if (ContactTextArea.getText().equalsIgnoreCase("")) {
            ContactErrorLabel.setText("Please Insert Text");
        } else if (ContactSubjectField.getText().contains(";") || ContactSubjectField.getText().contains("\"")) {
            ContactErrorLabel.setText("Do not use ; or \" in the subject field");
        } else if (ContactTextArea.getText().contains(";") || ContactTextArea.getText().contains("\"")) {
            ContactErrorLabel.setText("Do not use ; or \" in the text area");
        } else {
            ContactErrorLabel.setText(logic.contactBank(logic.getCustomer().getID(), ContactSubjectField.getText(), ContactTextArea.getText()));
            ContactSubjectField.clear();
            ContactTextArea.clear();
        }
    }

    @FXML
    private void cancelBankMail() {
        ContactSubjectField.clear();
        ContactTextArea.clear();
    }

    @FXML
    private void openBankAccount() {
        System.out.println(AccountsDropdown.getItems().size());

        if (AccountsDropdown.getItems().size() == 10) {
            CreateBankAccountSucceslabel.setText("Max bank accounts");

        } else {

            String message = logic.openBankAccount();
            CreateBankAccountSucceslabel.setText(message);
            AccountsDropdown.getItems().clear();
            if (AccountsDropdown.getItems().isEmpty()) {

                String bankid[] = logic.getCustomer().getBankID().split(";");
                for (int i = 0; i < bankid.length; i++) {
                    AccountsDropdown.getItems().addAll(bankid[i]);

                }
            }
        }
        
    
    }



    
            

    

    private String makeInt(String text) {
        int commaPos = text.indexOf(",");
        if (commaPos == 0) {
            return "Enter a number before the comma";
        } else if (commaPos == -1) {
            text += "00";
        } else {
            int charAfterComma = text.length() - 1 - commaPos;
            if (charAfterComma == 2 || charAfterComma == 0) {
                text = text.replace(",", "");
            } else if (charAfterComma == 1) {
                text = text.replace(",", "");
                text += "0";
            } else if (charAfterComma > 2) {
                return "Only two decimals allowed";
            }
        }
        try {
            int i = Integer.parseInt(text);
        } catch (Exception e) {
            return "Amount is too large. Please contact bank";
        }
        return text;

    }

    private boolean checkAmount(String amount) {
        char[] charArrayToCheck = amount.toCharArray();
        int commaCount = 0;
        for (char c : charArrayToCheck) {
            if (c != ',') {
                if (!Character.isDigit(c)) {
                    return true;
                }
            } else {
                commaCount++;
                if (commaCount > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void freezeInputTransaction() {
        AmountField.setEditable(false);
        AccountField.setEditable(false);
        RegField.setEditable(false);
        MessageArea.setEditable(false);
    }

    private void defreezeInputTransaction() {
        AmountField.setEditable(true);
        AccountField.setEditable(true);
        RegField.setEditable(true);
        MessageArea.setEditable(true);
    }



    

     Thread watch = new Thread(new Runnable(){
            @Override
        public void run() {
                try{
                        while (true) {                        
                            Platform.runLater(new Runnable() {

                                @Override
        public void run() {
                                    Date date = new Date();
                                    //dateFormat.getCalendar().ge
                                    CustomerWatchLabel.setText(dateFormat.format(date));
                                    
                                    
                                }
                            });
                            Thread.sleep(500);
                    }
                    } catch(InterruptedException ex){
                        ex.printStackTrace();
                        
                    }
                
            }
        });
     //MUST NOT BE DELETED
   Paint paint = new Paint() {

        @Override
        public PaintContext createContext(ColorModel cm, Rectangle rctngl, Rectangle2D rd, AffineTransform at, RenderingHints rh) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getTransparency() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    boolean testwatch = true;
    Thread movewatch = new Thread(new Runnable(){
            @Override
        public void run() {
                try{
                    
                        while (true) {                        
                            Platform.runLater(new Runnable() {
                            double xpos;
                            
                            
                                @Override
        public void run() {
                                    double furthestx = 292 - CustomerWatchLabel.getWidth();
                                    
                                    if (testwatch == false) {
                                        xpos = CustomerWatchLabel.getLayoutX() - 1;
                                        CustomerWatchLabel.setLayoutX(xpos);
                                        
                                        if (CustomerWatchLabel.getLayoutX() == 0) {
                                            testwatch = true;
                                            CustomerWatchLabel.setTextFill(javafx.scene.paint.Paint.valueOf(randomColor()));
                                        }
                                    }
                                    else if (testwatch == true) {
                                       
                                       xpos = CustomerWatchLabel.getLayoutX() + 1;
                                       CustomerWatchLabel.setLayoutX(xpos);
                                       
                                       if (furthestx <= CustomerWatchLabel.getLayoutX()) {
                                            testwatch = false;
                                            CustomerWatchLabel.setTextFill(javafx.scene.paint.Paint.valueOf(randomColor()));
                                        }  
                                        
                                    }
                                    
                                    else
                                    CustomerWatchLabel.setLayoutX(xpos);
                                    
                                }
                            });
                            Thread.sleep(37);
                    }
                    } catch(InterruptedException ex){
                        ex.printStackTrace();
                        
                    }
                
            }
        });
    
    private String randomColor(){
        
        // create random object - reuse this as often as possible
        Random random = new Random();

        // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
        int nextInt = random.nextInt(0xffffff + 1);

        // format it as hexadecimal string (with hashtag and leading zeros)
        String colorCode = String.format("#%06x", nextInt);

        return colorCode;
    
    }

   

    private void displayName() {
        String[] name = logic.getCustomer().getName().split(" ");
        String firstName = "";
        for (int i = 0; i < name.length - 1; i++) {
            firstName += name[i] + " ";
        }
        NameField.setText(firstName);
        LastNameField.setText(logic.getCustomer().getName().split(" ")[name.length - 1]);
    }

    private void getBankIDs() {
        if (AccountsDropdown.getItems().isEmpty()) {
            String bankid[] = logic.getCustomer().getBankID().split(";");
            for (int i = 0; i < bankid.length; i++) {
                AccountsDropdown.getItems().add(bankid[i]);
            }
        }

    }
}
