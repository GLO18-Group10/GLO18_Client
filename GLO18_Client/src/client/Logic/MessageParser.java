/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

/**
 *
 * @author Peterzxcvbnm
 */
public class MessageParser {
    LogicFacade logic = new LogicFacade();
    
    public void toProtocol01(){
        logic.sendMessage("01");
        fromProtocol01(logic.receiveMessage());
    }
    
    private void fromProtocol01(String message){
        //Split the received data into the different parts
        String[] data = message.split(";");
        
    }
}
