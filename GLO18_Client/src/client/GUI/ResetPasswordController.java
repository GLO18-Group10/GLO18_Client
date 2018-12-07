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
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author irus
 */
public class ResetPasswordController implements Initializable {

    IGUI gui;
    ILogic logic;

    @FXML
    private TextField UsernameField;
    ArrayList<TextField> Fields;
    @FXML
    private Button passwordButton;
    @FXML
    private Label IDLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gui = GUIrun.getInstance();
        logic = GUIrun.getLogic();
        Fields = new ArrayList<>();
        Fields.add(UsernameField);

    }

    @FXML
    private void resetPasswordHandler(ActionEvent event) {
        String ID = "C" + UsernameField.getText();
        if (UsernameField.getText().isEmpty()) {
            IDLabel.setText("CPR field is empty");

        } else if (!ID.contains(";")) {
            if (logic.toProtocol17(ID).equalsIgnoreCase("true")) {

                IDLabel.setText("Password change");

            } else {
                IDLabel.setText("CPR does not exist");

            }
        } else{
            IDLabel.setText("You may not use special characters");
        }

    }

    @FXML
    private void backHandler(ActionEvent event) throws IOException {

        Parent nextView = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

}
