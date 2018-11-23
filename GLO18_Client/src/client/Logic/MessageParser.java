/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

import client.Acquaintance.ILogic;

/**
 *
 * @author Peterzxcvbnm
 */
public class MessageParser {
    ILogic logic;

    public MessageParser(ILogic logic) {
        this.logic = logic;
    }

    public String toProtocol00(String ID, String password) {
        String message = "00" + ";" + ID + ";" + password;
        logic.sendMessage(message);
        return logic.receiveMessage();
    }
    
    public int toProtocol02(String accountID){
        logic.sendMessage("02;"+ accountID);
        return Integer.parseInt(logic.receiveMessage()); 
    }

    /**
     * Protocol 01: get customer info
     *
     * @return 01
     */
    public String toProtocol01(String ID){
        return "01;" + ID;
    }

    /**
     * Message to split the response from the server into an array
     *
     * @param message Message from the server
     * @return An array of the different parameters from the server
     */
    public String[] fromProtocol(String message) {
        //Split the received data into the different parts
        String[] data = message.split(";");
        return data;
    }
    
    public String toProtocol05(String senderID, String amount, String recieverID, String text){
        return "05;" + senderID + ";" + amount + ";"+ recieverID + ";" + text;
    
    }
    
    public String toProtocol06(String ID){
    
        return "06;" + ID;
    }
    

    public String toProtocol03(String name, String phonenumber, String address, String email, String ID){
        return ("03;" + name + ";" + phonenumber + ";" + address + ";" + email + ";" + ID);
    }
    public String toProtocol07(String ID, String name, String birthday, String phonenumber, String address, String email, String password) {
        return ("07;" + ID + ";" + name + ";" + birthday + ";" + phonenumber + ";" + address + ";" + email + ";" + password);
        //return fromProtocol01(logic.receiveMessage());
    }

    public String toProtocol18(){
        logic.sendMessage("18");
        return logic.receiveMessage();
    }
    
    public String toProtocol08(){
        return "08";
    
    }
    public String toProtocol10(){
        return "10";
    }
}
