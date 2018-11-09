/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import client.Acquaintance.iGUI;
import client.Acquaintance.iLogic;
import client.Logic.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jeppe Enevold
 */
public class GUIrun extends Application implements iGUI {

    private static iLogic logic;

    private static GUIrun guiRun;

    @Override
    public void injectLogic(iLogic LogicLayer) {
        logic = LogicLayer;
    }

    public static GUIrun getInstance() {
        return guiRun;
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));


        Scene scene = new Scene(root);

        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void startApplication(String[] args) {
        this.startConnection();
        guiRun = this;
        launch(args);
    }
    
    

    public String login(String ID, String password) {
        return logic.login(ID, password);
    }

    public int getAccountBalance(String accountID) {
        return logic.getAccountBalance(accountID);
    }
    
    public String toProtocol05(String name, String phoneNo, String address, String email){
        return logic.toProtocol05(name, phoneNo, address, email);
    }

    public String toProtocol07(String ID, String name, String birthday, String phonenumber, String address, String email, String password){
        return logic.toProtocol07(ID, name, birthday, phonenumber, address, email, password);
    }

    @Override
    public void startConnection() {
        logic.startConnection();
    }

    public Customer getCustomer() {
        return logic.getCustomer();
    }

}
