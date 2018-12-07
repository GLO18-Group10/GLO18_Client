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
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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

    IGUI gui;
    ILogic logic;
    @FXML
    AnchorPane adminOverview;
    ListView Listview;
    @FXML
    Pane PaneBar;
    @FXML
    Button Close_Button;
    TextField SearchField;
    @FXML
    Button LogoutButton;
    @FXML
    Button Open_Button;
    @FXML
    Button CreateButton;
    @FXML
    TextField FirstNameField;
    @FXML
    TextField LastnameField;
    @FXML
    TextField CPRField;
    @FXML
    TextField EmailField;
    @FXML
    TextField PhoneField;
    ArrayList<TextField> textFields;
    @FXML
    private TextField AddressField;
    @FXML
    private DatePicker birthdayFieldDatePicker;
    @FXML
    private ListView<?> customerAccountsListView;
    @FXML
    private TextArea statusTextArea;
    @FXML
    private Button updateIDButton;
    @FXML
    private Label activateIDLabel;
    @FXML
    private Label AdminWatchLabel;

    /**
     * Initializes the controller class.
     */
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gui = GUIrun.getInstance();
        logic = GUIrun.getLogic();
        textFields = new ArrayList<>();
        textFields.add(CPRField);
        textFields.add(FirstNameField);
        textFields.add(LastnameField);
        textFields.add(PhoneField);
        textFields.add(AddressField);
        textFields.add(EmailField);
        getIdForList();
        watch.setDaemon(true);
        watch.start();
        movewatch.setDaemon(true);
        movewatch.start();
    }

    @FXML
    private void CreateButtonHandler(ActionEvent event) {
        logic.updateTimer();
        if (isEmptyOrContainsIllegalChar(textFields) || birthdayFieldDatePicker.getValue() == null || !isValid(CPRField.getText()) || !isValid(PhoneField.getText())) {
        } else {
            String ID = "C" + CPRField.getText();
            String name = FirstNameField.getText() + " " + LastnameField.getText();
            String birthdayTest = birthdayFieldDatePicker.getValue().toString();
            String phoneNumber = PhoneField.getText();
            String address = AddressField.getText();
            String email = EmailField.getText();
            String password = logic.getAdmin().generatePassword();
            String success = logic.toProtocol07(ID, name, birthdayTest, phoneNumber, address, email, password);
            if (success.equalsIgnoreCase("true")) {
                statusTextArea.clear();
                statusTextArea.appendText("Customer account has been created\n");
                String response = logic.sendMailAutogenerated(ID, email, name, password);
                if (response.equals("true")) {
                    statusTextArea.appendText("Email has been send");
                } else {
                    statusTextArea.appendText("Error occured. Email could not be sent");
                }
                getIdForList();
            } else {
                statusTextArea.appendText("Customer account could not be created\nIs the CPR number already in use?");
            }
        }
    }

    @FXML
    private void logoutHandler(ActionEvent event) {
        logic.updateTimer();
        if (logic.logout().equalsIgnoreCase("true")) {
            try {
                
                Parent nextView = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene newScene = new Scene(nextView);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(newScene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("Error; logoutHandler(admin)");
                ex.printStackTrace();
            }
        } else if (logic.logout().equalsIgnoreCase("false")) {
            System.out.println("could not log out"); //this should be changed to a label in the GUI
        }
    }

    private boolean isEmptyOrContainsIllegalChar(ArrayList<TextField> textFieldArray) {
        boolean isEmpty = false;
        statusTextArea.clear();
        for (TextField textField : textFieldArray) {
            if (textField.getText().trim().isEmpty()) {
                String[] textFieldEmpty = textField.toString().split(",");
                statusTextArea.appendText(textFieldEmpty[0].substring(13) + " IS EMPTY\n");
                isEmpty = true;
            }
            if (textField.getText().contains(";")) {
                String[] textFieldEmpty = textField.toString().split(",");
                statusTextArea.appendText(textFieldEmpty[0].substring(13) + " CANNOT CONTAIN ;\n");
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    private boolean isValid(String input) {
        char[] inputCharArray = input.toCharArray();
        for (int i = 0; i < inputCharArray.length; i++) {
            if (!Character.isDigit(inputCharArray[i])) {
                statusTextArea.appendText("Only digits in phone field and CPR");
                return false;
            }
        }
        return true;
    }

    @FXML
    private void getIdForList() {
        String[] data = logic.getAdmin().getCustomerId();
        ObservableList list = FXCollections.observableArrayList(data);
        customerAccountsListView.setItems(list);
    }

    @FXML
    private void CloseCustomerAccount(ActionEvent event) {
        logic.updateTimer();
        if (event.getSource() == Close_Button) {
            String response = logic.getAdmin().closeCustomerAccount((String) customerAccountsListView.getSelectionModel().getSelectedItem());
            activateIDLabel.setText("Deactivation: " + response);
        }

    }

    @FXML
    private void openCustomerAccount(ActionEvent event) {
       logic.updateTimer();
        if (event.getSource() == Open_Button) {
            String response = logic.getAdmin().openCustomerAccount((String) customerAccountsListView.getSelectionModel().getSelectedItem());
            activateIDLabel.setText("Activation: " + response);
        }
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
                                    AdminWatchLabel.setText(dateFormat.format(date));
                                    
                                    
                                }
                            });
                            Thread.sleep(500);
                    }
                    } catch(InterruptedException ex){
                        ex.printStackTrace();
                        
                    }
                
            }
        });
     //MUSTNT BE DELETED
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
                                    double furthestx = 936 - AdminWatchLabel.getWidth();
                                    
                                    if (testwatch == false) {
                                        xpos = AdminWatchLabel.getLayoutX() - 1;
                                        AdminWatchLabel.setLayoutX(xpos);
                                        
                                        if (AdminWatchLabel.getLayoutX() == 0) {
                                            testwatch = true;
                                            AdminWatchLabel.setTextFill(javafx.scene.paint.Paint.valueOf(randomColor()));
                                        }
                                    }
                                    else if (testwatch == true) {
                                       
                                       xpos = AdminWatchLabel.getLayoutX() + 1;
                                       AdminWatchLabel.setLayoutX(xpos);
                                       
                                       if (furthestx <= AdminWatchLabel.getLayoutX()) {
                                            testwatch = false;
                                            AdminWatchLabel.setTextFill(javafx.scene.paint.Paint.valueOf(randomColor()));
                                        }  
                                        
                                    }
                                    
                                    else
                                    AdminWatchLabel.setLayoutX(xpos);
                                    
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
