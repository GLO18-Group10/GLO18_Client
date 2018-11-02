/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

import client.Acquaintance.iLink;
import client.Acquaintance.iLogic;

/**
 *
 * @author Jeppe Enevold
 */
public class LogicFacade implements iLogic {

    private static iLink Link;
    private static User user;
    private MessageParser messageParser = new MessageParser(this);

    public void injectLink(iLink LinkLayer) {
        Link = LinkLayer;
    }

    @Override
    public void startConnection() {
        Link.startConnection();
    }

    @Override
    public void sendMessage(String message) {
        Link.sendMessage(message);
    }

    @Override
    public String receiveMessage() {
        return Link.receiveMessage();
    }

    @Override
    public String login(String ID, String password) {
        String message = messageParser.toProtocol00(ID, password);
        if (message.equalsIgnoreCase("true")) {
            initializeUser(ID);
            
        }
        
        return message;
    }
    
    @Override
    public int getAccountBalance(String accountID) {
        return messageParser.toProtocol02(accountID);
    }
    
    public void initializeUser(String ID) {
        
        if (ID.startsWith("A")) {
            user = new Adminstrator(ID);
        
        } 
        else if (ID.startsWith("C")) {
            user = new Customer(ID);
        }
    }
    
    @Override
    //This method could also be renamed to an appropriate name since the arcitecture has changed. For instance createCustomer(). This is preffered.
    public String toProtocol07(String ID, String name, String birthday, String phonenumber, String address, String email, String password){
        sendMessage(messageParser.toProtocol07(ID, name, birthday, phonenumber, address, email,  password));
        return receiveMessage();
    }
}
