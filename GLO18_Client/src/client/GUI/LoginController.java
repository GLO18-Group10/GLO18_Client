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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 *
 * @author antonio
 */
public class LoginController implements Initializable {

    private double xOffset;
    private double yOffset;
    IGUI gui;
    ILogic logic;

    @FXML
    private Button ExitButton;
    @FXML
    private Button LoginButton;
    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label alertField;
    @FXML
    private AnchorPane LoginParentPane;
    @FXML
    private AnchorPane LoginAnchorPane;

    
    @FXML
    private Label LoginWatchLabel;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gui = GUIrun.getInstance();
        logic = GUIrun.getLogic();
        watch.setDaemon(true);
        watch.start();
        movewatch.setDaemon(true);
        movewatch.start();
    }

    @FXML
    private void Login(javafx.event.ActionEvent event) {
        String ID = UsernameField.getText();
        String password = passwordField.getText();
        if (ID.trim().isEmpty() || password.trim().isEmpty() ) {
            alertField.setText("Please provide username and password");
        }
        else if(ID.trim().contains(";")|| password.trim().contains(";")){
            alertField.setText("Please check if your input is valid");
        }
        else{
             if (ID.startsWith("A")) {
                String check = logic.login(ID, password);
                if (check.equalsIgnoreCase("true")) {
                    try {
                        passwordField.setText("");
                        Parent nextView = FXMLLoader.load(getClass().getResource("admin.fxml"));
                        Scene newScene = new Scene(nextView);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(newScene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (check.equalsIgnoreCase("false")) {
                    passwordField.setText("");
                    alertField.setText("Log in is wrong");
                }
            } else {

                String correctedID = "C" + ID;

                String check = logic.login(correctedID, password);

                if (check.equalsIgnoreCase("true")) {
                    try {
                        UsernameField.setText("");
                        passwordField.setText("");
                        Parent nextView = FXMLLoader.load(getClass().getResource("Customer.fxml"));
                        Scene newScene = new Scene(nextView);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(newScene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (check.equalsIgnoreCase("false")) {
                    passwordField.setText("");
                    alertField.setText("Log in is wrong");

                }
            }
        }
    }

    @FXML
    private void exitHandler(ActionEvent event) {
        Platform.exit();
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
                                    LoginWatchLabel.setText(dateFormat.format(date));
                                    
                                    
                                }
                            });
                            Thread.sleep(500);
                    }
                    } catch(InterruptedException ex){
                        ex.printStackTrace();
                        
                    }
                
            }
        });
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
                                    double furthestx = LoginAnchorPane.getWidth() - LoginWatchLabel.getWidth();
                                    if (testwatch == false) {
                                        xpos = LoginWatchLabel.getLayoutX() - 1;
                                        LoginWatchLabel.setLayoutX(xpos);
                                        
                                        if (LoginWatchLabel.getLayoutX() == 0) {
                                            testwatch = true;
                                            LoginWatchLabel.setTextFill(javafx.scene.paint.Paint.valueOf(randomColor()));
                                        }
                                    }
                                    else if (testwatch == true) {
                                       
                                       xpos = LoginWatchLabel.getLayoutX() + 1;
                                       LoginWatchLabel.setLayoutX(xpos);
                                       
                                       if (furthestx == LoginWatchLabel.getLayoutX()) {
                                            testwatch = false;
                                            LoginWatchLabel.setTextFill(javafx.scene.paint.Paint.valueOf(randomColor()));
                                        }  
                                        
                                    }
                                    
                                    else
                                    LoginWatchLabel.setLayoutX(xpos);
                                    
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
}
