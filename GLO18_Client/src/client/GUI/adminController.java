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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    TextField BirtdayField;
    @FXML TextField PhoneField;
    @FXML
    private TextField BirthdayField;
    @FXML
    private TextField AddressField;
    @FXML
    private TextField PasswordField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void CreateButtonHandler(ActionEvent event) {
        String ID = "C" + CPRField.getText();
        String name = FirstNameField.getText() + " " + LastnameField.getText();
        String birthday = BirthdayField.getText();
        String phoneNumber = PhoneField.getText();
        String address = AddressField.getText();
        String email = EmailField.getText();
        String password = PasswordField.getText();
        System.out.println(createCustomer(ID, name, birthday, phoneNumber, address, email, password));
    }
    
    private String createCustomer(String ID, String name, String birthday, String phoneNumber, String address, String email, String password){
       return guiRun.getInstance().toProtocol07(ID, name, birthday, phoneNumber, address, email, password);
    }

    @FXML
    private void logoutHandler(ActionEvent event) {
        System.out.println("logout button");
            if(guiRun.getInstance().logout().equalsIgnoreCase("true")){
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
            }else if(guiRun.getInstance().logout().equalsIgnoreCase("false")){
                System.out.println("could not log out"); //this should bechanged to a label in the GUI
            }
    }
                
    
}
