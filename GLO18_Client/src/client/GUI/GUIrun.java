/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import client.Acquaintance.IAdmin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.Acquaintance.IGUI;
import client.Acquaintance.ILogic;
import java.io.IOException;

import javafx.application.Platform;

/**
 *
 * @author Jeppe Enevold
 */
public class GUIrun extends Application implements IGUI {

    private static ILogic logic;

    private static IGUI guiRun;
    private Stage mainStage;
    
    @Override
    public void injectLogic(ILogic LogicLayer) {
        logic = LogicLayer;
    }

    public static IGUI getInstance() {
        return guiRun;
    }
    
        public static ILogic getLogic(){
        return logic;
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
        logic.startConnection();
        guiRun = this;
        launch(args);
    }
     @Override
    public void logout() {
        if (logic.logout().equalsIgnoreCase("true")) {
            try {
                Parent nextView = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene newScene = new Scene(nextView);
                //Stage stage = (Stage) this.stage.getScene().getWindow();
                mainStage.setScene(newScene);
                mainStage.show();
            } catch (IOException ex) {
                System.out.println("Error; logoutButton(customer)");
                ex.printStackTrace();
            }
        } else if (logic.logout().equalsIgnoreCase("false")) {
            System.out.println("could not log out"); //this should bechanged to a label in the GUI
        }
    }
    /**
     * when the application stops, the user is logged out
     */
 
}