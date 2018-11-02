/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    GUIrun guiRun;
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
    @FXML 
    TextField PhoneField;
    ArrayList<TextField> textFields;
    @FXML
    private TextField AddressField;
    @FXML
    private TextField PasswordField;
    @FXML
    private DatePicker birthdayFieldDatePicker;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFields = new ArrayList<>();
        textFields.add(CPRField);
        textFields.add(FirstNameField);
        textFields.add(LastnameField);
        textFields.add(PhoneField);
        textFields.add(AddressField);
        textFields.add(EmailField);
        textFields.add(PasswordField);
    }    
    
    @FXML
    private void CreateButtonHandler(ActionEvent event) {
        if(isEmpty(textFields) || birthdayFieldDatePicker.getValue()== null){
            System.out.println("TOM TEST I CONTROLLER");
        }
        else{
        String ID = "C" + CPRField.getText();
        String name = FirstNameField.getText() + " " + LastnameField.getText();
        String birthdayTest = birthdayFieldDatePicker.getValue().toString();
        System.out.println(birthdayTest);
        String phoneNumber = PhoneField.getText();
        String address = AddressField.getText();
        String email = EmailField.getText();
        String password = PasswordField.getText();
        System.out.println(createCustomer(ID, name, birthdayTest, phoneNumber, address, email, password));
        }
    }
    
    private String createCustomer(String ID, String name, String birthday, String phoneNumber, String address, String email, String password){
       return guiRun.getInstance().toProtocol07(ID, name, birthday, phoneNumber, address, email, password);
    }
    
    private boolean isEmpty(ArrayList<TextField> textFieldArray){
        boolean isEmpty = false;
        for (TextField textField : textFieldArray) {
            if(textField.getText().trim().isEmpty()){
                System.out.println(textField.toString());
                String[] textFieldEmpty = textField.toString().split(",");
                System.out.println(textFieldEmpty[0].substring(13) + " IS EMPTY");
                isEmpty = true;
            }
        }
        return isEmpty;
    }
                
    
}
