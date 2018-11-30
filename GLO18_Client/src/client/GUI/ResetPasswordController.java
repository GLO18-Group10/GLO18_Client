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
    private TextField EmailField;
    ArrayList<TextField> textFields;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gui = GUIrun.getInstance();
        logic = GUIrun.getLogic();
        
        textFields.add(UsernameField);
        textFields.add(EmailField);

    }

    @FXML
    private void resetPasswordHandler(ActionEvent event) {
        if (isEmptyOrContainsIllegalChar(textFields)){
        } else {
            String ID = "C" + UsernameField.getText();
            String email = EmailField.getText();
            String password = logic.getAdmin().generatePassword();
            if (true) {
                logic.sendMail(ID, email, password);
            }
        }

    }
