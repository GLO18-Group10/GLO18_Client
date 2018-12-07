/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

/**
 *
 * @author Antonio
 */

import client.Acquaintance.IGUI;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import client.Acquaintance.ILogic;
import java.io.IOException;
import javafx.application.ConditionalFeature;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class LogoutTimer extends Timer  {
    
    // Amount of time before logging the user out
    private static long DELAY = 5*1000;
    ILogic logic;
    IGUI gui;
    private Stage mainStage;
   
    // The task to be executed (logout the user)
    class LogoutTask extends TimerTask {
                
        @Override
        public void run() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                  if(getimeRemaining()==0)
                      cancel();
                    
                    
                }
            });
        }
    }
     
    public long getimeRemaining() {
        return DELAY;
    }
    /*
     * Updates the timer such that the user is logged out 5 minutes from now!
    */
    public void updateTimer() {
        this.purge();
        this.schedule(new LogoutTask(), LogoutTimer.DELAY);
    }
}




