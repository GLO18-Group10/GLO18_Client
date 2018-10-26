/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import client.Acquaintance.iGUI;
import client.Acquaintance.iLogic;
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

    private static iLogic Logic;
    private static GUIrun guiRun;

    @Override
    public void injectLogic(iLogic LogicLayer) {
        Logic = LogicLayer;
    }

    public static GUIrun getInstance() {
        return guiRun;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void startApplication(String[] args) {
        guiRun = this;
        launch(args);
    }

    public void login() {
        String ID = "A1234567";
        String password = "passwordwut?";
        System.out.println(Logic.login(ID, password));
        //return Logic.login(ID, password);
    }

    @Override
    public String getName() {
        Logic.getName();
    }

    @Override
    public String getBirthday() {
        Logic.getBirthday();
    }

    @Override
    public String getPhoneNo() {
        Logic.getPhoneNo();
    }

    @Override
    public String getAddress() {
        Logic.getAddress();
    }

    @Override
    public String getEmail() {
        Logic.getEmail();
    }

}
