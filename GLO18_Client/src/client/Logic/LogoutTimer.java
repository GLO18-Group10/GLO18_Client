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
import javafx.stage.Stage;
public class LogoutTimer extends Timer  {
    
    // Amount of time before logging the user out
    private static long DELAY = 20;
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
                    
                    if (DELAY > 0) {
                        DELAY--;
                    }
                    else if (DELAY <= 0) {
                        DELAY = 0;
                        
                    }

                     
                    
                    
                }
            });
        }
    }
     
    public long getimeRemaining() {
        
        return LogoutTimer.DELAY;
    }
    /*
     * Updates the timer such that the user is logged out 5 minutes from now!
    */
    public void updateTimer() {
        this.purge();
        
        //this.schedule(new LogoutTask(), 1000);
        this.scheduleAtFixedRate(new LogoutTask(), 1000, 1000);
        
    }
     public void cancelTimer(){
         this.cancel();
     
     }
}




